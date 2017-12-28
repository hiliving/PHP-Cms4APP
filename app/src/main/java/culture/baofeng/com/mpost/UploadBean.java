package culture.baofeng.com.mpost;

/**
 * Created by huangyong on 2017/12/26.
 */

public class UploadBean {

    /**
     * status : 1
     * msg : 提交成功
     * success : 1
     * failure : 0
     */

    private int status;
    private String msg;
    private int success;
    private int failure;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public int getFailure() {
        return failure;
    }

    public void setFailure(int failure) {
        this.failure = failure;
    }
}
