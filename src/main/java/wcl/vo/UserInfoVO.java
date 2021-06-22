package wcl.vo;

import wcl.pojo.Check;
import wcl.pojo.UserInfo;

public class UserInfoVO extends UserInfo {

    private String newPassword;
    private String verCode;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getVerCode() {
        return verCode;
    }

    public void setVerCode(String verCode) {
        this.verCode = verCode;
    }

    public Check checkForLogin(){
        Check c = new Check();
        StringBuilder sb = new StringBuilder();
        if (this.getUserId() == null || "".equals(this.getUserId())){
            c.setCheck(true);
            sb.append("登录名为空");
        }
        if (this.getUserPassword() == null || "".equals(this.getUserPassword())){
            c.setCheck(true);
            sb.append("密码为空");
        }
        /*if (this.verCode == null || "".equals(this.verCode)){
            c.setCheck(true);
            sb.append("验证码为空");
        }*/
        c.setMsg(sb.toString());
        return c;
    }

    public Check checkForRegister(){
        Check c = new Check();
        StringBuilder sb = new StringBuilder();
        if (this.getUserId() == null || "".equals(this.getUserId())){
            c.setCheck(true);
            sb.append("登录名为空");
        }
        if (this.getUserPassword() == null || "".equals(this.getUserPassword())){
            c.setCheck(true);
            sb.append("密码为空");
        }
        if (this.getUserNickname() == null || "".equals(this.getUserNickname())){
            c.setCheck(true);
            sb.append("昵称为空");
        }
        c.setMsg(sb.toString());
        return c;
    }

    public Check checkForPassword(){
        Check c = new Check();
        StringBuilder sb = new StringBuilder();
        if (this.getUserPassword() == null || "".equals(this.getUserPassword())){
            c.setCheck(true);
            sb.append("原密码为空");
        }
        if (this.newPassword == null || "".equals(this.newPassword)){
            c.setCheck(true);
            sb.append("新密码为空");
        }
        c.setMsg(sb.toString());
        return c;
    }
}
