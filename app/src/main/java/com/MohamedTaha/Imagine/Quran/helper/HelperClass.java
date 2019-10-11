package com.MohamedTaha.Imagine.Quran.helper;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.MohamedTaha.Imagine.Quran.R;

public class HelperClass {
    public static void customToast(Activity activity, String ToastTitle) {

        LayoutInflater inflater = activity.getLayoutInflater();

        View layout = inflater.inflate(R.layout.custom_toast,
                (ViewGroup) activity.findViewById(R.id.toast_layout_root));

        TextView text = layout.findViewById(R.id.text);
        text.setText(ToastTitle);

        Toast toast = new Toast(activity);
        toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.BOTTOM, 0, 100);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }

    // This method for handle Activity
    public static void startActivity(Context context, Class<?> toActivity) {
        Intent startActivity = new Intent(context, toActivity);
        startActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(startActivity);
    }

    public static void replece(Fragment fragment, FragmentManager fragmentManager, int id, Toolbar toolbar, String title) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(id, fragment);
        transaction.addToBackStack(null);
        // for change from commit() because don't happen Error
        //   java.lang.IllegalStateException: Can not perform this action after onSaveInstanceState
        transaction.commitAllowingStateLoss();

        if (toolbar != null) {
            toolbar.setTitle(title);
        }
    }

    //this method for Close App
    public static void AlertDialogForCloseApp(final Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.tetileClose);
        builder.setMessage(R.string.messageClose);
        builder.setPositiveButton(R.string.textYes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                closeApp(context);
            }
        });
        builder.setNegativeButton(R.string.textNo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });builder.show();
    }

    //This method for Exit App
    public static void closeApp(Context context) {
        Intent exitAppIntent = new Intent(Intent.ACTION_MAIN);
        exitAppIntent.addCategory(Intent.CATEGORY_HOME);
        exitAppIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(exitAppIntent);
    }
}
