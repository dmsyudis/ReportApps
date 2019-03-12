package com.duakoma.reportapps.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.duakoma.reportapps.R;

public class MainActivity extends AppCompatActivity {
    ImageView imgAnggota, imgSipil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgAnggota = (ImageView)findViewById(R.id.img_anggota);
        imgSipil = (ImageView)findViewById(R.id.img_sipil);

        imgAnggota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginAnggotaActivity.class);
                startActivity(intent);
            }
        });
        imgSipil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginSipilActivity.class);
                startActivity(intent);
            }
        });
    }
}
