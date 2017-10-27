package com.kyle.mvp.util;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ContextThemeWrapper;
import android.widget.ProgressBar;

import com.kyle.mvp.R;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2017/10/26
 */
public class DialogUtil {
    private DialogUtil() {
    }

    public static Dialog showNoticeDialog(Context context, CharSequence message,
                                          CharSequence actionName, DialogInterface.OnClickListener clickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setPositiveButton(actionName, clickListener);
        builder.setMessage(message);
        return builder.show();
    }

    public static Dialog createLoadingDialog(Context context, CharSequence message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(new ProgressBar(new ContextThemeWrapper(context,
                R.style.Widget_AppCompat_ProgressBar)));
        builder.setMessage(message);
        return builder.create();
    }

    public static void dismissDialog(Dialog dialog) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}
