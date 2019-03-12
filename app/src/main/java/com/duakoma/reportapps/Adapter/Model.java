package com.duakoma.reportapps.Adapter;

/**
 * Created by lenovo on 1/29/2019.
 */

public class Model {


    private String username;
    private String ktp;
    private String alamat;
    private String noHp;
    private String password;

    private int isSuccess;
    private String message;

    public Model(String username, String ktp, String alamat, String noHp, String password, int isSuccess, String message) {
        this.username = username;
        this.ktp = ktp;
        this.alamat = alamat;
        this.noHp = noHp;
        this.password = password;
        this.isSuccess = isSuccess;
        this.message = message;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getKtp() {
        return ktp;
    }

    public void setKtp(String ktp) {
        this.ktp = ktp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(int isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
