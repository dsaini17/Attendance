package com.example.devesh.attendance;

import android.app.*;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.*;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by devesh on 5/1/17.
 */

public class Delete_Dialog extends DialogFragment {

    public static final String TAG = "Delete Dialog";

    public interface DialogListener{
        public void Positive_Click(Bundle args);
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

        final ArrayList<String> back = new ArrayList<>();

        final CharSequence[] sub = getArguments().getCharSequenceArray("Subject_Delete");
        Log.d("Delete dialog","Seq size : "+sub.length);



        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Delete Subjects").setMultiChoiceItems(sub,null, new DialogInterface.OnMultiChoiceClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                if(b){
                    Log.d(TAG,i+" "+sub[i]);
                    back.add((String) sub[i]);
                    Log.d(TAG,back.size()+"");
                }
                else{
                    back.remove(sub[i]);
                    Log.d(TAG,back.size()+"");
                }
            }
        }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                    if(dialogListener!=null){
                        Bundle args = new Bundle();
                        args.putStringArrayList("To_Delete",back);
                        dialogListener.Positive_Click(args);
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
