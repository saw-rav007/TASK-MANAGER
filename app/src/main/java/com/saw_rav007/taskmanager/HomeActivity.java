package com.saw_rav007.taskmanager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private FloatingActionButton fabBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar=findViewById(R.id.toolbar_home);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("MY TASKS");
        fabBtn=findViewById(R.id.floating_action_button);

        fabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder myDialog=new AlertDialog.Builder(HomeActivity.this);
                // Get the layout inflater

                LayoutInflater layoutInflater=LayoutInflater.from(HomeActivity.this);
                View view=layoutInflater.inflate(R.layout.custominputfield,null);
                myDialog.setView(view);
                AlertDialog dialog=myDialog.create();
                final EditText title = findViewById(R.id.edt_title);
                final EditText note  = findViewById(R.id.edt_note);
                Button btnsave = findViewById(R.id.saveBtn);


                btnsave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String mTitle = title.getText().toString().trim();
                        String mNote  = note.getText().toString().trim();

                        if(TextUtils.isEmpty(mTitle)) {
                            title.setError("Enter the Title here!");
                            return;
                        }
                        if (TextUtils.isEmpty(mNote)) {
                            note.setError("Enter the Note here!");
                            return;
                        }
                    }
                });



                dialog.show();

            }
        });

    }
}
