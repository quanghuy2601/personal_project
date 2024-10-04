package com.dusty.personal_project.Models;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class ResModel<T> implements Serializable {

    private T data;

    private int code;

    public static <V> ResModel<V> ok(V data) {
        return new ResModel<>(200, data);
    }

    public ResModel() {
        this.data = null;
    }

    public ResModel(int code, T oData) {
        this.data = oData;
        this.code = code;
    }
}
