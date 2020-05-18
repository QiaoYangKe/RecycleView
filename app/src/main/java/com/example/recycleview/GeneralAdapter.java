package com.example.recycleview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.module.Task;

import java.util.ArrayList;
import java.util.List;

public class GeneralAdapter extends RecyclerView.Adapter<GeneralAdapter.MyViewHolder> {
    //当前上下文对象
    private Context context;
    //RecyclerView填充Item数据的List对象
    private List<Task> datas;
    private String query;

    private int currentPage = 1;
    private int pageSize = 10;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void searchData(String query) {
        List<Task> taskList = new ArrayList<>();
        int page = currentPage;
        for (int i = (page-1)*pageSize ; i<(page*pageSize);i++){
            Task task = new Task();
            task.setId(String.valueOf(i));
            task.setTaskName("测试数据" + i + "*******");
            taskList.add(task);
        }
        this.datas.addAll(taskList);
        notifyDataSetChanged();
        currentPage++;
    }

    public GeneralAdapter(Context context,List<Task> datas){
        this.context = context;
        this.datas = datas;
    }

    //创建ViewHolder
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.racycler_item,parent,false);
        return new MyViewHolder(view);
    }

    //绑定数据
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Task task = datas.get(position);
        if(task == null) {
            holder.textView1.setText("loading");
        } else {
            holder.textView1.setText("批号:" + task.getTaskName());
            holder.textView2.setText("工序名称:" + task.getTaskName() + "卷号:" + task.getTaskName());
            holder.textView3.setText("成品合金:" + task.getTaskName() + "成品状态:" + task.getTaskName());
            holder.textView4.setText("工序参数:" + task.getTaskName());
            holder.textView4.setText("工序参数:" + task.getTaskName());
            holder.textView9.setText(task.getId());
        }
    }


    //返回Item的数量
    @Override
    public int getItemCount() {
        return datas.size();
    }

    //继承RecyclerView.ViewHolder抽象类的自定义ViewHolder
    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView1,textView2,textView3,textView4,textView9;

        MyViewHolder(View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.textView1);
            textView2 = itemView.findViewById(R.id.textView2);
            textView3 = itemView.findViewById(R.id.textView3);
            textView4 = itemView.findViewById(R.id.textView4);
            textView9 = itemView.findViewById(R.id.textView9);
        }
    }
}