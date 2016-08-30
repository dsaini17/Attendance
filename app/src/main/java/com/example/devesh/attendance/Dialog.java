package com.example.devesh.attendance;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by devesh on 30/8/16.
 */
public class Dialog extends DialogFragment {

    public interface DialogListener{
        public void PositiveClick();
        public void NegativeClick();
    }

    DialogListener dialogListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dialogListener = (DialogListener) context;
    }

    @NonNull
    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_activity,null);

        builder.setView(view);

        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).setCancelable(true);

        return builder.create();
    }
}
