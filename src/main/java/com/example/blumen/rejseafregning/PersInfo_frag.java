package com.example.blumen.rejseafregning;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;


public class PersInfo_frag extends Fragment implements View.OnClickListener{
    Button TilbageBtn;
    TextView NavnText, AfdelingText, EmailText, AdresseText;
    String Result;
    Logik logik;

    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View rod = i.inflate(R.layout.pers_info_frag, container, false);
        logik = new Logik();

        NavnText = (TextView) rod.findViewById(R.id.NavnText);
        AfdelingText = (TextView) rod.findViewById(R.id.AfdelingText);
        EmailText = (TextView) rod.findViewById(R.id.EmailText);
        AdresseText = (TextView) rod.findViewById(R.id.AdresseText);

        TilbageBtn = (Button) rod.findViewById(R.id.TilbagePersBtn);
        TilbageBtn.setOnClickListener(this);

        try {
            Result = logik.stringFromURL(Logik.url + "info/" + Logik.Bruger + "/" + Logik.Pass);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] strs = Result.split("\\n");
        NavnText.setText(strs[1].substring(17,strs[1].length()));
        AfdelingText.setText(strs[2].substring(23, strs[2].length()));
        EmailText.setText(strs[3].substring(7, strs[3].length()));
        AdresseText.setText(strs[5] + "\n" + strs[6]);

        return rod;
    }

    @Override
    public void onClick(View v) {
        if(v == TilbageBtn)
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragment_felt, new HovedMenu_frag())
                    .addToBackStack(null)
                    .commit();
    }

}
