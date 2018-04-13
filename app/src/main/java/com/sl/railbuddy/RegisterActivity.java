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
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener
{

    EditText fname,email,password,contactno;
    Button signup;
    TextView Btnoldlogin;
    ConstraintLayout activity_register;

    private FirebaseAuth auth;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDbRef;

    String usertType=" ";
    List<Users> userlist=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fname=(EditText)findViewById(R.id.input_nameF);

        email=(EditText)findViewById(R.id.input_email);
        contactno=(EditText)findViewById(R.id.input_contactno);
        password=(EditText)findViewById(R.id.input_password);

        signup=(Button)findViewById(R.id.BtnGoregister);
        Btnoldlogin=(TextView) findViewById(R.id.BtnoldLogin);

        activity_register=(ConstraintLayout)findViewById(R.id.activity_register);

        Btnoldlogin.setOnClickListener(this);
        signup.setOnClickListener(this);

        //Init Firebase
        auth=FirebaseAuth.getInstance();

        mDatabase=FirebaseDatabase.getInstance();
        mDbRef=mDatabase.getReference();

        initFirebase();
        AddData();
    }
    private void AddData()
    {
        mDbRef.child("Users").addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                if(userlist.size()>0)
                    userlist.clear();
                for(DataSnapshot postsnapshot:dataSnapshot.getChildren())
                {
                    Users users=postsnapshot.getValue(Users.class);
                    userlist.add(users);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError)
            {

            }
        });
    }
    private void initFirebase()
    {
        FirebaseApp.initializeApp(this);
    }
    @Override
    public void onClick(View v)
    {
        if(v.getId()==R.id.BtnoldLogin)
        {
            startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            finish();
        }
        else if(v.getId()==R.id.BtnGoregister)
        {
            String  fnamecheck=fname.getText().toString();
            String  emailcheck=email.getText().toString();
            String  passcheck=password.getText().toString();
            String  phonecheck=contactno.getText().toString();

            int phonecheckDigit=phonecheck.length();

            if(fnamecheck.equalsIgnoreCase("")  )
            {
                fname.setHint("Please Enter First Name");
                fname.setError("Required");
            }
            if(emailcheck.equalsIgnoreCase(""))
            {
                email.setHint("Please Enter Email-ID");
                email.setError("Required");
            }
            if(passcheck.equalsIgnoreCase(""))
            {
                password.setHint("Please Enter Password");
                password.setError("Required");
            }
            else if(phonecheck.equalsIgnoreCase(""))
            {
                contactno.setHint("Please Enter Contact No");
                contactno.setError("Required");
            }
            else
            {
               RegisterUser(email.getText().toString(),password.getText().toString() );
            }

        }
    }

    private void RegisterUser( String email1,  String password1)
    {
        final ProgressDialog progressDialog = ProgressDialog.show(RegisterActivity.this, "Please wait...", "Processing...", true);
        auth.createUserWithEmailAndPassword(email1,password1).addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                progressDialog.dismiss();
                if(!task.isSuccessful())
                {
                    Snackbar errorSnack = Snackbar.make(activity_register, "Error : "+task.getException().getMessage(), Snackbar.LENGTH_SHORT);
                    errorSnack.show();
                }
                else
                {
                    AddUserToFirebaseDB();
                    Snackbar s1 = Snackbar.make(activity_register, "Registration Successful Now You Can Login", Snackbar.LENGTH_SHORT);
                    startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                    s1.show();
                }
            }

        });
    }
    private void AddUserToFirebaseDB()
    {
        Users users=new Users(UUID.randomUUID().toString(),fname.getText().toString(),email.getText().toString(),contactno.getText().toString(),password.getText().toString());
        mDbRef.child("Users").child(users.getID()).setValue(users);
        clearEditText();
    }

    private void clearEditText()
    {
        fname.setText("");
        email.setText("");
        contactno.setText("");
        password.setText("");
    }
}
