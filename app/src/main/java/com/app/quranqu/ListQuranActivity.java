package com.app.quranqu;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.app.quranqu.Adapter.ListQuranAdapter;
import com.app.quranqu.Model.DataHolder;

import java.util.ArrayList;
import java.util.List;

public class ListQuranActivity extends AppCompatActivity {

    RecyclerView mViewList;
    private List<DataHolder> orderList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_quran);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mViewList = (RecyclerView) findViewById(R.id.recyclerView);
        mViewList.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(ListQuranActivity.this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mViewList.setLayoutManager(llm);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ListQuranAdapter mListOrderAdapter = new ListQuranAdapter(getListData(), ListQuranActivity.this);
        mViewList.setAdapter(mListOrderAdapter);
    }

    private List<DataHolder> getListData() {
        orderList.add(new DataHolder("1", "Al-Fatihah"));
        orderList.add(new DataHolder("2", "Al-Baqarah"));
        orderList.add(new DataHolder("3", "Ali Imran"));
        orderList.add(new DataHolder("4", "An Nisa"));
        orderList.add(new DataHolder("5", "An Maidah"));
        orderList.add(new DataHolder("6", "Al An'am"));
        orderList.add(new DataHolder("6", "Al A'raf"));
        return orderList;
    }

}
