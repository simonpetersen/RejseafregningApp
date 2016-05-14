package com.example.blumen.rejseafregning;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;


public class OpdaterAdresse_frag extends Fragment implements View.OnClickListener{
    EditText PostnrInput, VejInput, HusInput, EtageInput, DoorInput;
    Button AnnullerBtn, GemBtn;
    Logik logik;
    public static String res;

    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View rod = i.inflate(R.layout.opdater_adresse_frag, container, false);
        logik = new Logik();


        PostnrInput = (EditText) rod.findViewById(R.id.PostnrInput);
        VejInput = (EditText) rod.findViewById(R.id.VejInput);
        HusInput = (EditText) rod.findViewById(R.id.HusInput);
        EtageInput = (EditText) rod.findViewById(R.id.EtageInput);
        DoorInput = (EditText) rod.findViewById(R.id.DoorInput);

        AnnullerBtn = (Button) rod.findViewById(R.id.AnnullerBtn);
        GemBtn = (Button) rod.findViewById(R.id.GemBtn);

        AnnullerBtn.setOnClickListener(this);
        GemBtn.setOnClickListener(this);

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
            try {
                String params = Logik.Bruger + "/" + Logik.Pass + "/" + PostnrInput.getText().toString()
                        + "/" + VejInput.getText().toString() + "/" + HusInput.getText().toString() + "/"
                        + EtageInput.getText().toString() + "/" + DoorInput.getText().toString();
                res = logik.putUrl(Logik.url + params, params);
            } catch (IOException e) {
                e.printStackTrace();
            }
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragment_felt, new AdresseOpdateret_frag())
                    .addToBackStack(null)
                    .commit();
        }
    }

}
