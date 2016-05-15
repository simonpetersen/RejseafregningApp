package com.example.blumen.rejseafregning;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by Simon on 14/05/16.
 */
public class OpretOpgave_frag extends Fragment implements View.OnClickListener {

    ProgressDialog progressDialog;
    EditText opgText;
    Button tilbageButton, gemButton;

    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View rod = i.inflate(R.layout.opret_opgave_frag, container, false);

        opgText = (EditText) rod.findViewById(R.id.opgaveText);
        tilbageButton = (Button) rod.findViewById(R.id.tilbageButton);
        tilbageButton.setOnClickListener(this);
        gemButton = (Button) rod.findViewById(R.id.gemButton);
        gemButton.setOnClickListener(this);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Gemmer..");

        return rod;
    }

    @Override
    public void onClick(View v) {
        if(v == tilbageButton)
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragment_felt, new HovedMenu_frag())
                    .addToBackStack(null)
                    .commit();
        else if (v == gemButton) {
            progressDialog.show();
            final String opgaveNavn = opgText.getText().toString();
            new AsyncTask<Object, Object, String>() {
                @Override
                protected String doInBackground(Object... params) {
                    String result = null;
                    try {
                        Logik.instans.putUrl("POST", "opgave/"+opgaveNavn, opgaveNavn);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return result;
                }

                @Override
                protected void onPostExecute(String result) {
                    gemOpgave(result);
                }
            }.execute();
        }
    }

    private void gemOpgave(String result) {
        progressDialog.hide();
        if (result != null && result.equals("Opgave er oprettet"))
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragment_felt, new HovedMenu_frag())
                    .addToBackStack(null)
                    .commit();
        else
            Toast.makeText(getActivity(), "Der skete en fejl!", Toast.LENGTH_LONG);
    }
}
