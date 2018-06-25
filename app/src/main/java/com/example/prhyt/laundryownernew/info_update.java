package com.example.prhyt.laundryownernew;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class info_update extends AppCompatActivity {

    EditText et_clothes, et_amount;
    Button btn_update;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    int userClothes;
    double userBalance,userCost;
    String username,useremail,userdate,userpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_update);
        setID();

        Intent neo = getIntent();
        String uid = neo.getStringExtra("uid");
        final String message = neo.getStringExtra("house");
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference(uid);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user_info userinfo = dataSnapshot.getValue(user_info.class);
                userClothes = userinfo.getClothes();
                userCost = userinfo.getCost();
                userBalance = userinfo.getBalance();
                username = userinfo.getUsername();
                useremail = userinfo.getUseremail();
                userdate = userinfo.getDate();
                userpass = userinfo.getUserpassword();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),databaseError.getCode(),Toast.LENGTH_SHORT).show();
            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String clothes_inp, amount_inp;
                clothes_inp = et_clothes.getText().toString();
                amount_inp = et_amount.getText().toString();
                if(clothes_inp.isEmpty() && amount_inp.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Both the fields cannot be empty!",Toast.LENGTH_LONG).show();
                }

                else{
                    int newClothes;
                    double newBalance,newCost;
                    if(check_for_amount(amount_inp) && check_for_clothes(clothes_inp)){

                           newBalance = Double.parseDouble(amount_inp);
                           newClothes = Integer.parseInt(clothes_inp);
                           userBalance+=newBalance;
                           userClothes-=newClothes;
                           if(userCost==0){
                               ;
                           }else if(userCost>userBalance){  // Cost is more than the balance
                               userCost-=userBalance;
                               userBalance=0;
                           }else if(userCost<userBalance){  // Balance is more than the cost
                               userBalance-=userCost;
                               userCost=0;
                           }else if(userCost==userBalance){ // Cost and balance are the same
                               userCost=userBalance=0;
                           }

                    }else if(clothes_inp.isEmpty() && !amount_inp.isEmpty()){
                        newBalance = Double.parseDouble(amount_inp);
                        userBalance+=newBalance;
                        if(userCost==0){
                            ;
                        }else if(userCost>userBalance){  // Cost is more than the balance
                            userCost-=userBalance;
                            userBalance=0;
                        }else if(userCost<userBalance){  // Balance is more than the cost
                            userBalance-=userCost;
                            userCost=0;
                        }else if(userCost==userBalance){ // Cost and balance are the same
                            userCost=userBalance=0;
                        }

                    }else if(!clothes_inp.isEmpty() && amount_inp.isEmpty()){
                        newClothes = Integer.parseInt(clothes_inp);
                        userClothes-=newClothes;
                        if(userCost==0){
                            ;
                        }else if(userCost>userBalance){  // Cost is more than the balance
                            userCost-=userBalance;
                            userBalance=0;
                        }else if(userCost<userBalance){  // Balance is more than the cost
                            userBalance-=userCost;
                            userCost=0;
                        }else if(userCost==userBalance){ // Cost and balance are the same
                            userCost=userBalance=0;
                        }

                    }


                    user_info userinfo = new user_info(username,useremail,userpass,userClothes,userBalance,userCost,userdate);
                    databaseReference.setValue(userinfo);
                    Intent neo = new Intent(getApplicationContext(),info_main.class);
                    neo.putExtra("house",message);
                    startActivity(neo);

                }

            }
        });

    }

    private boolean check_for_amount(String name){
        boolean ans=true;
        if(name.isEmpty()){
            ans=true;
        }
        else{
            for(int i=0;i<name.length();i++){
                if(name.charAt(i)>57 || name.charAt(i)<48){
                    Toast.makeText(getApplicationContext(),"Enter only decimal digits for amount!",Toast.LENGTH_LONG).show();
                    ans=false;
                    break;
                }
            }
        }
        return ans;
    }

    private boolean check_for_clothes(String name){
        boolean ans=true;
        if(name.isEmpty()){
            //Make clothes given=0;
            ans=true;
        }
        else {
            for (int i = 0; i < name.length(); i++){
                if(name.charAt(i)>57 || name.charAt(i)<48){
                    Toast.makeText(getApplicationContext(),"Enter only the decimal digits!",Toast.LENGTH_LONG).show();
                    ans=false;
                    break;
                }
            }
        }
        return ans;
    }

    private void setID(){
        et_clothes = findViewById(R.id.et_update_clothes);
        et_amount = findViewById(R.id.et_amount_update);
        btn_update = findViewById(R.id.btn_userinfo_update);
    }
}
