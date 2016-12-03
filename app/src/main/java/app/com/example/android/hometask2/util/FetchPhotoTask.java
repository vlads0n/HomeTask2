package app.com.example.android.hometask2.util;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.Nullable;

import java.io.InputStream;
import java.lang.ref.WeakReference;

/**
 * Created by Влад on 30.11.2016.
 */
public class FetchPhotoTask extends AsyncTask<Uri, Void, Bitmap> {
    private WeakReference<Context> weakReference;
    private OnPhotoProcessed onPhotoProcessedListener;

    public FetchPhotoTask(Context context, OnPhotoProcessed onPhotoProcessedListener) {
        weakReference = new WeakReference<>(context);
        this.onPhotoProcessedListener = onPhotoProcessedListener;
    }

    @Override
    protected Bitmap doInBackground(Uri... params) {
        Uri photo = params[0];
        Context context = weakReference.get();
        Bitmap bitmap = null;
        if (context != null) {
            ContentResolver contentResolver = context.getContentResolver();
            try {
                InputStream inputStream = contentResolver.openInputStream(photo);
                bitmap = BitmapFactory.decodeStream(inputStream);
                inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        onPhotoProcessedListener.onBitmapReady(bitmap);
    }

    public interface OnPhotoProcessed {
        void onBitmapReady(@Nullable Bitmap bitmap);
    }
}
