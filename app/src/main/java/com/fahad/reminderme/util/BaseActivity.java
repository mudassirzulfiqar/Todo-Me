package com.fahad.reminderme.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;
import timber.log.Timber;

/**
 * Created by moodi on 26/01/2018.
 */

public abstract class BaseActivity extends AppCompatActivity {

    ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mProgressDialog = new ProgressDialog(this);
        initializeDialog();

//        alert();
    }


    private void initializeDialog() {
        mProgressDialog.setMessage(getProgressDialogName());
        mProgressDialog.setCanceledOnTouchOutside(false);
    }

    public void showError(String message) {
        Toasty.error(this, message, Toast.LENGTH_SHORT).show();

    }

    public void showSuccess(String message) {
        Toasty.success(this, message, Toast.LENGTH_SHORT).show();

    }

    public void showInfo(String message) {
        Toasty.info(this, message, Toast.LENGTH_SHORT).show();
    }

    public void showLoading() {
        mProgressDialog.show();
    }

    public void hideLoading() {
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    public abstract String getProgressDialogName();

    public void startActivity(Class<?> activityToOpen) {
        startActivity(new Intent(this, activityToOpen));
    }

    public void log(String msg) {
        Timber.d("log: " + msg);
    }


    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}