package com.supermarket.fileloader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.supermarket.util.CommonUtils;

public class LoadFileData {

	public static List<List<String>> readFile(String fileName, String delimeter) throws IOException {
		List<List<String>> values = null;
		if (CommonUtils.isNullOrEmptyString(fileName)) {
			return values;
		}
		try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
			values = lines.map(line -> Arrays.asList(line.split(delimeter))).collect(Collectors.toList());	
		}
		return values;
	}

}