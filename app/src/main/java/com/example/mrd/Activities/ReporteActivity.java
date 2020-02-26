package com.example.mrd.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mrd.R;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class ReporteActivity extends AppCompatActivity {

    int REQUEST_TAKE_PHOTO = 1;
    static final int FOTO_FRONTAL = 1;
    static final int FOTO_TRASERA = 2;
    static final int FOTO_LADO1 = 3;
    static final int FOTO_LADO2 = 4;

    private ImageButton ibtnFrontal;
    private ImageButton ibtnTrasera;
    private ImageButton ibtnLado1;
    private ImageButton ibtnLado2;

    private Button btnGuardar;

    ArrayList<String> imgPath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte);

        imgPath = new ArrayList<String>();
        imgPath.add("");
        imgPath.add("");
        imgPath.add("");
        imgPath.add("");

        ibtnFrontal = findViewById(R.id.imageButtonFrontal);
        ibtnTrasera = findViewById(R.id.imageButtonTrasera);
        ibtnLado1 = findViewById(R.id.imageButtonLado1);
        ibtnLado2 = findViewById(R.id.imageButtonLado2);



        if (ContextCompat.checkSelfPermission(ReporteActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(ReporteActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ReporteActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 1000);
        }

        ibtnFrontal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                REQUEST_TAKE_PHOTO = 1;
                tomarFoto();
                imgPath.set(0, currentPhotoPath);
            }
        });

        ibtnTrasera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ibtnLado1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ibtnLado2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    }

    String currentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "BACKUP_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }



    private void tomarFoto() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                Toast.makeText(ReporteActivity.this, "Error al crear el archivo", Toast.LENGTH_SHORT).show();
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(ReporteActivity.this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI.toString());
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    static final  int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            switch (requestCode){
                case FOTO_FRONTAL:
                break;
                case FOTO_TRASERA:
                    break;
                case FOTO_LADO1:
                    break;
                case FOTO_LADO2:
                    break;
                    //TODO Terminar de llenar el switch y probar
            }
            ibtnFrontal.setImageBitmap(imageBitmap);
        }
    }


}
