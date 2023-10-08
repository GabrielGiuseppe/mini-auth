package com.vr.miniauth.exception;

public abstract class Strings {

	public static String firstUpperCase(String key) {
		String upper = key.substring(0, 1).toUpperCase() + key.substring(1);
		return upper;
	}
}