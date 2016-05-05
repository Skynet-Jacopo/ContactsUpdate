package edu.feicui.contactsupdate.db;

import android.content.Context;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ☆刘群☆ on 2016/5/3.
 */
public class AssetsDBManager {

    public static void copyAssetsFileToFile(Context context, String path, File toFile) throws IOException {

        InputStream is = null;

        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        try {
            is = context.getAssets().open(path);
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(new FileOutputStream(toFile, false));

            int len = 0;
            byte[] buffer = new byte[1024];

            while ((len = bis.read(buffer)) != -1) {

                bos.write(buffer,0,len);
            }
            bos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            bis.close();
            bos.close();
            is.close();
        }

    }
}
