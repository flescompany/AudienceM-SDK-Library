package com.oneclick.testandroidlib;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.oneclick.audiencem_sdk_library.ToastClass;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ToastClass.Companion.showToast(this,"하이루 뭐야 왜 안떠");

        setContentView(R.layout.activity_main);
    }
}