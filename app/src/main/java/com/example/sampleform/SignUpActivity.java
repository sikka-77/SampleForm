package com.example.sampleform;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    TextView mSignin;
    private EditText mNameEditText;
    private EditText mEmailEditText;
    private EditText mMobileEditText;
    private EditText mLocationEditText;
    private EditText mPasswordEditText;
    private Button mSignUpButton;
    private FirebaseAuth firebaseSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mNameEditText=(EditText)findViewById(R.id.getNameEditText);
        mEmailEditText=(EditText)findViewById(R.id.getEmailEditText);
        mMobileEditText=(EditText)findViewById(R.id.getMobileEditText);
        mLocationEditText=(EditText)findViewById(R.id.getLocationEditText);
        mPasswordEditText=(EditText)findViewById(R.id.getPasswordEditText);
        mSignUpButton=(Button)findViewById(R.id.SignUpButton);
        firebaseSignup=FirebaseAuth.getInstance();

        mSignin=(TextView)findViewById(R.id.Signin);
        mSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(SignUpActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
        mSignUpButton.setOnClickListener(this);
    }

    private void registerNewUser()
    {
        String email=mEmailEditText.getText().toString().trim();
        String password=mPasswordEditText.getText().toString().trim();

        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Please set a password", Toast.LENGTH_SHORT).show();
            return;
        }

//creating new user

        firebaseSignup.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(SignUpActivity.this, "account created", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(SignUpActivity.this, "Try Again" , Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onClick(View view)
    {
        if(view==mSignUpButton)
        {
            registerNewUser();
        }
    }

}
