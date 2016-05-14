package com.example.blumen.rejseafregning;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class AdresseOpdateret_frag extends Fragment implements View.OnClickListener{

    TextView GammelAdresse, NyAdresse;
    Button MenuBtn;

    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View rod =  i.inflate(R.layout.adresse_opdateret_frag, container, false);

        GammelAdresse = (TextView) rod.findViewById(R.id.GammelAdresse);
        NyAdresse = (TextView) rod.findViewById(R.id.NyAdresse);

        MenuBtn = (Button) rod.findViewById(R.id.HovedMenuBtn);
        MenuBtn.setOnClickListener(this);

        String[] strs = OpdaterAdresse_frag.res.split("\\n");

        GammelAdresse.setText(strs[1].substring(23, strs[1].length()));
        NyAdresse.setText(strs[3].substring(16, strs[3].length()));

        return rod;
    }

    @Override
    public void onClick(View v) {
        if(v == MenuBtn)
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragment_felt, new HovedMenu_frag())
                    .addToBackStack(null)
                    .commit();
    }

}
