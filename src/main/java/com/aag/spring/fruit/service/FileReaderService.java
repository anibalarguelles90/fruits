package com.aag.spring.fruit.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.aag.spring.fruit.exception.FileStorageException;

@Service
public class FileReaderService {

	public List<List<String>> readFile(MultipartFile file) {
		// Normalize file name
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try {
			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}
			return getRecordsFromCsv(file.getInputStream(), ',');

		} catch (IOException ex) {
			throw new FileStorageException("Could not load file " + fileName + ". Please try again!", ex);
		}
	}

	public static List<List<String>> getRecordsFromCsv(InputStream csvInput, char csvSeparator) {

		// Prepare.
		BufferedReader csvReader = null;
		List<List<String>> csvList = new ArrayList<>();
		String csvRecord = null;

		// Process records.
		try {
			csvReader = new BufferedReader(new InputStreamReader(csvInput, "UTF-8"));
			while ((csvRecord = csvReader.readLine()) != null) {
				csvList.add(parseCsvRecord(csvRecord, csvSeparator));
			}
		} catch (IOException e) {
			throw new RuntimeException("Reading CSV failed.", e);
		} finally {
			if (csvReader != null)
				try {
					csvReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}

		return csvList;
	}

	private static List<String> parseCsvRecord(String record, char csvSeparator) {

		// Prepare.
		boolean quoted = false;
		StringBuilder fieldBuilder = new StringBuilder();
		List<String> fields = new ArrayList<>();

		// Process fields.
		for (int i = 0; i < record.length(); i++) {
			char c = record.charAt(i);
			fieldBuilder.append(c);

			if (c == '"') {
				quoted = !quoted; // Detect nested quotes.
			}

			if ((!quoted && c == csvSeparator) // The separator ..
					|| i + 1 == record.length()) // .. or, the end of record.
			{
				String field = fieldBuilder.toString() // Obtain the field, ..
						.replaceAll(csvSeparator + "$", "") // .. trim ending separator, ..
						.replaceAll("^\"|\"$", "") // .. trim surrounding quotes, ..
						.replace("\"\"", "\""); // .. and un-escape quotes.
				fields.add(field.trim()); // Add field to List.
				fieldBuilder = new StringBuilder(); // Reset.
			}
		}

		return fields;
	}
}
