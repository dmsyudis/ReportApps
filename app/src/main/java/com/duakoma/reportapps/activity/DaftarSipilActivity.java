package com.duakoma.reportapps.activity;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.duakoma.reportapps.Adapter.Model;
import com.duakoma.reportapps.Api.Api;
import com.duakoma.reportapps.Api.ApiClient;
import com.duakoma.reportapps.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaftarSipilActivity extends AppCompatActivity {
    TextView showPass, showulangPass;
    EditText password, nama, ktp, alamat, noHP, ulangPassword;
    Button btnDaftar;
    int setType = 0;
    final String RegisterURL = "http://192.168.137.1/intelkom/regist_sipil.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_sipil);
        showPass = (TextView)findViewById(R.id.shw_pass);
        password = (EditText)findViewById(R.id.id_password);
        nama = (EditText)findViewById(R.id.id_nama);
        ktp = (EditText)findViewById(R.id.id_ktp);
        alamat = (EditText)findViewById(R.id.id_alamat);
        noHP = (EditText)findViewById(R.id.id_nomorHP);
        btnDaftar = (Button)findViewById(R.id.btn_daftar);
        ulangPassword = (EditText)findViewById(R.id.id_ulang_password);
        showulangPass = (TextView)findViewById(R.id.shw__ulang_pass);

        setType = 1;
        showulangPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(setType == 1){
                    setType = 0;
                    ulangPassword.setTransformationMethod(null);
                    if(ulangPassword.getText().length() > 0){
                        ulangPassword.setSelection(ulangPassword.getText().length());
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            showulangPass.setBackgroundResource(R.drawable.ic_action_eye_open_black);
                        }
                    }
                }
                else{
                    setType = 1;
                    ulangPassword.setTransformationMethod(new PasswordTransformationMethod());
                    if(ulangPassword.getText().length()>0){
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            showulangPass.setBackgroundResource(R.drawable.ic_action_eye_closed_black);
                        }
                    }
                }
            }
        });
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
                            showPass.setBackgroundResource(R.drawable.ic_action_eye_open_black);
                        }
                    }
                }
                else{
                    setType = 1;
                    password.setTransformationMethod(new PasswordTransformationMethod());
                    if(password.getText().length()>0){
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            showPass.setBackgroundResource(R.drawable.ic_action_eye_closed_black);
                        }
                    }
                }
            }
        });
        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateUserData();
            }
        });
    }
    private void validateUserData(){
        final String reg_nama = nama.getText().toString();
        final String reg_ktp = ktp.getText().toString();
        final String reg_alamat = alamat.getText().toString();
        final String reg_noHP = noHP.getText().toString();
        final String reg_password = password.getText().toString();
        final String reg_ulang_password = ulangPassword.getText().toString();

        if(TextUtils.isEmpty(reg_nama)){
            nama.setError("Harap masukkan nama");
            nama.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(reg_ktp)){
            ktp.setError("Harap masukkan nomor KTP");
            ktp.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(reg_alamat)){
            alamat.setError("Harap masukkan alamat anda");
            alamat.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(reg_noHP)){
            noHP.setError("Harap masukkan nomor HP anda");
            noHP.requestFocus();
            return;
        }
        if(!reg_password.equals(reg_ulang_password)){
            password.setError("Password tidak cocok");
            password.requestFocus();
            return;
        }
        registerUser(reg_nama,reg_ktp,reg_alamat, reg_noHP, reg_password);
    }
    private void registerUser (String user_name, String user_ktp, String user_alamat, String user_noHP, String user_password){
        final String reg_nama = nama.getText().toString();
        final String reg_ktp = ktp.getText().toString();
        final String reg_alamat = alamat.getText().toString();
        final String reg_noHP = noHP.getText().toString();
        final String reg_password = password.getText().toString();

        Api api = ApiClient.getClient().create(Api.class);
        Call<Model> login = api.register(user_name, user_ktp, user_alamat, user_noHP, user_password);

        login.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                if(response.body().getIsSuccess() == 1){
                    //get username
                    String user = response.body().getUsername();
                    startActivity(new Intent(DaftarSipilActivity.this,MainActivity.class));
                }else{
                    Toast.makeText(DaftarSipilActivity.this,response.body().getMessage(),Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                Toast.makeText(DaftarSipilActivity.this,t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }
}
