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

public class DelegateActivity extends AppCompatActivity {

    ListView lv_delegate_task;
    Button btn_delegate_remove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delegate);


        lv_delegate_task = findViewById(R.id.lv_delegate_task);
        btn_delegate_remove = findViewById(R.id.btn_delegate_remove);

        DataBaseHelper dataBaseHelper = new DataBaseHelper(DelegateActivity.this);

        List<String> delegate_task = dataBaseHelper.getDelegate();

        ArrayAdapter<String> doArrayAdapter = new ArrayAdapter<>(DelegateActivity.this, android.R.layout.simple_list_item_1, delegate_task);
        lv_delegate_task.setAdapter(doArrayAdapter);

        btn_delegate_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog removeAlert = new AlertDialog.Builder(DelegateActivity.this)
                        .setTitle("Confirm Deletion")
                        .setMessage("Do you want to delete this list")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                boolean result = dataBaseHelper.deleteDelegate();

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