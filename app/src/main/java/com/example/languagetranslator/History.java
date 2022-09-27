package com.example.languagetranslator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.TextView;

import com.example.languagetranslator.adapter.MyAdapter;
import com.example.languagetranslator.db.HistoryDatabase;
import com.example.languagetranslator.db.entity.Model;

import java.util.ArrayList;
import java.util.Objects;

public class History extends AppCompatActivity implements MyAdapter.OnItemClickListener{

    private HistoryDatabase historyDatabase;
    private final ArrayList<Model> modelArrayList = new ArrayList<>();
    private MyAdapter adapter;
    private Model refModel;
    private TextView word, meaning;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        //1-Setting toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("History");

        //Creating history database reference
        historyDatabase = Room.databaseBuilder(
                getApplicationContext(),
                HistoryDatabase.class,
                "historyDB").allowMainThreadQueries().build();

        modelArrayList.addAll(historyDatabase.getModelDao().getAllModel());
        adapter = new MyAdapter(this, modelArrayList);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void openDialog(){
        LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
        View view = inflater.inflate(R.layout.dialog_layout, null);

        word = view.findViewById(R.id.word);
        meaning = view.findViewById(R.id.meaning);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setCancelable(false);
        builder.setView(view);
        builder.setTitle("Stored");

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                builder.setCancelable(true);
            }
        }).setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(DialogInterface dialog, int which) {
                historyDatabase.getModelDao().deleteWordMeaning(refModel);
                modelArrayList.remove(refModel);
                adapter.notifyDataSetChanged();
            }
        });

        AlertDialog finalAlertdialog = builder.create();
        finalAlertdialog.show();
    }

    @Override
    public void onItemClick(Model model) {
        refModel = model;
        openDialog();
        word.setText(model.getWord());
        meaning.setText(model.getMeaning());
    }
}