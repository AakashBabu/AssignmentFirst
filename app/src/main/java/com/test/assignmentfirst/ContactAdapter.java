package com.test.assignmentfirst;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.MyViewHolder>{

    List<Contacts> contacts;
    Context context;

    public ContactAdapter(Context context) {
        this.context = context;
        this.contacts = new ArrayList<>();
    }

    public void loadContacts(List<Contacts> cont){
        contacts.clear();
        for(int i=0;i<cont.size();i++){
            Contacts myCont = cont.get(i);
            if(myCont.getName().trim().length() != 0 || myCont.getNumber().trim().length() != 0)
                contacts.add(myCont);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int i) {

        holder.txt_title.setText(contacts.get(i).getName());
        holder.txt_number.setText(contacts.get(i).getNumber());

        holder.ll_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,DetailActivity.class);
                intent.putExtra("data",contacts.get(i));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_title, txt_number;
        ImageView img_profile;
        LinearLayout ll_click;

        public MyViewHolder(View view) {
            super(view);
            ll_click = (LinearLayout) view.findViewById(R.id.ll_click);
            txt_title = (TextView) view.findViewById(R.id.txt_title);
            img_profile = (ImageView) view.findViewById(R.id.img_profile);
            txt_number = (TextView) view.findViewById(R.id.txt_number);
        }
    }
}
