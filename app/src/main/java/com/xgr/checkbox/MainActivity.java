package com.xgr.checkbox;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

public class MainActivity extends Activity implements View.OnClickListener {

    private ImageView ivShow;
    private ImageView ivDismiss;
    private RelativeLayout rlBottom;
    private Button btnSkip;
    private PopupWindow mPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setViews();
        setListeners();

    }

    private void setViews() {
        ivShow = (ImageView) findViewById(R.id.iv_show);
        ivDismiss = (ImageView) findViewById(R.id.iv_dismiss);
        rlBottom= (RelativeLayout) findViewById(R.id.rl_bottom);
        btnSkip= (Button) findViewById(R.id.btn_skip_this);
    }

    private void setListeners() {
        ivShow.setOnClickListener(this);
        ivDismiss.setOnClickListener(this);
        btnSkip.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_show:
                if(mPopupWindow!=null){
                    return;
                }
                mPopupWindow=new CustomPopuWindow(this,R.layout.popuwindow_repair,0);
                mPopupWindow.showAtLocation(rlBottom,0,0, Gravity.NO_GRAVITY);
                break;
            case R.id.iv_dismiss:
                if(mPopupWindow!=null){
                    mPopupWindow.dismiss();
                    mPopupWindow=null;
                    return;
                }
                break;
            case R.id.btn_skip_this:
                Intent intent=new Intent(this,MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

}
