package com.atzzazz.store.util;

import java.io.Serializable;

/**
 * リクエストに対する統一のレスパンス
 */
public class JsonResult<E> implements Serializable {
    private Integer code;
    private String message;
    private E data;

    public JsonResult() {
    }

    public JsonResult(Integer code) {
        this.code = code;
    }

    public JsonResult(Integer code, E data) {
        this.code = code;
        this.data = data;
    }

    public JsonResult(Throwable e) {
        this.message = e.getMessage();
    }
}
