package com.example.taxi;


import android.graphics.Bitmap;
import android.os.Bundle;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.util.Hashtable;

public final class QRCodeEncoder {
    private static final int WHITE = 0xFFFFFFFF;
    private static final int BLACK = 0xFF000000;

    private int dimension = Integer.MIN_VALUE;
    private String contents = null;
    private BarcodeFormat format = null;
    private boolean encoded = false;

    public QRCodeEncoder(String data, Bundle bundle, String format, int dimension) {
        this.dimension = dimension;
        encoded = encodeContents(data, bundle, format);
    }

    private boolean encodeContents(String data, Bundle bundle,  String formatString) {
        // Default to QR_CODE if no format given.
        format = null;
        if (formatString != null) {
            try {
                format = BarcodeFormat.valueOf(formatString);
            } catch (IllegalArgumentException iae) {
                // Ignore it then
            }
        }
        if (data != null && data.length() > 0) {
            contents = data;

        }
        return contents != null && contents.length() > 0;
    }

    public Bitmap encodeAsBitmap() throws WriterException {
    	if (!encoded) return null;

    	Hashtable<EncodeHintType, String> hints = null;
    	String encoding = guessAppropriateEncoding(contents);
    	if (encoding != null) {
	    	hints = new Hashtable<EncodeHintType, String>();
	    	hints.put(EncodeHintType.CHARACTER_SET, encoding);
    	}
    	MultiFormatWriter writer = new MultiFormatWriter();
    	BitMatrix result = writer.encode(contents, format, dimension, dimension, hints);
    	int width = result.getWidth();
    	int height =result.getHeight();
    	int[] pixels = new int[width * height];
    	// All are 0, or black, by default
    	for (int y = 0; y < height; y++) {
	    	int offset = y * width;
	    	for (int x = 0; x < width; x++) {
	
		    	if(!result.get(x, y))
		    	{
		    	pixels[offset + x] = WHITE ;
		    	}
		    	else
		    	pixels[offset + x] = BLACK ;
	
	    	}
    	}

    	Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
    	bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
    	return bitmap;
   }

    private static String guessAppropriateEncoding(CharSequence contents) {
        // Very crude at the moment
        for (int i = 0; i < contents.length(); i++) {
            if (contents.charAt(i) > 0xFF) { return "UTF-8"; }
        }
        return null;
    }

} 
