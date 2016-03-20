package com.astro.vijay.unavuwithparse.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.astro.vijay.unavuwithparse.Model.FoodItem;
import com.astro.vijay.unavuwithparse.R;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseUser;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewMealActivity extends AppCompatActivity {

    // PICK_PHOTO_CODE is a constant integer
    public final static int PICK_PHOTO_CODE = 1046;
    private ParseFile photoFile;

    @Bind(R.id.etName)
    EditText etName;

    @Bind(R.id.etDescription)
    EditText etDescription;

    @Bind(R.id.etCusine)
    EditText etCusine;

    @Bind(R.id.ivChoosePic)
    ParseImageView ivChoosePic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_meal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        ButterKnife.bind(this);
        ivChoosePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPickPhoto();
            }
        });
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_meal, menu);
        return true;
    }

    public void onSave(MenuItem item) {
        FoodItem foodItem = new FoodItem();
        foodItem.setUser(ParseUser.getCurrentUser());
        foodItem.setName(etName.getText().toString());
        foodItem.setDescription(etDescription.getText().toString());
        foodItem.setCusine(etCusine.getText().toString());
        photoFile.saveInBackground();
        foodItem.setPhotoFile(photoFile);
        foodItem.saveInBackground();
        finish();
    }

    public void onPickPhoto() {
        // Create intent for picking a photo from the gallery
        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        // If you call startActivityForResult() using an intent that no app can handle, your app will crash.
        // So as long as the result is not null, it's safe to use the intent.
        if (intent.resolveActivity(getPackageManager()) != null) {
            // Bring up gallery to select a photo
            startActivityForResult(intent, PICK_PHOTO_CODE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {
            Uri photoUri = data.getData();
            // Do something with the photo based on Uri
            Bitmap selectedImage = null;
            try {
                selectedImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            selectedImage.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byte[] byteArray = stream.toByteArray();

            photoFile = new ParseFile(byteArray);
            ivChoosePic.setImageBitmap(selectedImage);
        }
    }
}
