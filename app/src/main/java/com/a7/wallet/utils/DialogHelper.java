package com.a7.wallet.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.a7.wallet.R;


/**
 * Created by viosonlee
 * on 2017/8/14.
 * for
 */

public class DialogHelper {

    public static Dialog getProgressDialog(Context context) {
        View view= LayoutInflater.from(context).inflate(R.layout.loading_progress, null);
        Dialog dialog = new Dialog(context, R.style.progress_dialog);
        dialog.setContentView(view, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        dialog.setCancelable(false);
        return dialog;
    }


}
