package com.example.blumen.rejseafregning;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;

public class Login_frag extends Fragment implements View.OnClickListener{
    EditText bruger, password;
    Button Login;
    Logik logik;
    String brugerLogin;

    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState){
        View rod = i.inflate(R.layout.login_frag, container, false);

        bruger = (EditText) rod.findViewById(R.id.BrugerInput);
        password = (EditText) rod.findViewById(R.id.PasswordInput);
        Login = (Button) rod.findViewById(R.id.LoginBtn);

        Login.setOnClickListener(this);

        logik = new Logik();

        return rod;
    }

    @Override
    public void onClick(View v) {
        if(v == Login){
            Logik.Bruger = bruger.getText().toString();
            Logik.Pass = password.getText().toString();

            try {
                brugerLogin = logik.stringFromURL(Logik.url + "opdater/" + Logik.Bruger + "/" + Logik.Pass);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(brugerLogin.equals("Koden er korrekt.")){
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_felt, new HovedMenu_frag())
                        .addToBackStack(null)
                        .commit();
            }
        }
    }
}
