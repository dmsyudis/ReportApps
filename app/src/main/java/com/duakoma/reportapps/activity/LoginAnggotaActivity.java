package com.duakoma.reportapps.activity;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.duakoma.reportapps.R;

public class LoginAnggotaActivity extends AppCompatActivity {

    Button btnLogin, btnDaftar;
    TextView btn_showPass, txtAnggota;
    EditText username,password;
    int setType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = (Button)findViewById(R.id.btn_login);
        password = (EditText)findViewById(R.id.password);
        btnDaftar = (Button)findViewById(R.id.btn_daftar);

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginAnggotaActivity.this, DaftarAnggotaActivity.class);
                startActivity(intent);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginAnggotaActivity.this, BerandaActivity.class);
                startActivity(intent);
            }
        });
        setType = 1;
        btn_showPass = (TextView)findViewById(R.id.shw_pass);
        btn_showPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(setType == 1){
                    setType = 0;
                    password.setTransformationMethod(null);
                    if(password.getText().length() > 0){
                        password.setSelection(password.getText().length());
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            btn_showPass.setBackgroundResource(R.drawable.ic_action_eye_open);
                        }
                    }
                }
                else{
                    setType = 1;
                    password.setTransformationMethod(new PasswordTransformationMethod());
                    if(password.getText().length()>0){
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            btn_showPass.setBackgroundResource(R.drawable.ic_action_eye_closed);
                        }
                    }
                }
            }
        });
    }
}
