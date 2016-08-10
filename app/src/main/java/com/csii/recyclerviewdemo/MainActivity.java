package com.csii.recyclerviewdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Context mContext = this;
    private Button main_activity_vertical_list_view_btn, main_activity_honrizontal_list_view_btn, main_activity_grid_view_btn,main_activity_strag_grid_view_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        initView();
        addEvent();

    }

    private void initView(){

        main_activity_vertical_list_view_btn = (Button)findViewById(R.id.main_activity_vertical_list_view_btn);
        main_activity_honrizontal_list_view_btn = (Button)findViewById(R.id.main_activity_honrizontal_list_view_btn);
        main_activity_grid_view_btn = (Button)findViewById(R.id.main_activity_grid_view_btn);
        main_activity_strag_grid_view_btn = (Button)findViewById(R.id.main_activity_strag_grid_view_btn);
    }

    private void addEvent(){

        main_activity_vertical_list_view_btn.setOnClickListener(this);
        main_activity_honrizontal_list_view_btn.setOnClickListener(this);
        main_activity_grid_view_btn.setOnClickListener(this);
        main_activity_strag_grid_view_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.main_activity_vertical_list_view_btn:

                startActivity(new Intent(mContext,VerticalListView.class));

                break;
            case R.id.main_activity_honrizontal_list_view_btn:

                startActivity(new Intent(mContext,HorizontalListView.class));


                break;
            case R.id.main_activity_grid_view_btn:

                startActivity(new Intent(mContext,GridViewRecycler.class));

                break;
            case R.id.main_activity_strag_grid_view_btn:
                startActivity(new Intent(mContext,StragGridView.class));
            break;
        }

    }
}
