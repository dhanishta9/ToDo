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

public class DeleteActivity extends AppCompatActivity {

    ListView lv_delete_task;
    Button btn_delete_remove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        lv_delete_task = findViewById(R.id.lv_delete_task);
        btn_delete_remove = findViewById(R.id.btn_delete_remove);

        DataBaseHelper dataBaseHelper = new DataBaseHelper(DeleteActivity.this);

        List<String> delete_task = dataBaseHelper.getDelete();

        ArrayAdapter<String> doArrayAdapter = new ArrayAdapter<>(DeleteActivity.this, android.R.layout.simple_list_item_1, delete_task);
        lv_delete_task.setAdapter(doArrayAdapter);

        btn_delete_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog removeAlert = new AlertDialog.Builder(DeleteActivity.this)
                        .setTitle("Confirm Deletion")
                        .setMessage("Do you want to delete this list")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                boolean result = dataBaseHelper.deleteDelete();

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