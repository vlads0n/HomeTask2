package app.com.example.android.hometask2.getPhoto;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;

import java.io.File;

/**
 * Created by Влад on 30.11.2016.
 */
public class PhotoHelper {
    private static final int REQUEST_IMAGE_OPEN = 11;
    private static final int REQUEST_IMAGE_CAPTURE = 22;
    private Context context;
    private OnPhotoPicked onPhotoPickedListener;
    private Uri filePath;

    public PhotoHelper(Context context, OnPhotoPicked onPhotoPickedListener) {
        this.context = context;
        this.onPhotoPickedListener = onPhotoPickedListener;
    }

    public void takePhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            filePath = createFileUri();
            if (filePath != null) {
                intent = intent.putExtra(MediaStore.EXTRA_OUTPUT, filePath);
                ActivityCompat.startActivityForResult((AppCompatActivity) context, intent, REQUEST_IMAGE_CAPTURE, null);
            }
        }
    }

    public void pickPhoto() {
        Intent pickPhotoIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        pickPhotoIntent.addCategory(Intent.CATEGORY_OPENABLE);
        pickPhotoIntent.setType("image/*");
        ActivityCompat.startActivityForResult(
                (AppCompatActivity) context, pickPhotoIntent,
                REQUEST_IMAGE_OPEN, null);
    }

    private Uri createFileUri() {
        File file = new File(context.getCacheDir(), "photo.jpg");
        return FileProvider.getUriForFile(context, "app.com.example.android.hometask2", file);
    }

    public void onActivityResult(int resultCode, int requestCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_IMAGE_OPEN) {
                Uri photoUri = data.getData();
                onPhotoPickedListener.onPicked(photoUri);
            } else if (requestCode == REQUEST_IMAGE_CAPTURE) {
                onPhotoPickedListener.onPicked(filePath);
            }
        }
    }

    interface OnPhotoPicked {
        void onPicked(Uri photoUri);
    }
}
