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


public class DBInfo_frag extends Fragment implements View.OnClickListener{

    TextView brugerCount, rejseafregningCount;
    Button TilbageBtn;
    ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View rod = i.inflate(R.layout.dbinfo_frag, container, false);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Henter..");
        progressDialog.show();

        brugerCount = (TextView) rod.findViewById(R.id.BrugerCount);
        rejseafregningCount = (TextView) rod.findViewById(R.id.RejseafregningCount);

        TilbageBtn = (Button) rod.findViewById(R.id.TilbageBtn);
        TilbageBtn.setOnClickListener(this);

        new AsyncTask() {

            @Override
            protected Object doInBackground(Object[] params) {
                String result = null;
                try {
                    result = Logik.instans.stringFromURL("info");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return result;
            }

            @Override
            protected void onPostExecute(Object result) {
                setUpUI((String) result);
            }
        }.execute();

        return rod;
    }

    private void setUpUI(String result) {
        String[] resultat = result.split("\\n");
        brugerCount.setText(resultat[0].substring(29));
        rejseafregningCount.setText(resultat[1].substring(33));
        progressDialog.hide();
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
