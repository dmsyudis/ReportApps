package com.duakoma.reportapps.activity;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.duakoma.reportapps.Adapter.Utility;
import com.duakoma.reportapps.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;

@RequiresApi(api = Build.VERSION_CODES.N)
public class FormActivity extends AppCompatActivity {
    EditText sDate, sLokasi;
    Button sSave;
    RelativeLayout btn_foto, btn_foto1, btn_foto2;
    ImageView imgFoto, img_foto2, img_foto3, take_picture_1, take_picture_2, take_picture_3;
    String newAddress = null;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1, SELECT_FILE2 = 2, SELECT_FILE3 = 3;
    private String userChoosenTask;
    final Calendar myCalendar = Calendar.getInstance();
    private static final int MY_CAMERA_REQUEST_CODE = 100, MY_CAMERA_REQUEST_CODE2 = 101, MY_CAMERA_REQUEST_CODE3 = 102;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        sDate = (EditText) findViewById(R.id.id_date);
        sSave = (Button)findViewById(R.id.id_simpan);
        btn_foto = (RelativeLayout) findViewById(R.id.line_foto);
        btn_foto1 = (RelativeLayout)findViewById(R.id.line_foto_1);
        btn_foto2 = (RelativeLayout)findViewById(R.id.line_foto_2);
        sLokasi = (EditText)findViewById(R.id.id_lokasi);
        img_foto2 = (ImageView)findViewById(R.id.img_foto2);
        img_foto3 = (ImageView)findViewById(R.id.img_foto3);
        take_picture_1 = (ImageView)findViewById(R.id.take_pict_1);
        take_picture_2 = (ImageView)findViewById(R.id.take_pict_2);
        take_picture_3 = (ImageView)findViewById(R.id.take_pict_3);
        sLokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(newAddress != null){
                    Intent intent = new Intent(FormActivity.this, LokasiActivity.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(FormActivity.this, LokasiActivity.class);
                    startActivity(intent);
                }

            }
        });
        if (savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                newAddress = null;
            }else{
                newAddress = extras.getString("Full_ADDRESS");
                sLokasi.setText(newAddress);
            }
        }else{
            newAddress = (String) savedInstanceState.getSerializable("Full_ADDRESS");
        }
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };
        sDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(FormActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        sSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FormActivity.this, "Data Berhasil di Simpan", Toast.LENGTH_SHORT).show();

            }
        });
        //camera 1
        btn_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
        //camera 2
        btn_foto1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage2();
            }
        });
        btn_foto2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage3();
            }
        });
        imgFoto = (ImageView)findViewById(R.id.img_foto);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if(userChoosenTask.equals("Take Photo")){
                        if (checkSelfPermission(Manifest.permission.CAMERA)
                                != PackageManager.PERMISSION_GRANTED) {
                            requestPermissions(new String[]{Manifest.permission.CAMERA},
                                    MY_CAMERA_REQUEST_CODE);
                        }
                        if (requestCode == MY_CAMERA_REQUEST_CODE) {
                            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                                cameraIntent();
                                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                    else if(userChoosenTask.equals("Choose from Library"))
                        galleryIntent();
                } else {
                    //code for deny
                }
                break;
        }
    }
    private void selectImage() {
        final CharSequence[] items = { "Take Photo", "Choose from Library",
                "Cancel" };

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(FormActivity.this);
        builder.setTitle("Add Photo 1");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result=Utility.checkPermission(FormActivity.this);

                if (items[item].equals("Take Photo")) {
                    userChoosenTask ="Take Photo";
                    if(result)
                        cameraIntent();

                } else if (items[item].equals("Choose from Library")) {
                    userChoosenTask ="Choose from Library";
                    if(result)
                        galleryIntent();

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void galleryIntent()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"),SELECT_FILE);
    }

    private void cameraIntent()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, MY_CAMERA_REQUEST_CODE);
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == SELECT_FILE2)
                onSelectFromGalleryResult2(data);
            else if(requestCode == SELECT_FILE3)
                onSelectFromGalleryResult3(data);
            else if (requestCode == MY_CAMERA_REQUEST_CODE)
                onCaptureImageResult(data);
            else if(requestCode == MY_CAMERA_REQUEST_CODE2)
                onCaptureImageResult2(data);
            else if (requestCode == MY_CAMERA_REQUEST_CODE3)
                onCaptureImageResult3(data);
        }
    }

    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");

        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        imgFoto.setVisibility(View.GONE);
        take_picture_1.setVisibility(View.VISIBLE);
        take_picture_1.setImageBitmap(thumbnail);
    }

    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {

        Bitmap bm=null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        imgFoto.setVisibility(View.GONE);
        take_picture_1.setVisibility(View.VISIBLE);
        take_picture_1.setImageBitmap(bm);
    }

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        sDate.setText(sdf.format(myCalendar.getTime()));
    }
    private void selectImage2() {
        final CharSequence[] items = { "Take Photo", "Choose from Library",
                "Cancel" };

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(FormActivity.this);
        builder.setTitle("Add Photo 2");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result=Utility.checkPermission(FormActivity.this);

                if (items[item].equals("Take Photo")) {
                    userChoosenTask ="Take Photo";
                    if(result)
                        cameraIntent2();

                } else if (items[item].equals("Choose from Library")) {
                    userChoosenTask ="Choose from Library";
                    if(result)
                        galleryIntent2();

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
    private void selectImage3() {
        final CharSequence[] items = { "Take Photo", "Choose from Library",
                "Cancel" };

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(FormActivity.this);
        builder.setTitle("Add Photo 3");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result=Utility.checkPermission(FormActivity.this);

                if (items[item].equals("Take Photo")) {
                    userChoosenTask ="Take Photo";
                    if(result)
                        cameraIntent3();

                } else if (items[item].equals("Choose from Library")) {
                    userChoosenTask ="Choose from Library";
                    if(result)
                        galleryIntent3();

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
    private void cameraIntent2()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, MY_CAMERA_REQUEST_CODE2);
        }
    }
    private void cameraIntent3()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, MY_CAMERA_REQUEST_CODE3);
        }
    }
    private void galleryIntent2()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"),SELECT_FILE2);
    }
    private void galleryIntent3()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"),SELECT_FILE3);
    }
    private void onCaptureImageResult2(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");

        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        img_foto2.setVisibility(View.GONE);
        take_picture_2.setVisibility(View.VISIBLE);
        take_picture_2.setImageBitmap(thumbnail);
    }
    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult2(Intent data) {

        Bitmap bm=null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        img_foto2.setVisibility(View.GONE);
        take_picture_2.setVisibility(View.VISIBLE);
        take_picture_2.setImageBitmap(bm);
    }
    private void onCaptureImageResult3(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");

        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        img_foto3.setVisibility(View.GONE);
        take_picture_3.setVisibility(View.VISIBLE);
        take_picture_3.setImageBitmap(thumbnail);
    }
    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult3(Intent data) {

        Bitmap bm=null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        img_foto3.setVisibility(View.GONE);
        take_picture_3.setVisibility(View.VISIBLE);
        take_picture_3.setImageBitmap(bm);
    }
}
