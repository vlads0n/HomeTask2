package app.com.example.android.hometask2.getPhoto;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import app.com.example.android.hometask2.R;
import app.com.example.android.hometask2.broadcastReceiver.HeadsetReceiver;

public class GetPhotoActivity extends AppCompatActivity {
    ImageView imageView;
    HeadsetReceiver headsetReceiver;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_photo);

        imageView = (ImageView) findViewById(R.id.image_from_camera);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        headsetReceiver = new HeadsetReceiver();
        registerReceiver(headsetReceiver, new IntentFilter("android.intent.action.HEADSET_PLUG"));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(headsetReceiver);
    }
}
