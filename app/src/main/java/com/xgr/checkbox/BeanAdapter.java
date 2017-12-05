package com.xgr.checkbox;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xgr on 2017/11/25.
 */

public class BeanAdapter extends RecyclerView.Adapter<BeanAdapter.ViewHolder> {

    private Context context;
    private List<JavaBean> list;
    private List<String> checklist;

    public List<String> getChecklist() {
        return checklist;
    }

    public void setChecklist(List<String> checklist) {
        this.checklist = checklist;
    }

    public BeanAdapter(Context context, List<JavaBean> list) {
        this.context = context;
        this.list = list;
        checklist=new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_repair, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        JavaBean bean = list.get(position);
        holder.checkBox.setText(bean.getName());
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    checklist.add(holder.checkBox.getText().toString());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;

        public ViewHolder(View view) {
            super(view);
            checkBox = (CheckBox) view.findViewById(R.id.checkbox_problem);
        }
    }
}
