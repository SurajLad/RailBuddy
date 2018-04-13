package com.sl.railbuddy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TestActivity extends AppCompatActivity implements View.OnClickListener
{
    EditText fname,email,password,contactno,time;
    Button signup;
    TextView Btnoldlogin;

    private FirebaseDatabase mDatabase;
    private DatabaseReference mDbRef;
    Button btnlogin;

    List<Stations> userlist=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testdata);

        mDatabase=FirebaseDatabase.getInstance();
        mDbRef=mDatabase.getReference();
        initFirebase();

        fname=(EditText)findViewById(R.id.input_nameF);

        email=(EditText)findViewById(R.id.input_email);
        contactno=(EditText)findViewById(R.id.input_contactno);

        signup=(Button) findViewById(R.id.BtnGoregister);

        signup.setOnClickListener(this);

        AddData();

    }
    private void initFirebase()
    {
        FirebaseApp.initializeApp(this);
    }

    private void AddUserToFirebaseDB()
    {
        Stations st=new Stations(fname.getText().toString(),email.getText().toString(),contactno.getText().toString());
        mDbRef.child("Stations").child(st.getSloc()).setValue(st);
        Toast.makeText(this, "Data Added", Toast.LENGTH_SHORT).show();
    }
    private void AddData()
    {
        mDbRef.child("Stations").addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                if(userlist.size()>0)
                    userlist.clear();
                for(DataSnapshot postsnapshot:dataSnapshot.getChildren())
                {
                    Stations users=postsnapshot.getValue(Stations.class);
                    userlist.add(users);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError)
            {
            }
        });
    }

    @Override
    public void onClick(View view)
    {
        if(view.getId()==R.id.BtnGoregister)
        {
            AddUserToFirebaseDB();
        }
    }
}
