package com.sl.railbuddy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener
{

    private FirebaseAuth auth;
    TextView btnregister,btnForgotPass;
    EditText useridlogin,passwordlogin;
    Button btnlogin;
    ConstraintLayout activity_login;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth= FirebaseAuth.getInstance();

        if(auth.getCurrentUser()!=null)
            startActivity(new Intent(LoginActivity.this,MainActivity.class
            ));


        btnregister=(TextView) findViewById(R.id.BtnRegister);
        btnForgotPass=(TextView)findViewById(R.id.BtnForgotpass);
        btnlogin=(Button)findViewById(R.id.BtnLogin);

        useridlogin=(EditText)findViewById(R.id.useridlogin);
        passwordlogin=(EditText)findViewById(R.id.passwordlogin);
        activity_login=(ConstraintLayout)findViewById(R.id.activity_login);


        btnregister.setOnClickListener(this);
        btnlogin.setOnClickListener(this);
        btnForgotPass.setOnClickListener(this);
    }
    public void onClick(View v)
    {
        if(v.getId()==R.id.BtnForgotpass)
        {
            startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            finish();
        }
        if(v.getId()==R.id.BtnRegister)
        {
            startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            finish();
        }
        else if(v.getId()==R.id.BtnLogin)
        {
            String  usercheck=useridlogin.getText().toString();
            String  passcheck=passwordlogin.getText().toString();

            if(usercheck.equalsIgnoreCase(""))
            {
                useridlogin.setError("Please Enter Username");
            }
            else if(passcheck.equalsIgnoreCase(""))
            {
                passwordlogin.setError("Please Enter Password");
            }
            else
            {
                loginuser(useridlogin.getText().toString(),passwordlogin.getText().toString());
            }

        }
    }

    private void loginuser(String email, final String password)
    {
        final ProgressDialog progressDialog = ProgressDialog.show(LoginActivity.this, "Please wait...", "Proccessing...", true);

        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                progressDialog.dismiss();
                if(!task.isSuccessful())
                {
                    if(password.length()<6)
                    {
                        Snackbar errorSnack=Snackbar.make(activity_login,"Password Length Must Be More Than 6 Characters",Snackbar.LENGTH_SHORT);
                        errorSnack.show();
                    }
                    else
                    {
                        Snackbar errorSnack=Snackbar.make(activity_login,"Error : "+task.getException().getMessage(),Snackbar.LENGTH_SHORT);
                        errorSnack.show();
                    }
                }
                else
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
            }
        });
    }
}
