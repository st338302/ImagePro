package project.task.saurabh.imagepro.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;

import project.task.saurabh.imagepro.R;

public class StaticUtils {

    public static String ARG_URI = "URI";
    public static String ARG_DESC = "Description";
    public static int PICKER_CODE = 111;

    public static void startGallery(Activity context) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        context.startActivityForResult(Intent.createChooser(intent,
                context.getString(R.string.select_picture)),
                PICKER_CODE);
    }

    public static boolean isReadPermissionGranted(Activity context) {
        return ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    public static void requestReadPermission(Activity context) {
        ActivityCompat.requestPermissions(context,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PICKER_CODE);
    }
}
