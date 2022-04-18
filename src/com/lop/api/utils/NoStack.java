package com.wandy.api.utils;

import java.util.*;

public class NoStack {
	public static Random RANDOM;
	private static char[] APPEND;

	static {
		RANDOM = new Random();
		APPEND = new char[] { '§', '\0', '§', '\0', '§', '\0', '§', '\0' };
	}

	public static String randomString() {
		NoStack.APPEND[1] = (char) (48 + NoStack.RANDOM.nextInt(10));
		NoStack.APPEND[3] = (char) (48 + NoStack.RANDOM.nextInt(10));
		NoStack.APPEND[5] = (char) (48 + NoStack.RANDOM.nextInt(10));
		NoStack.APPEND[7] = (char) (48 + NoStack.RANDOM.nextInt(10));
		return new String(NoStack.APPEND);
	}
}
