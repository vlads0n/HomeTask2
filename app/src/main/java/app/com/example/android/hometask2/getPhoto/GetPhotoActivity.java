package app.com.example.android.hometask2.getPhoto;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import app.com.example.android.hometask2.R;
import app.com.example.android.hometask2.util.FetchPhotoTask;

public class GetPhotoActivity extends AppCompatActivity {
    ImageView imageView;
    PhotoHelper photoHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_photo);

        imageView = (ImageView) findViewById(R.id.image_from_camera);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                photoHelper = new PhotoHelper(GetPhotoActivity.this, new PhotoHelper.OnPhotoPicked() {
                    @Override
                    public void onPicked(Uri photoUri) {
                        FetchPhotoTask fetchPhotoTask = new FetchPhotoTask(GetPhotoActivity.this, listener);
                        fetchPhotoTask.execute(photoUri);
                    }
                });
                photoHelper.pickPhoto();
            }
        });
    }

    private final FetchPhotoTask.OnPhotoProcessed listener = new FetchPhotoTask.OnPhotoProcessed() {
        @Override
        public void onBitmapReady(@Nullable Bitmap bitmap) {
            imageView.setImageBitmap(bitmap);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        photoHelper.onActivityResult(resultCode, requestCode, data);
    }
}
