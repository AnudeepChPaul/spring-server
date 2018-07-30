package com.springserver.SpringServer.utils;

public class MessageUtils {
	public static String getMessage(String code) {
		switch (code) {
			case "URL_NOT_FOUND": return "Not a valid url.";
		}
		return "Something went wrong. We're trying hard to fix it.";
	}
}
