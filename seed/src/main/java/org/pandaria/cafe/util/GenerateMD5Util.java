package org.pandaria.cafe.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class GenerateMD5Util {
    
    private static Logger logger = LoggerFactory.getLogger(GenerateMD5Util.class);
    public static final String BLANK = "";

    private GenerateMD5Util() {}
    
	/**
	 * Convert to Hex string
	 * 
	 * @param data
	 * @return
	 */
	public static String toHex(byte[] data) {
		if (data == null) {
			throw new IllegalArgumentException("Byte array must not be null");
		}

		StringBuffer hex = new StringBuffer(data.length * 2);
		for (int i = 0; i < data.length; i++) {
			hex.append(Character.forDigit((data[i] & 0XF0) >> 4, 16));
			hex.append(Character.forDigit((data[i] & 0X0F), 16));
		}
		return hex.toString().toUpperCase();
	}

	public static String generateMD5(String... source) {
		String result = null;
        String message = BLANK;

		if (source == null) {
			return null;
		}

		for (String s : source) {
            if (s != null && !BLANK.equals(s)) {
				message += s;
			}
		}

        if (message.equals(BLANK)) {
			return null;
		}

		MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(message.getBytes("UTF-8"));
            result = toHex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            logger.error(e.getMessage());
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage());
        }

		return result;

	}

	/**
	 * Method used to generate md5
	 * 
	 * @param lst
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @throws NoSuchAlgorithmException 
	 */
	public static String generateMD5(List<String> lst) {
	    String[] vals = null;
		if (lst == null) {
			vals = new String[0];
		} else {
		    vals = lst.toArray(new String[0]);
		}
		return GenerateMD5Util.generateMD5(vals);
	}
	
}