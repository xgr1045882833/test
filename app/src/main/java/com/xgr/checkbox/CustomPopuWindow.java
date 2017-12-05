package com.xgr.checkbox;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xgr on 2017/11/25.
 */

public class CustomPopuWindow extends PopupWindow {
    private Context context;
    private View mPopuWindow;

    private RecyclerView mRecyclerView;
    private List<JavaBean> list = new ArrayList<>();
    private BeanAdapter adapter;
    private Button btnSubmit;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:

                    break;
            }
        }
    };

    public CustomPopuWindow(Context context, int resLayout, int anchorHeight) {
        super(context);
        this.context = context;
        initResLayout(resLayout);
        setPopuWindow(anchorHeight);
        initData(resLayout);
    }

    private void initResLayout(int resLayout) {
        mPopuWindow = LayoutInflater.from(context).inflate(resLayout, null);
    }

    private void setPopuWindow(int anchorHeight) {
        this.setContentView(mPopuWindow);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(1600);
        // 实例化一个ColorDrawable颜色为半透明
        //ColorDrawable dw = new ColorDrawable(00000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(null);
    }

    private void initData(int resLayout) {
        //报修弹窗布局
        if (resLayout == R.layout.popuwindow_repair) {
            initData();
            setViews();
            setAdapter();
            setListeners();
        }
    }

    private void initData() {
        for (int i = 0; i < 20; i++) {
            JavaBean bean = new JavaBean();
            bean.setName("视频卡顿");
            list.add(bean);
        }
    }

    private void setViews() {
        mRecyclerView = (RecyclerView) mPopuWindow.findViewById(R.id.recycler_view);
        btnSubmit = (Button) mPopuWindow.findViewById(R.id.btn_submit);
    }

    private void setListeners() {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitData();
            }
        });
    }

    private void submitData() {
        if(adapter.getChecklist().size()<=0){
            Toast.makeText(context,"您还未选择报修原因",Toast.LENGTH_LONG).show();
            return;
        }
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<adapter.getChecklist().size();i++){
            String name=adapter.getChecklist().get(i);
            sb.append(name+",");
        }

        String param=sb.toString().substring(0,sb.length()-1);
        //联网上传数据
        //成功后handler.send.....在activity中取消弹窗，也可直接dismiss()，但是直接dismiss()
        //并没有销毁PopupWindow实例
        this.dismiss();
    }

    private void setAdapter() {
        int number = 0;//用于指定列数，目的：让数据居中显示
        if (list.size() > 1) {
            number = 2;//当数据大于1条时，列表分两列显示
        } else {
            number = 1;//当数据小于2条时，列表为一列显示，且数据居中
        }
        GridLayoutManager manager = new GridLayoutManager(context, number);
        mRecyclerView.setLayoutManager(manager);
        adapter = new BeanAdapter(context, list);
        mRecyclerView.setAdapter(adapter);
    }

}
