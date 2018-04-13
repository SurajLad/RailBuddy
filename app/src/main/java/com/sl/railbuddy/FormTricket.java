package com.sl.railbuddy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FormTricket extends AppCompatActivity
{
    TextView bform,dojsd,name,email,phone,nop,dojF;
    String cls,tot,DT,tname,source,destination,DOJ;
    Button bookticket;
    String m,n,p;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_tricket);

        bform=(TextView)findViewById(R.id.BookingFor);
        dojsd=(TextView)findViewById(R.id.journey);
        name=(TextView)findViewById(R.id.input_nameF);
        email=(TextView)findViewById(R.id.input_email);
        phone=(TextView)findViewById(R.id.input_contactno);
        nop=(TextView)findViewById(R.id.input_not);
        dojF=(TextView)findViewById(R.id.DoJ);

        bookticket=(Button)findViewById(R.id.BtnGoTicket);



        cls=getIntent().getStringExtra("Class");
        tot=getIntent().getStringExtra("TotSeats");
        tname=getIntent().getStringExtra("TNAME");

        SharedPreferences bb = getSharedPreferences("my_prefs", 0);
         m = bb.getString("SOURCE", "");
         n = bb.getString("DEST", "");
         p = bb.getString("DOJ", "");


        bform.setText("Booking Form For "+tname);
        dojsd.setText("Reservation For "+cls+" Berth From "+m+" To "+n);
        dojF.setText(p);

        bookticket.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(FormTricket.this,Transaction.class));
                SharedPreferences prefs = getSharedPreferences("BOOKING", MODE_PRIVATE);
                SharedPreferences.Editor edit = prefs.edit();
                edit.putString("NAME", name.getText().toString() );
                edit.putString("SOURCE", m );
                edit.putString("DEST", n );
                edit.putString("DATE", p );
                edit.putString("TNAME", tname );
                edit.commit();
            }
        });
    }

}
