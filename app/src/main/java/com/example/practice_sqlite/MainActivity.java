package com.example.practice_sqlite;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupMenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView rcv1;

    ArrayList<EmployeeEntity> emps = new ArrayList<>();

    DBhandler dh = new DBhandler(this);

    ImageView imgadd;

    Intent intent;

    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rcv1 = findViewById(R.id.rcv1);

        try {
            emps = dh.getAllEmployee();

            Log.e("==>ds", "onCreate: " + emps.size());
            EmployeeAdapter adapter;
            adapter = new EmployeeAdapter(this, emps);
            rcv1.setLayoutManager(new LinearLayoutManager(this));
            rcv1.setAdapter(adapter);


        } catch (ParseException e) {
            e.printStackTrace();


        }

        imgadd = findViewById(R.id.imgadd);

        imgadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PopupMenu popup = new PopupMenu(MainActivity.this, view);
                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.addStudent) {
                            intent = new Intent(MainActivity.this, add_employee.class);
                            startActivity(intent);
                        }

                        return true;
                    }
                });

                popup.show(); //showing popup menu
            }
        });
    }

    private void showExitDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.layout_custom_dialog);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(true);


        dialog.findViewById(R.id.btn_no).setOnClickListener(view -> {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        });

        dialog.findViewById(R.id.btn_yes).setOnClickListener(view -> {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            finishAffinity();
        });

        dialog.show();
    }

    @Override
    public void onBackPressed() {
        showExitDialog();
    }
}