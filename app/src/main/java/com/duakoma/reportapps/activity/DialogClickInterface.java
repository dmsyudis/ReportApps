package com.duakoma.reportapps.activity;

import android.content.DialogInterface;

/**
 * Created by lenovo on 1/19/2019.
 */

public interface DialogClickInterface {

    public void onClickPositiveButton(DialogInterface pDialog, int pDialogIntefier);

    public void onClickNegativeButton(DialogInterface pDialog, int pDialogIntefier);
}
