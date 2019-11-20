package com.app.advancecurd.dialogboxs;

import android.app.Dialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;

import com.app.advancecurd.R;

public class InputDialogBox extends AppCompatDialogFragment {
    SetDeleteOperation inputDataDialogListener;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final LayoutInflater layoutInflater = requireActivity().getLayoutInflater();
        final View view = layoutInflater.inflate(R.layout.activity_message_delete, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setView(view)
                .setTitle("Delete User")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        inputDataDialogListener.getData(true);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

        return builder.create();
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            inputDataDialogListener=(SetDeleteOperation) context;
        }catch (Exception e){
        }
    }



}
