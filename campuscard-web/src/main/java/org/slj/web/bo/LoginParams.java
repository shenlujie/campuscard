package org.slj.web.bo;

import java.io.Serializable;

/**
 * @Description: 登录参数
 * @create: 2019/5/14
 * @Author: SLJ
 */
public class LoginParams implements Serializable {

    private String stNum;

    private String stPassword;

    public String getStNum() {
        return stNum;
    }

    public void setStNum(String stNum) {
        this.stNum = stNum;
    }

    public String getStPassword() {
        return stPassword;
    }

    public void setStPassword(String stPassword) {
        this.stPassword = stPassword;
    }
}
