package com.example.android.alcintermediateapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.android.alcintermediateapp.adapter.DataAdapter;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DataAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        adapter = new DataAdapter();
        recyclerView.setAdapter(adapter);
    }
}
