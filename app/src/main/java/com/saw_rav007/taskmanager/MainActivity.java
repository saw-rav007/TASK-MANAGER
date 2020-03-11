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

public class MainActivity extends AppCompatActivity {

    private TextView signup_txt;

    private EditText email;
    private EditText pass;
    private Button login;
    private FirebaseAuth mauth;
    private ProgressDialog mdialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email=findViewById(R.id.email_login);
        pass=findViewById(R.id.password_login);
        login=findViewById(R.id.login_btn);
        mauth=FirebaseAuth.getInstance();
        mdialog=new ProgressDialog(this);
        if(mauth.getCurrentUser()!=null)
        {
            startActivity(new Intent(getApplicationContext(),HomeActivity.class));
        }
        signup_txt=findViewById(R.id.signup_txt);

        signup_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),RegistrationActivity.class));
            }
        });
          
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String memail=email.getText().toString().trim();
                String mpass=pass.getText().toString().trim();
                if(TextUtils.isEmpty(memail))
                {
                    email.setError("Required Field!");
                    return;
                }
                if(TextUtils.isEmpty(mpass))
                {
                    pass.setError("Required Field!");
                    return;
                }
                mdialog.setMessage("Logging In");
                mdialog.show();
                mauth.signInWithEmailAndPassword(memail,mpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(getApplicationContext(), "Log in Successful", Toast.LENGTH_SHORT).show();
                            login.setEnabled(false);
                            startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                            mdialog.dismiss();

                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "Check Log In credentials!", Toast.LENGTH_SHORT).show();
                            mdialog.dismiss();
                        }
                    }
                });
            }
        });

    }
}
