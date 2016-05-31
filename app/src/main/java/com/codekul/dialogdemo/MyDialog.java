package com.codekul.dialogdemo;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyDialog extends DialogFragment {

    public static final String KEY_ALERT_DIALOG = "alertDialog";

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Dialog dialog = null;

        if(getTag().equalsIgnoreCase(KEY_ALERT_DIALOG)){

            final DialogClick click = new DialogClick();
            AlertDialog.Builder builder =
                    new AlertDialog.Builder(getActivity())
                    .setTitle("Title")
                    .setMessage("MEssage")
                    .setIcon(R.mipmap.ic_launcher)
                    .setPositiveButton("Positive",click)
                    .setNeutralButton("Neutral",click)
                    .setNegativeButton("Negative",click);

            dialog = builder.create();
        }

        return dialog;
    }

    private class DialogClick implements
            DialogInterface.OnClickListener {

        @Override
        public void onClick(DialogInterface dialog, int which) {

            if(which == DialogInterface.BUTTON_NEGATIVE){
                mt("negative");
            }
            else if(which == DialogInterface.BUTTON_POSITIVE){
                mt("Positive");
            }
            else{
                mt("Neutral");
            }
        }
    }

    private void mt(String msg){
        Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
    }
}
