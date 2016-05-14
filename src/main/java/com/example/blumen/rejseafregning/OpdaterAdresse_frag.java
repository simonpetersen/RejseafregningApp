package com.example.blumen.rejseafregning;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;


public class OpdaterAdresse_frag extends Fragment implements View.OnClickListener{
    EditText PostnrInput, VejInput, HusInput, EtageInput, DoorInput;
    Button AnnullerBtn, GemBtn;
    //public static String res;
    ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View rod = i.inflate(R.layout.opdater_adresse_frag, container, false);

        PostnrInput = (EditText) rod.findViewById(R.id.PostnrInput);
        VejInput = (EditText) rod.findViewById(R.id.VejInput);
        HusInput = (EditText) rod.findViewById(R.id.HusInput);
        EtageInput = (EditText) rod.findViewById(R.id.EtageInput);
        DoorInput = (EditText) rod.findViewById(R.id.DoorInput);

        AnnullerBtn = (Button) rod.findViewById(R.id.AnnullerBtn);
        GemBtn = (Button) rod.findViewById(R.id.GemBtn);

        AnnullerBtn.setOnClickListener(this);
        GemBtn.setOnClickListener(this);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Gemmer..");

        return rod;
    }

    @Override
    public void onClick(View v) {
        if(v == AnnullerBtn)
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragment_felt, new HovedMenu_frag())
                    .addToBackStack(null)
                    .commit();
        if(v == GemBtn){
            progressDialog.show();
            final String parameters = Logik.instans.getBrugernavn() + "/" + Logik.instans.getAdgangskode() + "/" + PostnrInput.getText().toString()
                    + "/" + VejInput.getText().toString() + "/" + HusInput.getText().toString() + "/"
                    + EtageInput.getText().toString() + "/" + DoorInput.getText().toString();
            new AsyncTask<Object, Object, String>() {
                @Override
                protected String doInBackground(Object... params) {
                    String result = null;
                    try {
                        result = Logik.instans.putUrl("PUT", "opdater/"+parameters, parameters);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return result;
                }

                @Override
                protected void onPostExecute(String result) {
                    gemAdresse(result);
                }
            }.execute();
        }
    }

    private void gemAdresse(String result) {
        System.out.println("Result = "+result);
        if (result != null && result.substring(0, 13).equals("Medarbejderen")) {
            progressDialog.hide();
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("Adressen er gemt!").setTitle("Gemt");
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragment_felt, new AdresseOpdateret_frag())
                    .addToBackStack(null)
                    .commit();
        } else Toast.makeText(getActivity(), "Der skete en fejl", Toast.LENGTH_LONG);
    }

}
