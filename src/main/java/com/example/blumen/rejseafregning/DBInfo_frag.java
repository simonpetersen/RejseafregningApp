package com.example.blumen.rejseafregning;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;


public class DBInfo_frag extends Fragment implements View.OnClickListener{

    TextView brugerCount, rejseafregningCount;
    Button TilbageBtn;
    Logik logik;
    String result = "";

    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View rod = i.inflate(R.layout.dbinfo_frag, container, false);
        logik = new Logik();

        brugerCount = (TextView) rod.findViewById(R.id.EditText1);
        rejseafregningCount = (TextView) rod.findViewById(R.id.Edittext2);

        TilbageBtn = (Button) rod.findViewById(R.id.TilbageBtn);
        TilbageBtn.setOnClickListener(this);

        try {
            result = logik.stringFromURL(Logik.url + "/info");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] resu = result.split("\\n");
        brugerCount.setText(result.substring(29,30));
        rejseafregningCount.setText(result.substring(63,64));
        return rod;
    }

    @Override
    public void onClick(View v) {
        if(v == TilbageBtn){
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragment_felt, new HovedMenu_frag())
                    .addToBackStack(null)
                    .commit();
        }
    }
}
