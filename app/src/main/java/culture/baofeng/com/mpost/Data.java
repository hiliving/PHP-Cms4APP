package culture.baofeng.com.mpost;

import java.util.List;

/**
 * Created by huangyong on 2017/12/26.
 */

public class Data {

    /**
     * code : 200
     * message : 信息插入成功
     * data : []
     */

    private int code;
    private String message;
    private List<?> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
