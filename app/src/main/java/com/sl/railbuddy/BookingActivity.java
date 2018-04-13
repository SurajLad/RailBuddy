package com.sl.railbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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

public class BookingActivity extends AppCompatActivity implements View.OnClickListener
{

    private FirebaseDatabase mDatabase;
    private DatabaseReference mDbRef;
    DatabaseReference ref2;
    List<CURAVL> userlist=new ArrayList<>();

    TextView tname;
    String source,destination;

    String tno;

    TextView t1,t2,t3,sl,s2;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        tname=(TextView)findViewById(R.id.Tname);
        source=getIntent().getStringExtra("TName");
        tname.setText(source);

        t1=(TextView)findViewById(R.id.txt1A);
        t2=(TextView)findViewById(R.id.txt2A);
        t3=(TextView)findViewById(R.id.txt3A);
        s2=(TextView)findViewById(R.id.txt2S);
        sl=(TextView)findViewById(R.id.txtSL);

        t1.setOnClickListener(this);
        t2.setOnClickListener(this);
        t3.setOnClickListener(this);
        s2.setOnClickListener(this);
        sl.setOnClickListener(this);

        String[] splited = source.split("\\s+");

        tno=splited[0];

        mDatabase=FirebaseDatabase.getInstance();
        mDbRef = FirebaseDatabase.getInstance().getReference();
     //   ref2 =mDbRef.child("AVL").child("12051");
        initFirebase();

    }
    private void initFirebase()
    {
        FirebaseApp.initializeApp(this);
    }
    @Override
    protected void onStart()
    {
        super.onStart();
        mDbRef.child("AVL").addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                if(userlist.size()>0)
                    userlist.clear();
                for(DataSnapshot postsnapshot:dataSnapshot.getChildren())
                {
                    CURAVL users=postsnapshot.getValue(CURAVL.class);
                   // String a=postsnapshot.getKey();
                    if(tno.equals(postsnapshot.getKey()))
                    {
                        t1.setText(users.onea);
                        t2.setText(users.twoa);
                        t3.setText(users.threea);
                        s2.setText(users.cc);
                        sl.setText(users.sl);

                    }
                  //  items.add(users.sloc);
                //    Toast.makeText(BookingActivity.this, " "+users.cc, Toast.LENGTH_SHORT).show();
                //    tname.setText(a);
                    //progressDialog.dismiss();
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError)
            {
            }
        });
    }

    @Override
    public void onClick(View v)
    {
        if(v.getId()==R.id.txt1A)
        {
            startActivity(new Intent(BookingActivity.this,FormTricket.class).putExtra("TNAME",source).putExtra("Class", "1A").putExtra("TotSeats",t1.getText().toString()));
        }
        if(v.getId()==R.id.txt2A)
        {
            startActivity(new Intent(BookingActivity.this,FormTricket.class).putExtra("TNAME",source).putExtra("Class", "2A").putExtra("TotSeats",t2.getText().toString()));
        }
        if(v.getId()==R.id.txt3A)
        {
            startActivity(new Intent(BookingActivity.this,FormTricket.class).putExtra("TNAME",source).putExtra("Class", "3A").putExtra("TotSeats",t3.getText().toString()));
        }
        if(v.getId()==R.id.txt2S)
        {
            startActivity(new Intent(BookingActivity.this,FormTricket.class).putExtra("TNAME",source).putExtra("Class", "2S").putExtra("TotSeats",s2.getText().toString()));
        }
        if(v.getId()==R.id.txtSL)
        {
            startActivity(new Intent(BookingActivity.this,FormTricket.class).putExtra("TNAME",source).putExtra("Class", "SL").putExtra("TotSeats",sl.getText().toString()));
        }

    }
}
