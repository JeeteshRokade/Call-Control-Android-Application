package com.example.felix.callcontrol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AddToBlocklist extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_blocklist);



    }


    public void gotocallog (View view)
    {
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("position",1); //for example
        startActivity(intent);
    }
    public void gotocontacts (View view)
    {
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("position",3); //for example
        startActivity(intent);
    }
}
