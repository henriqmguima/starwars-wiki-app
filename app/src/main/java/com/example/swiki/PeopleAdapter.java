package com.example.swiki;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.ViewHolder> {

    private List<People> peopleList;
    private Context context;

    public PeopleAdapter(Context context, List<People> peopleList) {
        this.context = context;
        this.peopleList = peopleList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_people, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        People person = peopleList.get(position);
        holder.textPersonName.setText(person.getName());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailPActivity.class);
            intent.putExtra("CHARACTER_ID", person.getUid());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return peopleList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textPersonName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textPersonName = itemView.findViewById(R.id.textPersonName);
        }
    }
}
