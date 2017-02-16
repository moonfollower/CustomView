package com.example.moon.customviewdemo.activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.moon.customviewdemo.R;
import com.example.moon.customviewdemo.view.SearchView;

public class SearchViewActivity extends AppCompatActivity {

    private SearchView searchView;
    private Button btnStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view);
        init();
    }

    //初始化
    private void init(){
        searchView = (SearchView) findViewById(R.id.searchView);
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(searchView.isOperation()){
                    return;
                }
                searchView.beginSearch();
            }
        });


        btnStop = (Button) findViewById(R.id.btn_stop_search);
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchView.stopSearch();
            }
        });
    }


}
