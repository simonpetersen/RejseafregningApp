package com.example.blumen.rejseafregning;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class HovedMenu_frag extends Fragment implements View.OnClickListener{

    Button DBInfoBtn, PersInfoBtn, AddressBtn, OpgaveBtn, LogudBtn;

    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState){
        View rod = i.inflate(R.layout.hoved_menu_frag, container, false);


        DBInfoBtn = (Button) rod.findViewById(R.id.DBInfoBtn);
        PersInfoBtn = (Button) rod.findViewById(R.id.PersInfoBtn);
        AddressBtn = (Button) rod.findViewById(R.id.AdresseBtn);
        OpgaveBtn = (Button) rod.findViewById(R.id.OpgaveBtn);
        LogudBtn = (Button) rod.findViewById(R.id.LogudBtn);

        DBInfoBtn.setOnClickListener(this);
        PersInfoBtn.setOnClickListener(this);
        AddressBtn.setOnClickListener(this);
        OpgaveBtn.setOnClickListener(this);
        LogudBtn.setOnClickListener(this);



        return rod;
    }

    @Override
    public void onClick(View v) {
        if(v == DBInfoBtn)
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragment_felt, new DBInfo_frag())
                    .addToBackStack(null)
                    .commit();
        if(v == PersInfoBtn)
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragment_felt, new PersInfo_frag())
                    .addToBackStack(null)
                    .commit();
        if(v == AddressBtn)
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragment_felt, new OpdaterAdresse_frag())
                    .addToBackStack(null)
                    .commit();
        if (v == LogudBtn) {
            Logik.Bruger = "";
            Logik.Pass = "";
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragment_felt, new Login_frag())
                    .addToBackStack(null)
                    .commit();
        }
    }
}
