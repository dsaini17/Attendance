package com.example.devesh.attendance;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

/**
 * Created by devesh on 30/8/16.
 */
public class Dialog extends DialogFragment {

    EditText et1, et2, et3;

    public interface DialogListener {


        public void Positive_Click(Bundle args);

        public void PositiveClick(Bundle args);

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
        View view = layoutInflater.inflate(R.layout.dialog_activity, null);

        et1 = (EditText) view.findViewById(R.id.subj_code);
        et2 = (EditText) view.findViewById(R.id.subj_name);
        et3 = (EditText) view.findViewById(R.id.teacher_name);

        builder.setView(view);

        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (dialogListener != null) {
                    Bundle args = new Bundle();

                    args.putString("Subject_Code", et1.getText().toString());
                    args.putString("Subject_Name", et2.getText().toString());
                    args.putString("Teacher", et3.getText().toString());

                    dialogListener.PositiveClick(args);

                }
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).setCancelable(true);

        return builder.create();
    }
}
