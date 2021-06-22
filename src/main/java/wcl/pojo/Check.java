package wcl.pojo;

/**
 * check为false代表校验通过
 */
public class Check {
    private boolean check;
    private String msg;

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Check() {
        this.check = false;
    }
}
