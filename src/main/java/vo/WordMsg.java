package vo;

/**
 * @author Administrator
 */
public class WordMsg {
    /**
        传输状态 true-success false-fail
     */
    private boolean status;
    /**
        错误编码
     */
    private int errCode;
    /**
        错误消息
     */
    private String errMsg;
    /**
        错误异常
     */
    private Exception exception;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }
}
