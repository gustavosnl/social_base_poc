package com.glima.socialbasepoc.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.glima.socialbasepoc.R;
import com.glima.socialbasepoc.databinding.ActivityMainBinding;

import static android.support.v7.widget.LinearLayoutManager.VERTICAL;

public class MainActivity extends BaseActivity {

    RecyclerView games;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
    }

    private void init() {
        games = ((ActivityMainBinding) viewDataBinding).gamesList;
        games.setLayoutManager(new LinearLayoutManager(this, VERTICAL, false));
        games.setAdapter(new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return null;
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            }

            @Override
            public int getItemCount() {
                return 0;
            }
        });
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }
}
