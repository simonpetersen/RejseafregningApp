package com.example.blumen.rejseafregning;


import android.app.ProgressDialog;
import android.os.AsyncTask;
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
    ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View rod = i.inflate(R.layout.pers_info_frag, container, false);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Henter..");
        progressDialog.show();

        NavnText = (TextView) rod.findViewById(R.id.NavnText);
        AfdelingText = (TextView) rod.findViewById(R.id.AfdelingText);
        EmailText = (TextView) rod.findViewById(R.id.EmailText);
        AdresseText = (TextView) rod.findViewById(R.id.AdresseText);

        TilbageBtn = (Button) rod.findViewById(R.id.TilbagePersBtn);
        TilbageBtn.setOnClickListener(this);

        new AsyncTask<Object, Object, String>() {

            @Override
            protected String doInBackground(Object[] params) {
                String result = null;
                try {
                    result = Logik.instans.stringFromURL("info/" + Logik.instans.getBrugernavn());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return result;
            }

            @Override
            protected void onPostExecute(String result) {
                setUpUI(result);
            }
        }.execute();

        return rod;
    }

    private void setUpUI(String result) {
        String[] info = result.split("\\n");
        NavnText.setText(info[1].substring(17,info[1].length()));
        AfdelingText.setText(info[2].substring(23, info[2].length()));
        EmailText.setText(info[3].substring(7, info[3].length()));
        if (info.length > 5)
            AdresseText.setText(info[5] + "\n" + info[6]);
        else
            AdresseText.setText(info[4]);
        progressDialog.hide();
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
