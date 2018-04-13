package com.sl.railbuddy;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Transaction extends AppCompatActivity
{
String a,b,c,d,e;
TextView name,source,destination,date,tname;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        source=(TextView)findViewById(R.id.textView9);
        name=(TextView)findViewById(R.id.textView16);
        destination=(TextView)findViewById(R.id.textView10);
        date=(TextView)findViewById(R.id.textView14);
        tname=(TextView)findViewById(R.id.textView11);



        SharedPreferences bb = getSharedPreferences("BOOKING", 0);
        a = bb.getString("NAME", "");
       b = bb.getString("SOURCE", "");
       c = bb.getString("DEST", "");
        d= bb.getString("DATE", "");
        e= bb.getString("TNAME", "");

        name.setText(a);
        source.setText(b);
        destination.setText(c);
        date.setText(d);
        tname.setText(e);
        }
}
