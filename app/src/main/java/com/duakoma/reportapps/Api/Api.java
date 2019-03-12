package com.duakoma.reportapps.Api;

import android.content.Intent;
import android.graphics.ColorSpace;

import com.duakoma.reportapps.Adapter.Model;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by lenovo on 1/29/2019.
 */

public interface Api {

    @POST("regist_sipil.php")
    @FormUrlEncoded
    Call<Model> register(@Field("nama_sipil") String username,
                                    @Field("no_ktp") String ktp,
                                    @Field("alamat") String alamat,
                                    @Field("no_hp") String noHp,
                                    @Field("password") String password);

    @POST("login_sipil.php")
    @FormUrlEncoded
    Call<Model> login(@Field("no_ktp") String ktp, @Field("password") String password);

    @POST("profil.php")
    @FormUrlEncoded
    Call<Model> profilsipil(@Field("no_ktp") String ktp);
}
