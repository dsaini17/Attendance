package com.example.devesh.attendance;

import android.app.*;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * Created by devesh on 23/10/16.
 */
public class Dialog_Show extends DialogFragment {



    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        TextView tv1,tv2,tv3;
        String s1,s2,s3;

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View convertView = inflater.inflate(R.layout.show,null);

        tv1 = (TextView) convertView.findViewById(R.id.subject_code);
        tv2 = (TextView) convertView.findViewById(R.id.subject_teacher);
        tv3 = (TextView) convertView.findViewById(R.id.subject_past);

        s1 = getArguments().getString("code");
        s2 = getArguments().getString("teacher");
        s3 = getArguments().getString("past");

        tv1.setText(s1);
        tv2.setText(s2);
        if(s3.isEmpty())
            tv3.setText("No Record");
        else
            tv3.setText(s3);


        builder.setView(convertView).setCancelable(true);

        return builder.create();
    }



}

