package com.duakoma.reportapps.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.duakoma.reportapps.Adapter.SharedPref;
import com.duakoma.reportapps.R;

public class AkunActivity extends AppCompatActivity {

    EditText nama, ktp, alamat, noHp;
    Button btnLogout;
    String USER_NAME = "USER_NAME";
    String userName, sUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_akun);

        nama = (EditText)findViewById(R.id.id_nama);
        ktp = (EditText)findViewById(R.id.id_ktp);
        alamat = (EditText)findViewById(R.id.id_alamat);
        noHp = (EditText)findViewById(R.id.id_nomorHP);
        btnLogout = (Button)findViewById(R.id.id_logout);
        if (getIntent().hasExtra(USER_NAME)) {
            userName = getIntent().getStringExtra(USER_NAME);
            System.out.println("ini KTP: "+userName);
            ktp.setText(userName);
        }

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                SharedPref.getInstance(getApplicationContext()).logout();
            }
        });
    }
}
