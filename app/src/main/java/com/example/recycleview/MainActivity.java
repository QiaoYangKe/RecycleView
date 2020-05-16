package com.example.recycleview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.module.Task;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    GeneralAdapter generalAdapter;
    //SwipeRefreshLayout mRefreshLayout;
    LinearLayoutManager linearLayoutManager;
    Button button;
    EditText editText;
    List<Task> tasks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        button = findViewById(R.id.button2);
        editText = findViewById(R.id.editText2);
        this.initData();
        linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
 // andoridx
//        mRefreshLayout = findViewById(R.id.recyclerView);
//        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
//            public void onRefresh() {
//                //我在List最前面加入一条数据
//                Task task = new Task();
//                task.setId("aaa");
//                task.setTaskName("上拉除了");
//                tasks = new ArrayList<>();
//                tasks.add(task);
//                //数据重新加载完成后，提示数据发生改变，并且设置现在不在刷新
//                generalAdapter.notifyDataSetChanged();
//                mRefreshLayout.setRefreshing(false);
//            }
//        });
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        generalAdapter=new GeneralAdapter(this,tasks);
        recyclerView.setAdapter(generalAdapter);
//        recyclerView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
//            @Override
//            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//                menu.add(0,0,0,"开工");
//                menu.add(0,0,0,"完工");
//            }
//        });
        recyclerView.addOnItemTouchListener(new MyItemTouchListener(recyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
                TextView textView = vh.itemView.findViewById(R.id.textView1);
                startActivity(new Intent(MainActivity.this,SelectPicPopupWindow.class).putExtra("ph",textView.getText().toString()));
            }
        });
        recyclerView.addOnScrollListener(new EndLessOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                loadMoreData();
            }
        });

    }
    public void initData() {
        for (int i = 0; i<10; i++) {
            Task task = new Task();
            task.setTaskName("测试数据" + i + "*******");
            tasks.add(task);
        }
    }
    public void loadMoreData() {
       tasks.addAll(generalAdapter.searchData());
       generalAdapter.notifyDataSetChanged();
    }
}
