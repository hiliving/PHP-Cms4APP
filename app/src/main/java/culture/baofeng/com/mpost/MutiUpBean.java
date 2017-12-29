package culture.baofeng.com.mpost;

import java.util.List;

/**
 * Created by huangyong on 2017/12/29.
 */

public class MutiUpBean {

    /**
     * code : 200
     * message : 上传成功
     * data : {"path":["http://192.168.85.236/upload/5a45f56a4b74b.png","http://192.168.85.236/upload/5a45f56a4bf1c.png"]}
     */

    private int code;
    private String message;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<String> path;

        public List<String> getPath() {
            return path;
        }

        public void setPath(List<String> path) {
            this.path = path;
        }
    }
}
