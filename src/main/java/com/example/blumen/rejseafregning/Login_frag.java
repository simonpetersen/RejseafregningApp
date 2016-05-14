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
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;

public class Login_frag extends Fragment implements View.OnClickListener{
    EditText bruger, password;
    Button login;
    String brugerLogin;
    ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState){
        View rod = i.inflate(R.layout.login_frag, container, false);

        bruger = (EditText) rod.findViewById(R.id.BrugerInput);
        bruger.setText("DIMO");
        password = (EditText) rod.findViewById(R.id.PasswordInput);
        password.setText("123");
        login = (Button) rod.findViewById(R.id.LoginBtn);
        login.setOnClickListener(this);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Logger ind..");

        return rod;
    }

    @Override
    public void onClick(View v) {
        if(v == login){
            progressDialog.show();
            Logik.instans.setBrugernavn(bruger.getText().toString());
            Logik.instans.setAdgangskode(password.getText().toString());

            new AsyncTask() {
                @Override
                protected String doInBackground(Object[] params) {
                    try {
                        brugerLogin = Logik.instans.stringFromURL("opdater/" + Logik.instans.getBrugernavn() + "/" +
                                Logik.instans.getAdgangskode());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if(brugerLogin.substring(0, 17).equals("Koden er korrekt.")) return "Success!";
                    return null;
                }

                @Override
                protected void onPostExecute(Object result) {
                    if (result != null) {
                        progressDialog.hide();
                        getFragmentManager().beginTransaction()
                                .replace(R.id.fragment_felt, new HovedMenu_frag())
                                .addToBackStack(null)
                                .commit();
                    } else {
                        Toast.makeText(getActivity(), "Ugyldigt login!", Toast.LENGTH_SHORT);
                    }
                }
            }.execute();
        }
    }
}
