package com.example.blumen.rejseafregning;


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
    Logik logik;
    String brugerLogin;
    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState){
        View rod = i.inflate(R.layout.login_frag, container, false);

        bruger = (EditText) rod.findViewById(R.id.BrugerInput);
        password = (EditText) rod.findViewById(R.id.PasswordInput);
        login = (Button) rod.findViewById(R.id.LoginBtn);
        progressBar = (ProgressBar) rod.findViewById(R.id.progressBar);
        progressBar.setVisibility(ProgressBar.INVISIBLE);
        login.setOnClickListener(this);

        logik = new Logik();

        return rod;
    }

    @Override
    public void onClick(View v) {
        if(v == login){
            progressBar.setVisibility(ProgressBar.VISIBLE);
            Logik.Bruger = bruger.getText().toString();
            Logik.Pass = password.getText().toString();

            new AsyncTask() {
                @Override
                protected String doInBackground(Object[] params) {
                    try {
                        brugerLogin = logik.stringFromURL(Logik.url + "opdater/" + Logik.Bruger + "/" + Logik.Pass);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if(brugerLogin.substring(0, 17).equals("Koden er korrekt.")) return Logik.Bruger;
                    return null;
                }

                @Override
                protected void onPostExecute(Object result) {
                    if (result != null) {
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
