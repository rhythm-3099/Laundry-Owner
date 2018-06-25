package com.example.prhyt.laundryownernew;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button home12,home13,home14a,home14b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setID();

        home12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent neo = new Intent(getApplicationContext(),info_main.class);
                neo.putExtra("house","12");
                startActivity(neo);
                //startActivity(new Intent(getApplicationContext(),info_main.class));
            }
        });

        home13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent neo = new Intent(getApplicationContext(),info_main.class);
                neo.putExtra("house","13");
                startActivity(neo);
                //startActivity(new Intent(getApplicationContext(),info_main.class));
            }
        });

        home14a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent neo = new Intent(getApplicationContext(),info_main.class);
                neo.putExtra("house","14-A");
                startActivity(neo);

                //startActivity(new Intent(getApplicationContext(),info_main.class));
            }
        });

        home14b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent neo = new Intent(getApplicationContext(),info_main.class);
                neo.putExtra("house","14-B");
                startActivity(neo);

                //startActivity(new Intent(getApplicationContext(),info_main.class));
            }
        });

    }

    private void setID(){
        home12 = findViewById(R.id.btn_home_12);
        home13 = findViewById(R.id.btn_home_13);
        home14a = findViewById(R.id.btn_home_14_A);
        home14b = findViewById(R.id.btn_home_14_B);
    }
}
