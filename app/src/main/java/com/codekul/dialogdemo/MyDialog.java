package com.codekul.dialogdemo;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyDialog extends DialogFragment {

    public static final String KEY_ALERT_DIALOG = "alertDialog";
    public static final String KEY_DATEPICKER_DIALOG = "datePicker";
    public static final String KEY_TIMEPICKER_DIALOG = "timePicker";
    public static final String KEY_PROGRESS_DIALOG = "progressDialog";
    public static final String KEY_CUSTOM_DIALOG = "customDialog";

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Dialog dialog = null;

        if(getTag().equalsIgnoreCase(KEY_ALERT_DIALOG)){

            final DialogClick click = new DialogClick();
            final AlertDialog.Builder builder =
                    new AlertDialog.Builder(getActivity())
                    .setTitle("Title")
                    .setMessage("MEssage")
                    .setIcon(R.mipmap.ic_launcher)
                    .setPositiveButton("Positive",click)
                    .setNeutralButton("Neutral",click)
                    .setNegativeButton("Negative",click);

            dialog = builder.create();
        }
        else if(getTag().equalsIgnoreCase(KEY_DATEPICKER_DIALOG)){

            final DatePickerDialog datePicker =
                    new DatePickerDialog(getActivity(),
                            new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view,
                                              int year,
                                              int monthOfYear,
                                              int dayOfMonth) {
                            mt(""+dayOfMonth +" - "
                                    +(monthOfYear+1)+" - "+year);
                        }
                    }, 2016, 5, 1);

            dialog = datePicker;
        }

        else if(getTag().equalsIgnoreCase(KEY_TIMEPICKER_DIALOG)){

            final TimePickerDialog timePicker = new TimePickerDialog(getActivity(),
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                            mt(""+hourOfDay+" : "+minute);
                        }
                    },8,40,true);

            dialog = timePicker;
        }
        else if(getTag().equalsIgnoreCase(KEY_PROGRESS_DIALOG)){

           // ProgressDialog progressDialog = ProgressDialog.show(getActivity(),
             //       "Title","Message");

            final ProgressDialog progressDialog = new ProgressDialog(getActivity());
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setTitle("Title");
            progressDialog.setMessage("Message");

            dialog = progressDialog;
        }
        else{
            final AlertDialog.Builder builder =
                    new AlertDialog.Builder(getActivity());
            final LayoutInflater infalter = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View view = infalter.inflate(R.layout.fragment_my_dialog,null,false);
            final Button btnOkay = (Button) view.findViewById(R.id.btnOkay);

            builder.setView(view);

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
