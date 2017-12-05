package com.xgr.checkbox;

import java.io.Serializable;

/**
 * Created by xgr on 2017/11/24.
 */

public class JavaBean implements Serializable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
