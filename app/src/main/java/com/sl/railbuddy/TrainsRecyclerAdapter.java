package com.sl.railbuddy;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

class RecylerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView Tname,Tno,Ttime;
    public ItemClickListener itemClickListener;

    public RecylerViewHolder(View itemView)
    {
        super(itemView);
        Tname=(TextView)itemView.findViewById(R.id.Tname);
        itemView.setOnClickListener(this);
    }
public void setItemClickListener(ItemClickListener itemClickListener)
{
    this.itemClickListener=itemClickListener;
}
    @Override
    public void onClick(View v)
    {
    itemClickListener.onCilck(v,getAdapterPosition());
    }
}

public class TrainsRecyclerAdapter extends RecyclerView.Adapter<RecylerViewHolder>
{
    private List<String> listData=new ArrayList<>();
    private Context context;

    public TrainsRecyclerAdapter(List<String> listData, Context context)
    {
        this.listData = listData;
        this.context = context;
    }
    @Override
    public RecylerViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View itemview=inflater.inflate(R.layout.avltrains,parent,false);

        return new RecylerViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(RecylerViewHolder holder, int position)
    {
        holder.Tname.setText(listData.get(position));
        holder.setItemClickListener(new ItemClickListener()
        {
            @Override
            public void onCilck(View view, int position)
            {
            //    Toast.makeText(context, " "+listData.get(position), Toast.LENGTH_SHORT).show();
                context.startActivity(new Intent(context,BookingActivity.class).putExtra("TName", listData.get(position)));
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return listData.size();
    }
}
