package com.example.blumen.rejseafregning;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;


public class AdresseOpdateret_frag extends Fragment implements View.OnClickListener{

    TextView gammelAdresse, nyAdresse, beskedView;
    Button menuBtn;

    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        String[] strs = Logik.instans.getResponse().split("\\\n");
        View rod;
        if (strs.length == 1) {
            rod = i.inflate(R.layout.ny_adresse_frag, container, false);
            beskedView = (TextView) rod.findViewById(R.id.beskedView);
            beskedView.setText(Logik.instans.getResponse());
        } else {
            rod =  i.inflate(R.layout.adresse_opdateret_frag, container, false);
            gammelAdresse = (TextView) rod.findViewById(R.id.GammelAdresse);
            nyAdresse = (TextView) rod.findViewById(R.id.NyAdresse);
            gammelAdresse.setText(strs[1].substring(23));
            nyAdresse.setText(strs[3].substring(16));
        }

        menuBtn = (Button) rod.findViewById(R.id.HovedMenuBtn);
        menuBtn.setOnClickListener(this);

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
