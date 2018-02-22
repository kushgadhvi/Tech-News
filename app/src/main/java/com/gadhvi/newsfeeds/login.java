package com.gadhvi.newsfeeds;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

public class login extends AppCompatActivity {
    private EditText e1,e2;
    private Button b1;
private FirebaseAuth mAuth;
    private DatabaseReference mdatabase;
  private   ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        e1 =(EditText)findViewById(R.id.email);
        e2=(EditText)findViewById(R.id.password);
        b1=(Button)findViewById(R.id.auth);
        final    String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        mdatabase = FirebaseDatabase.getInstance().getReference().child("Token");
        mAuth =FirebaseAuth.getInstance();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email =e1.getText().toString().trim();
                String password =e2.getText().toString().trim();

                if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password))
                {

                    mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                startActivity(new Intent(login.this,AddNews.class));
                                login.this.finish();
                                
                            }
                            else {
                                Toast.makeText(login.this,"Emai or Password incorrect",Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                }
                else {
                    Toast.makeText(login.this, "Please Enter Fields Correctly ", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
