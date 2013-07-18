package com.josivansilva.util;

/**
 * Util class containing useful methods.
 * 
 * @author Josivan Silva
 *
 */
public class Util {

	/**
	 * Checks if a given string is not empty.
	 * 
	 * @param str the string to be checked.
	 * @return a boolean indicating the result.
	 */
	public static boolean isNonEmpty (String str) {
		boolean isNonEmpty = false;
		if (str != null && str.length() > 0) {
			isNonEmpty = true;
		}
		return isNonEmpty;
	}
	
}
