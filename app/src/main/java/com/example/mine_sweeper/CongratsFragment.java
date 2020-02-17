package com.example.mine_sweeper;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatDialogFragment;

public class CongratsFragment extends AppCompatDialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Create the view to show
          View v = LayoutInflater.from(getActivity()).inflate(R.layout.congratulations_layout, null);

          Button btn = v.findViewById(R.id.btnOk);
          btn.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  getActivity().finish();
              }
          });

        return new AlertDialog.Builder(getActivity()).setView(v).create();
    }
}
