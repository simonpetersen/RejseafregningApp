package com.example.blumen.rejseafregning;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragmenter);

        if(savedInstanceState == null){
            Fragment fragment = new Login_frag();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_felt, fragment)
                    .commit();
        }

        setTitle("Rejseafregning");

    }
}
