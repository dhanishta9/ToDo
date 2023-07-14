package com.planner.todo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;


public class TodoActivity extends AppCompatActivity implements View.OnClickListener{

     EditText et_task;
     Button btn_add, btn_cancel, btn_ok;
     CheckBox mImportant, mUrgent;
     CardView cv_do, cv_plan, cv_delegate, cv_delete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        btn_add = findViewById(R.id.btn_add);
        cv_do = findViewById(R.id.cv_do);
        cv_plan = findViewById(R.id.cv_plan);
        cv_delegate = findViewById(R.id.cv_delegate);
        cv_delete = findViewById(R.id.cv_delete);

        cv_do.setOnClickListener(this);
        cv_delete.setOnClickListener(this);
        cv_delegate.setOnClickListener(this);
        cv_plan.setOnClickListener(this);

        btn_add.setOnClickListener(new View.OnClickListener()
        {
          @Override
          public void onClick(View v) {

              final AlertDialog.Builder alert = new AlertDialog.Builder(TodoActivity.this);
              View m_view = getLayoutInflater().inflate(R.layout.add_task_dialog, null);

              et_task = m_view.findViewById(R.id.et_task);
              btn_cancel = m_view.findViewById(R.id.btn_cancel);
              btn_ok = m_view.findViewById(R.id.btn_ok);
              mImportant = m_view.findViewById(R.id.important);
              mUrgent = m_view.findViewById(R.id.urgent);


              alert.setView(m_view);

              final AlertDialog alertDialog = alert.create();
              alertDialog.setCanceledOnTouchOutside(false);

              alertDialog.show();

              btn_ok.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {

                      if (et_task.length()==0)
                      {
                          et_task.setError("Enter Task or Press Cancel");
                      }
                      else {
                          TaskModel taskModel = new TaskModel(-1, et_task.getText().toString(), mImportant.isChecked(), mUrgent.isChecked());
                          Toast.makeText(TodoActivity.this, taskModel.toString(), Toast.LENGTH_SHORT).show();
                          DataBaseHelper dataBaseHelper = new DataBaseHelper(TodoActivity.this);
                          boolean success = dataBaseHelper.addOne(taskModel);
                          Toast.makeText(TodoActivity.this, "Success " + success, Toast.LENGTH_SHORT).show();

                          alertDialog.dismiss();

                      }
                  }
              });

              btn_cancel.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      alertDialog.dismiss();
                  }

              });

          }
        });

    }


    @Override
    public void onClick(View v) {
        Intent intent;
        switch(v.getId()) {
            case R.id.cv_do:
                intent = new Intent(this, DoActivity.class);
                startActivity(intent);
                break;
            case R.id.cv_plan:
                intent = new Intent(this, PlanActivity.class);
                startActivity(intent);
                break;
            case R.id.cv_delegate:
                intent = new Intent(this, DelegateActivity.class);
                startActivity(intent);
                break;
            case R.id.cv_delete:
                intent = new Intent(this, DeleteActivity.class);
                startActivity(intent);
                break;

        }
    }
}













