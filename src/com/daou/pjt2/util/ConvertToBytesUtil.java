package com.daou.pjt2.util;

import java.text.DecimalFormat;

public class ConvertToBytesUtil {
	
	private static final int VALID_LEN = 1;
	private static final long K = 1024;
	private static final long M = K * K;
	private static final long G = M * K;
	private static final long T = G * K;
    private static long[] dividers = new long[] { T, G, M, K, 1 };
    private static String[] units = new String[] { "TB", "GB", "MB", "KB", "B" };
	
	public static String convertBytesUtil(long value){
	    if(value < VALID_LEN)
	        throw new IllegalArgumentException("Invalid file size: " + value);
	    
	    String result = "";
	  
	    for(int i = 0; i < dividers.length; i++){
	        long divider = dividers[i];
	        if(value >= divider){
	            result = format(value, divider, units[i]);
	            break;
	        }
	    }
	    
	    return result;
	}

	private static String format(long value, long divider, String unit){
	    double result = divider > 1 ? (double) value / (double) divider : (double) value;
	    
	    return new DecimalFormat("#,##0.#").format(result) + " " + unit;
	}
}
