package com.example.recycleview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SelectPicPopupWindow extends Activity implements OnClickListener{

    private Button btn_take_photo, btn_pick_photo, btn_cancel;
    private LinearLayout layout;
    private TextView textView;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.racycler_bottom_tool);
        textView = findViewById(R.id.textView);
        Bundle extras = this.getIntent().getExtras();
        if(extras.getString("ph") != null) {
            textView.setText(extras.getString("ph").toString());
        }
        if(extras.getString("id") != null) {
            id = extras.getString("id").toString();
        }
        btn_take_photo = this.findViewById(R.id.btn_product);
        btn_pick_photo = this.findViewById(R.id.btn_complete);
        btn_cancel = this.findViewById(R.id.btn_cancel);

        layout=(LinearLayout)findViewById(R.id.pop_layout);

        //添加选择窗口范围监听可以优先获取触点，即不再执行onTouchEvent()函数，点击其他地方时执行onTouchEvent()函数销毁Activity
        layout.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(getApplicationContext(), "提示：点击窗口外部关闭窗口！",
                        Toast.LENGTH_SHORT).show();
            }
        });
        //添加按钮监听
        btn_cancel.setOnClickListener(this);
        btn_pick_photo.setOnClickListener(this);
        btn_take_photo.setOnClickListener(this);
    }

    //实现onTouchEvent触屏函数但点击屏幕时销毁本Activity
    @Override
    public boolean onTouchEvent(MotionEvent event){
        finish();
        return true;
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_product:
                startActivity(new Intent(SelectPicPopupWindow.this,ProducingActivity.class).putExtra("id",id));
                break;
            case R.id.btn_complete:
                startActivity(new Intent(SelectPicPopupWindow.this,CompleteActivity.class).putExtra("id",id));
                break;
            case R.id.btn_cancel:
                break;
            default:
                break;
        }
        finish();
    }

}