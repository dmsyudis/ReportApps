package com.duakoma.reportapps.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;

import com.duakoma.reportapps.Adapter.Model;
import com.duakoma.reportapps.Adapter.SharedPref;
import com.duakoma.reportapps.Api.Api;
import com.duakoma.reportapps.Api.ApiClient;
import com.duakoma.reportapps.R;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BerandaActivity extends AppCompatActivity implements DialogClickInterface {
    //CircleImageView bantuan, laporan, tugas, akun;
    CardView btn_laporan, btn_kantor, btn_video, btn_tugas, btn_poin, btn_akun;
    private int identifier = 0;
    String USER_NAME = "USER_NAME";
    String sKTP = null;
    String userName, sUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);
        //bantuan = (CircleImageView)findViewById(R.id.text_logo2);
        //laporan = (CircleImageView)findViewById(R.id.btn_laporan);
        //tugas = (CircleImageView)findViewById(R.id.btn_tugas);
        //akun = (CircleImageView)findViewById(R.id.btn_akun);
        btn_laporan = (CardView)findViewById(R.id.id_laporan);
        btn_kantor = (CardView)findViewById(R.id.id_kantor_polisi);
        btn_video = (CardView)findViewById(R.id.id_video);
        btn_tugas = (CardView)findViewById(R.id.id_tugas);
        btn_poin = (CardView)findViewById(R.id.id_poin);
        btn_akun = (CardView)findViewById(R.id.id_akun);

        btn_laporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomAlertDialog.getInstance().showConfirmDialog("Laporan", "Pilih 'FORM' jika ingin mengisi laporan, dan " +
                        "pilih 'HISTORY' jika ingin melihat daftar laporan anda", "Form", "History", BerandaActivity.this, identifier);
            }
        });
        btn_kantor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BerandaActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });

        btn_tugas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BerandaActivity.this, TugasActivity.class);
                startActivity(intent);
            }
        });

        btn_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BerandaActivity.this, R.string.thp_dev, Toast.LENGTH_SHORT).show();
            }
        });
        btn_poin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BerandaActivity.this, R.string.thp_dev, Toast.LENGTH_SHORT).show();
            }
        });

        String loggedUser = SharedPref.getInstance(this).LoggedInUser();
        System.out.println("ini USERNAME: "+loggedUser);

            userName = getIntent().getStringExtra(USER_NAME);
            System.out.println("ini KTP: "+userName);

        sUserName = userName;
        System.out.println("iniiiiiiii KTP: "+sUserName);
        btn_akun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profilsipil(sKTP);
            }
        });
    }
    @Override
    public void onClickPositiveButton(DialogInterface pDialog, int pDialogIntefier) {
        if (pDialogIntefier == 0) {
            Intent intent = new Intent(BerandaActivity.this, FormActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onClickNegativeButton(DialogInterface pDialog, int pDialogIntefier) {
        if (pDialogIntefier == 0) {
            Intent intent = new Intent(BerandaActivity.this, HistoryActivity.class);
            startActivity(intent);
            //Toast.makeText(BerandaActivity.this, "Clicked on negative button..!", Toast.LENGTH_SHORT).show();
        }
    }
    private void profilsipil (final String ktp){


        Api api = ApiClient.getClient().create(Api.class);
        Call<Model> profilsipil = api.profilsipil(ktp);

        profilsipil.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                Intent intent = new Intent(BerandaActivity.this, AkunActivity.class);
                intent.putExtra(USER_NAME, sUserName);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                Toast.makeText(BerandaActivity.this, "GAGAL !", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
