package cc.raven.util;

import java.util.ArrayList;

/**
 * Created by fenghou on 2017/11/29.
 */
public class Result {
    private int status;
    private String message;
    private Object data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        if (this.message == null) {
            this.message = "";
        }
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        if (this.data == null) {
            this.data = "";
        }
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Result(int status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
