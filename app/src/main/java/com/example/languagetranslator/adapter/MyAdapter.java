package com.example.languagetranslator.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.languagetranslator.R;
import com.example.languagetranslator.db.entity.Model;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Model> models;
    private OnItemClickListener listener;

    public MyAdapter(Context context, ArrayList<Model> models) {
        this.context = context;
        this.models = models;
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView word;
        public TextView meaning;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.word = itemView.findViewById(R.id.word);
            this.meaning = itemView.findViewById(R.id.meaning);
        }
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_container, parent, false);
        return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Model model = models.get(position);
        holder.word.setText(model.getWord());
        holder.meaning.setText(model.getMeaning());

        try{
            listener = (OnItemClickListener) context;
        }catch (ClassCastException e){
            throw  new ClassCastException(toString()+" Implement the method");
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Model model1 = models.get(position);
                listener.onItemClick(model1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public interface OnItemClickListener{

        public void onItemClick(Model model);
    }

}
