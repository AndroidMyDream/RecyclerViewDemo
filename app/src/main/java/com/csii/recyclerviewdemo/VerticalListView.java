package com.csii.recyclerviewdemo;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class VerticalListView extends AppCompatActivity {

    private Context mContext = this;
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_vertical_list_view);
        initView();
        initData();

    }

    private void initData(){

        mAdapter = new RecyclerViewAdapter();

        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        Drawable shapeDrawable = mContext.getResources().getDrawable(R.drawable.shape_vertical_list_view_divider);

        mRecyclerView.addItemDecoration(new DividerItemDecoration(DividerItemDecoration.VERTICAL_LIST,shapeDrawable));


    }

    private void initView(){

        mRecyclerView = (RecyclerView) findViewById(R.id.vertical_list_view_recycler_view);
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                if(newState == RecyclerView.SCROLL_STATE_IDLE){

                    Log.i("DD","SCROLL_STATE_IDLE");

                }else if(newState == RecyclerView.SCROLL_STATE_DRAGGING){

                    Log.i("DD","SCROLL_STATE_DRAGGING");

                }else if(newState == RecyclerView.SCROLL_STATE_SETTLING){

                    Log.i("DD","SCROLL_STATE_SETTLING");
                }
            }
        });

    }



    class RecyclerViewViewHolder extends RecyclerView.ViewHolder{


        private TextView textView;

        public RecyclerViewViewHolder(TextView view){
            super(view);
            this.textView = view;
        }

    }

    class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewViewHolder>{


        @Override
        public RecyclerViewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            TextView tv = new TextView(mContext);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            tv.setGravity(Gravity.CENTER);
            tv.setPadding(10,10,10,10);
            tv.setTextSize(15);
            tv.setLayoutParams(layoutParams);

            return new RecyclerViewViewHolder(tv);
        }

        @Override
        public void onBindViewHolder(RecyclerViewViewHolder holder, final int position) {

            holder.textView.setText("recycler view");
            if(position % 2 == 0){

                holder.textView.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.selector_vertical_list_view_item_bg));

            }else{

                holder.textView.setBackgroundColor(mContext.getResources().getColor(R.color.colorAccent));
            }

            holder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(mContext,position+"",Toast.LENGTH_SHORT).show();
                }
            });

        }

        @Override
        public int getItemCount() {
            return 30;
        }

    }

}
