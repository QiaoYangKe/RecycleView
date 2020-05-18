package com.example.recycleview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.module.Equipment;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("Registered")
public class ProducingActivity extends Activity {
    Spinner spinner;
    Button button;
    EditText tvPc,tvGx,tvGzzx,tvBz,tvBzz;
    AlertDialog.Builder builder;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.producting_view);
        Bundle extras = this.getIntent().getExtras();
        spinner = findViewById(R.id.sp_sb);
        button = findViewById(R.id.bt_product);
        tvPc = findViewById(R.id.et_pch);
        tvGx = findViewById(R.id.et_gx);
        tvGzzx = findViewById(R.id.et_gzzx);
        tvBz = findViewById(R.id.et_bz);
        tvBzz = findViewById(R.id.et_bzz);
        builder = new AlertDialog.Builder(this);
        if(extras.getString("id") != null) {
            id = extras.getString("id");
            this.getProductInfo(id);
        }
        List<Equipment> equipments = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Equipment equipment = new Equipment();
            equipment.setId(i + "");
            equipment.setName("设备" + i);
            equipments.add(equipment);
        }
        ArrayAdapter<Equipment> adapter = new ArrayAdapter<Equipment>(ProducingActivity.this,android.R.layout.simple_list_item_1,equipments);
        spinner.setAdapter(adapter);
        spinner.setSelection(2,true);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setIcon(android.R.drawable.ic_dialog_info);
                builder.setTitle("温馨提示");
                builder.setMessage("确定要继续吗");
                builder.setCancelable(true);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Equipment equipment = (Equipment) spinner.getSelectedItem();
                        Toast.makeText(ProducingActivity.this,equipment.getName() + "开工成功",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ProducingActivity.this,MainActivity.class));
                    }
                });
                builder.create().show();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    public void getProductInfo(String id) {
        tvPc.setText("测试：" + id + "***********");
        tvGx.setText("测试：" + id + "***********");
        tvGzzx.setText("测试：" + id + "***********");
        tvBz.setText("测试：" + id + "***********");
        tvBzz.setText("测试：" + id + "***********");
        spinner.setSelection(1,true);
    }
}
