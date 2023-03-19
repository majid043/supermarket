package com.supermarket.executionmode;

import java.io.IOException;
import java.util.List;

import com.supermarket.fileloader.LoadFileData;
import com.supermarket.util.CommonUtils;
import com.supermarket.util.Constants;

class FileModeImpl extends AbstractExecutionModeHandler {

	@Override
	public void execute(String fileName) throws IOException {
		List<List<String>> values = LoadFileData.readFile(fileName, Constants.SPACE);
		if (CommonUtils.isNullOrEmptyCollection(values)) {
			System.out.println(CommonUtils.concatValues(Constants.INVALID_INPUT, Constants.SPACE, Constants.ERROR_CODE_11));			
		}
		values.forEach(value -> executeCommand(value));
	}

}