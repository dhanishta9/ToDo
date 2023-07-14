package com.planner.todo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class PlanActivity extends AppCompatActivity {

    ListView lv_plan_task;
    Button btn_plan_remove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);

        lv_plan_task = findViewById(R.id.lv_plan_task);
        btn_plan_remove = findViewById(R.id.btn_plan_remove);

        DataBaseHelper dataBaseHelper = new DataBaseHelper(PlanActivity.this);

        List<String> plan_task = dataBaseHelper.getPlan();

        ArrayAdapter<String> doArrayAdapter = new ArrayAdapter<>(PlanActivity.this, android.R.layout.simple_list_item_1, plan_task);
        lv_plan_task.setAdapter(doArrayAdapter);

        btn_plan_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog removeAlert = new AlertDialog.Builder(PlanActivity.this)
                        .setTitle("Confirm Deletion")
                        .setMessage("Do you want to delete this list")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                boolean result = dataBaseHelper.deletePlan();

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