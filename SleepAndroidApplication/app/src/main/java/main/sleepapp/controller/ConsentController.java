package main.sleepapp.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import main.sleepapp.R;
import main.sleepapp.model.StudentModel;


public class ConsentController extends AppCompatActivity {

    private StudentModel studentModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consentview);

        studentModel = getIntent().getParcelableExtra("studentModel");

        final Button btnAccept = (Button) findViewById(R.id.acceptButton);
        final Button btnReject = (Button) findViewById(R.id.rejectButton);

        btnReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleReject();
            }
        });

        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleAccept();
            }
        });


    }

    private void handleReject() {
        String type = "consent";
        studentModel.updateModel(type,"0");
        finish();
    }

    private void handleAccept(){
        String type = "consent";
        studentModel.updateModel(type,"1");
        finish();
    }


}
