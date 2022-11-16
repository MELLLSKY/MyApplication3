package com.example.myapplication3;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

public class PostActivity extends AppCompatActivity {

    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);


        final ActivityResultLauncher<Intent> launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK){
                            Bundle bundle = result.getData().getExtras();
                            Bitmap image = (Bitmap) bundle.get("data");
                            img.setImageBitmap(image);
                        }
                    }
                });
        img = findViewById(R.id.imageView1);
        img.setOnClickListener(new View.OnClickListener(){
                                   @Override
                                   public void onClick(View view){
                                       Intent intent =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                       launcher.launch(intent);
                                   }

                               }
        );
    }
}