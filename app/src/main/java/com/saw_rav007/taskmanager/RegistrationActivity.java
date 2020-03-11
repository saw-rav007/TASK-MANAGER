package com.saw_rav007.taskmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {


    private EditText email;
    private EditText pass;
    private Button btnreg;


    private TextView login_txt;
    private FirebaseAuth mauth;

    private ProgressDialog mdialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        mauth=FirebaseAuth.getInstance();
        mdialog=new ProgressDialog(this);

        email=findViewById(R.id.email_reg);
        pass=findViewById(R.id.password_reg);
        btnreg=findViewById(R.id.reg_btn);
        login_txt=findViewById(R.id.login_txt);

        login_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String memail=email.getText().toString().trim();
                String mpass=pass.getText().toString().trim();

                if(TextUtils.isEmpty(memail)){
                    email.setError("Required Field!");
                    return;
                }
                if(TextUtils.isEmpty(mpass)){
                    pass.setError("Required Field!");
                    return;
                }

                mdialog.setMessage("Signing Up");
                mdialog.show();

                mauth.createUserWithEmailAndPassword(memail,mpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            mdialog.dismiss();
                        }

                        else{
                            Toast.makeText(getApplicationContext(), "Problem", Toast.LENGTH_SHORT).show();
                            mdialog.dismiss();
                        }
                    }
                });
            }
        });




    }
}
