package com.planner.todo;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class DoActivity extends AppCompatActivity {

    ListView lv_do_task;
    Button btn_remove;
    int length;
    int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do);


        lv_do_task = findViewById(R.id.lv_do_task);
        btn_remove = findViewById(R.id.btn_remove);

        DataBaseHelper dataBaseHelper = new DataBaseHelper(DoActivity.this);

        List<String> do_task = dataBaseHelper.getDo();

        ArrayAdapter<String> doArrayAdapter = new ArrayAdapter<>(DoActivity.this, android.R.layout.simple_list_item_1, do_task);
        lv_do_task.setAdapter(doArrayAdapter);


        btn_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog removeAlert = new AlertDialog.Builder(DoActivity.this)
                        .setTitle("Confirm Deletion")
                        .setMessage("Do you want to delete this list")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                boolean result = dataBaseHelper.deleteDO();

                            }
                        })
                        .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create();
                removeAlert.show();


            }
        });



    }

}