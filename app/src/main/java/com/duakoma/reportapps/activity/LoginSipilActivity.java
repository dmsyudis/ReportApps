package com.duakoma.reportapps.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.duakoma.reportapps.Adapter.Model;
import com.duakoma.reportapps.Adapter.SharedPref;
import com.duakoma.reportapps.Api.Api;
import com.duakoma.reportapps.Api.ApiClient;
import com.duakoma.reportapps.R;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lenovo on 1/24/2019.
 */

public class LoginSipilActivity extends AppCompatActivity {
    String USER_NAME = "USER_NAME";
    Button btnLogin, btnDaftar;
    TextView btn_showPass, txtAnggota;
    EditText ktp,password;
    int setType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sipil_login);
        btnLogin = (Button)findViewById(R.id.btn_login);
        password = (EditText)findViewById(R.id.password);
        ktp = (EditText)findViewById(R.id.id_ktp);
        btnDaftar = (Button)findViewById(R.id.btn_daftar_sipil);

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginSipilActivity.this, DaftarSipilActivity.class);
                startActivity(intent);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(LoginSipilActivity.this, BerandaActivity.class);
                //startActivity(intent);
                validateUserData();
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
    private void validateUserData(){
        final String sKTP = ktp.getText().toString();
        final String sPassword = password.getText().toString();

        if(TextUtils.isEmpty(sKTP)){
            ktp.setError("Harap masukkan nomor KTP");
            ktp.requestFocus();
            btnLogin.setEnabled(true);
            return;
        }
        if(TextUtils.isEmpty(sPassword)){
            password.setError("Harap masukkan password");
            password.requestFocus();
            btnLogin.setEnabled(true);
            return;
        }
        loginUser(sKTP, sPassword);
    }
    private void loginUser(final String ktp, String password){
        Api api = ApiClient.getClient().create(Api.class);
        Call<Model> login = api.login(ktp, password);

        login.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                if(response.body().getIsSuccess() == 1){
                    //get username
                    String user = response.body().getKtp();
                    //storing the user in shared preferences
                    SharedPref.getInstance(LoginSipilActivity.this).storeUserName(user);
//                    Toast.makeText(MainActivity.this,response.body().getUsername(),Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginSipilActivity.this, BerandaActivity.class);
                    //intent.putExtra(USER_NAME, ktp);
                    startActivity(intent);
                }else{
                    Toast.makeText(LoginSipilActivity.this,response.body().getMessage(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                Toast.makeText(LoginSipilActivity.this,t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

}
