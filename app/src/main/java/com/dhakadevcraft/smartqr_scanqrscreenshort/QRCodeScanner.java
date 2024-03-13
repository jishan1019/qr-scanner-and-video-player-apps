package com.dhakadevcraft.smartqr_scanqrscreenshort;

import android.graphics.Bitmap;
import android.util.Log;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;

public class QRCodeScanner {

    public static String scanQRCode(Bitmap imageBitmap) {
        try {
            MultiFormatReader reader = new MultiFormatReader();

            int[] intArray = new int[imageBitmap.getWidth() * imageBitmap.getHeight()];
            imageBitmap.getPixels(intArray, 0, imageBitmap.getWidth(), 0, 0, imageBitmap.getWidth(), imageBitmap.getHeight());

            LuminanceSource source = new RGBLuminanceSource(imageBitmap.getWidth(), imageBitmap.getHeight(), intArray);
            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(source));

            Result result = reader.decode(binaryBitmap);

            if (result != null) {
                return result.getText();
            }
        } catch (Exception e) {
            Log.e("QRCodeScanner", "Error scanning QR code: " + e.getMessage());
        }
        return null;
    }

}
