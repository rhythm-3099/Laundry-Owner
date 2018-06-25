package com.example.prhyt.laundryownernew;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class info_main extends AppCompatActivity {

    private Button update_btn;
    private TextView userName,useremail,userCost,userClothes,userBalance,userdateinfo;
    private String dataname,dataclothes,datacost,datadateinfo,databalance,dataemail;
    public String user_house;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_main);
        setID();

        Intent neo = getIntent();
        String name = neo.getStringExtra("house");
        setHouse(name);

        final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();


        databaseReference.orderByChild("username").equalTo(name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot child: dataSnapshot.getChildren()){
                    String uid = child.getKey();
                    setUID(uid);
                    //make_toast(uid);
                }

                DatabaseReference myRef = firebaseDatabase.getReference(getUID());

                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        user_info userinfo = dataSnapshot.getValue(user_info.class);
                        userName.setText(userinfo.getUsername());
                        useremail.setText(userinfo.getUseremail());
                        userdateinfo.setText(userinfo.getDate());
                        String clothes,balance,cost;
                        clothes = Integer.toString(userinfo.getClothes());
                        balance = Double.toString(userinfo.getBalance());
                        cost = Double.toString(userinfo.getCost());
                        userBalance.setText(balance);
                        userClothes.setText(clothes);
                        userCost.setText(cost);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(),databaseError.getCode(),Toast.LENGTH_SHORT).show();
                    }
                });



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),databaseError.getCode(),Toast.LENGTH_SHORT).show();
            }
        });


        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent neo = new Intent(getApplicationContext(),info_update.class);
                neo.putExtra("uid",getUID());
                neo.putExtra("house",getHouse());
                startActivity(neo);
            }
        });
    }

    public String str;

    public void setHouse(String rhythm){
        user_house = rhythm;
    }

    public String getHouse(){
        return user_house;
    }

    public void setUID(String name){
        str = name;
    }

    public String getUID(){
        return str;
    }

    public void make_toast(String toast_name){
        Toast.makeText(getApplicationContext(),toast_name,Toast.LENGTH_LONG).show();
    }

    public void setName(String name){

    }

    private void setID(){
        update_btn = findViewById(R.id.updating_btn);
        userName = findViewById(R.id.userName);
        useremail = findViewById(R.id.useremail);
        userClothes = findViewById(R.id.userClothes);
        userCost = findViewById(R.id.userCost);
        userBalance = findViewById(R.id.userBalance);
        userdateinfo = findViewById(R.id.userinfodate);
    }
}
