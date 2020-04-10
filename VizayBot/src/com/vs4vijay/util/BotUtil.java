package com.vs4vijay.util;

/**
 * 
 * A General Bot Utility
 * 
 * @author Vijay
 * 
 */
public class BotUtil {

	public static String getGeneralResponse(String sender, String body) {
		// String sender = jid.getId().split("@")[0];
		String response = "Hey *" + sender + "*, You have entered : " + body;
		return response;
	}
}
