package com.example.practice_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

public class add_employee extends AppCompatActivity implements View.OnClickListener {

    TextView txtid,txtname,txtsalary;
    EditText editid,editname,editsalary;
    Button button;
    DBhandler dBhandler = new DBhandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);

        txtid = findViewById(R.id.txtid);
        txtname = findViewById(R.id.txtname);
        txtsalary = findViewById(R.id.txtsalary);

        editid = findViewById(R.id.editid);
        editname = findViewById(R.id.editname);
        editsalary = findViewById(R.id.editsalary);

        button = findViewById(R.id.button);

        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if(!txtid.getText().toString().isEmpty() && !txtname.getText().toString().isEmpty() && !txtsalary.getText().toString().isEmpty()){
            EmployeeEntity em;
            em = new EmployeeEntity();
            em.setEid(Integer.parseInt(editid.getText().toString()));
            em.setEname(editname.getText().toString());
            em.setSalary(Integer.parseInt(editsalary.getText().toString()));
            em.setCreated_At(new Date());
            dBhandler.AddEmployee(em);
            Intent i = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
            finish();
        }

    }
}