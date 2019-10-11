package com.MohamedTaha.Imagine.Quran.dialog;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.MohamedTaha.Imagine.Quran.R;

public class DialogNotEnternet extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      final AlertDialog  builder = new AlertDialog.Builder(this).create();
        builder.setTitle(R.string.text_alert);
        builder.setMessage(getString(R.string.text_false));
        builder.setCancelable(false);
       /* builder.setPositiveButton("مُوَافِقٌ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });*/
        builder.show();
    }
}
