package com.delfini.android_mastery;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class SampleRecyclerViewAdapter extends RecyclerView.Adapter<SampleRecyclerViewAdapter.ViewHolder> {
    private List<SampleRecyclerViewItems> listItems;
    private Context context;


    public SampleRecyclerViewAdapter(List<SampleRecyclerViewItems> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public SampleRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflating the dynamic layout will be using.
       View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_recycler_items, parent, false);
       return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SampleRecyclerViewAdapter.ViewHolder holder, int position) {
        final SampleRecyclerViewItems listItem = listItems.get(position);

        // Setting the value for the recycler view for each item.
        holder.txtUsername.setText(listItem.getUsername());
        holder.txtPassword.setText(listItem.getPassword());
        holder.llUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, listItem.getUsername(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
       return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // Initialize Views
        TextView txtUsername, txtPassword;
        LinearLayout  llUser;

        public ViewHolder(View itemView) {
            super(itemView);

            llUser = itemView.findViewById(R.id.userLL);

            txtUsername = itemView.findViewById(R.id.usernameTXTRV);
            txtPassword  = itemView.findViewById(R.id.passwordTXTRV);
        }
    }
}
