package com.example.blumen.rejseafregning;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class AdresseOpdateret_frag extends Fragment implements View.OnClickListener{

    TextView gammelAdresse, nyAdresse;
    Button menuBtn;

    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View rod =  i.inflate(R.layout.adresse_opdateret_frag, container, false);

        gammelAdresse = (TextView) rod.findViewById(R.id.GammelAdresse);
        nyAdresse = (TextView) rod.findViewById(R.id.NyAdresse);

        menuBtn = (Button) rod.findViewById(R.id.HovedMenuBtn);
        menuBtn.setOnClickListener(this);

        String[] strs = Logik.instans.getResponse().split("\\\n");

        gammelAdresse.setText(strs[1].substring(23));
        nyAdresse.setText(strs[3].substring(16));

        return rod;
    }

    @Override
    public void onClick(View v) {
        if(v == menuBtn)
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragment_felt, new HovedMenu_frag())
                    .addToBackStack(null)
                    .commit();
    }

}
