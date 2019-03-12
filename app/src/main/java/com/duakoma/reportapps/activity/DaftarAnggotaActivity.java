package com.duakoma.reportapps.activity;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.duakoma.reportapps.R;

public class DaftarAnggotaActivity extends AppCompatActivity {

    TextView showPass;
    EditText password;
    int setType = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_anggota);

        showPass = (TextView)findViewById(R.id.shw_pass);
        password = (EditText)findViewById(R.id.id_password);


        setType = 1;
        showPass = (TextView)findViewById(R.id.shw_pass);
        showPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(setType == 1){
                    setType = 0;
                    password.setTransformationMethod(null);
                    if(password.getText().length() > 0){
                        password.setSelection(password.getText().length());
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            showPass.setBackgroundResource(R.drawable.ic_action_eye_open);
                        }
                    }
                }
                else{
                    setType = 1;
                    password.setTransformationMethod(new PasswordTransformationMethod());
                    if(password.getText().length()>0){
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            showPass.setBackgroundResource(R.drawable.ic_action_eye_closed);
                        }
                    }
                }
            }
        });
    }
}
