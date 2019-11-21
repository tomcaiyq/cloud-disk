package com.tomcai.cloud.common;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class ResultBean<T> implements Serializable {
    public static final int SUCCESS = 200;
    public static final int FAIL = 500;

    private String msg = "success";
    private int code = SUCCESS;
    private T data;

    public ResultBean(T data) {
        this.data = data;
    }

    public ResultBean(String failMsg) {
        this.msg = failMsg;
        this.code = FAIL;
    }
}
