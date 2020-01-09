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

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView mRegisterText;
    private EditText mEmailEditText;
    private EditText mPasswordEditText;
    private Button mSignInButton;
    private FirebaseAuth firebaseSignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRegisterText=(TextView)findViewById(R.id.Register);
        mEmailEditText=(EditText)findViewById(R.id.getUserEmail);
        mPasswordEditText=(EditText)findViewById(R.id.getUserPassword);
        mSignInButton=(Button)findViewById(R.id.Signinside);
        firebaseSignin=FirebaseAuth.getInstance();

        mSignInButton.setOnClickListener(this);


        //setting the click listener

        mRegisterText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signup = new Intent(MainActivity.this,SignUpActivity.class);
                startActivity(signup);

            }
        });
    }

    private void userLogin(){
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
        firebaseSignin.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Signed In", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Try Again", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void onClick(View view){
        if(view==mSignInButton)
        {
            userLogin();
        }
    }


}
