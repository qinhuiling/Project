package com.bwie.asus.okhttp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static android.graphics.BitmapFactory.decodeFile;

/**
 * Created by ASUS on 2017/9/27.
 */

public class ImageResizeUtils {
    public static Bitmap resizeImage(String path, int specifiedWidth) throws Exception {


        Bitmap bitmap = null;
        FileInputStream inStream = null;
        File f = new File(path);
        System.out.println(path);
        if (!f.exists()) {
            throw new FileNotFoundException();
        }
        try {
            inStream = new FileInputStream(f);
            int degree = readPictureDegree(path);
            BitmapFactory.Options opt = new BitmapFactory.Options();

            opt.inJustDecodeBounds = true;

            decodeFile(path, opt);



            int inSampleSize = 1;
            final int width = opt.outWidth;
//            1000 980
            if (width > specifiedWidth) {
                inSampleSize = (int) (width / (float) specifiedWidth);
            }

            opt.inSampleSize = inSampleSize;
//            opt.inPurgeable = true;

            bitmap = BitmapFactory.decodeStream(inStream, null, opt);
            // bitmap = BitmapFactory.decodeFile(path, opt);
            if (inStream != null) {
                try {
                    inStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    inStream = null;
                }
            }

            if (bitmap == null) {
                return null;
            }
            Matrix m = new Matrix();
            if (degree != 0) {
                //               m.setRotate(degree);
                bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, true);
            }
            float scaleValue = (float) specifiedWidth / bitmap.getWidth();

            m.setScale(scaleValue, scaleValue);
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, true);
            return bitmap;
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }

    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }


    public static void copyStream(InputStream inputStream, OutputStream outStream) throws Exception {
        try {
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, len);
            }
            outStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(inputStream != null){
                inputStream.close();
            }
            if(outStream != null){
                outStream.close();
            }
        }

    }

}
