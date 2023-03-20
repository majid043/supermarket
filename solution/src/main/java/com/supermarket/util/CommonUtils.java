package com.supermarket.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Map;

public class CommonUtils {

	public static boolean isNullOrEmptyString(String source) {
		if (null == source || source.trim().length() == 0) {
			return true;
		}
		return false;
	}

	public static boolean isNullOrZeroOrNegativeInt(Integer source) {
		return null == source || source < 1;
	}

	public static boolean isNullOrEmptyCollection(Collection<?> list) {
		if (null == list || list.isEmpty()) {
			return true;
		}
		return false;
	}

	public static boolean isNullOrEmptyMap(Map<?, ?> map) {
		if (null == map || map.isEmpty()) {
			return true;
		}
		return false;
	}

	public static <T, V> boolean putValueInMap(Map<T, V> map, T key, V value) {
		if (null != map && null != key && null != value) {
			map.put(key, value);
			return true;
		}
		return false;
	}

	public static int convertStringToInt(String data) {
		int retVal = 0;
		if (isNullOrEmptyString(data)) {
			return retVal;
		}
		try {
			retVal = Integer.valueOf(data);
		} catch (NumberFormatException e) {}
		return retVal;
	}

	public static String formatDecimalValue(BigDecimal value, String pattern) {
		DecimalFormat decimalFormatter = (DecimalFormat) DecimalFormat.getInstance();
		decimalFormatter.applyPattern(pattern);
		return decimalFormatter.format(value);
	}

	public static String[] tokenizeString(String input, String delimeter, boolean isEscapeDelimeter, int limit) {
		String[] tokens = null;
		if (isNullOrEmptyString(input)) {
			return null;
		}
		if (isEscapeDelimeter) {
			tokens = input.split(Constants.ESCAPE_STRING + delimeter, limit);
		} else {
			tokens = input.split(delimeter, limit);
		}
		return tokens;
	}

	public static String concatValues(Object... arguments) {
		String concatenatedString = null;
		if (null != arguments && arguments.length > 0) {
			StringBuilder builder = new StringBuilder();
			for (int index = 0; index < arguments.length; index++) {
				builder.append(arguments[index]);
			}
			concatenatedString = builder.toString();
			builder = null;
		}
		return concatenatedString;
	}
}