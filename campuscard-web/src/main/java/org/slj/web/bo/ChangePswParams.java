package org.slj.web.bo;

/**
 * @Description: 修改密码参数
 * @create: 2019/5/20
 * @Author: SLJ
 */
public class ChangePswParams {

    /**
     * 学生学号
     */
    private String stNum;

    /**
     * 当前密码
     */
    private String curPsw;

    /**
     * 新密码
     */
    private String newPsw;

    /**
     * 新密码二次输入
     */
    private String reNewPsw;

    public String getStNum() {
        return stNum;
    }

    public void setStNum(String stNum) {
        this.stNum = stNum;
    }

    public String getCurPsw() {
        return curPsw;
    }

    public void setCurPsw(String curPsw) {
        this.curPsw = curPsw;
    }

    public String getNewPsw() {
        return newPsw;
    }

    public void setNewPsw(String newPsw) {
        this.newPsw = newPsw;
    }

    public String getReNewPsw() {
        return reNewPsw;
    }

    public void setReNewPsw(String reNewPsw) {
        this.reNewPsw = reNewPsw;
    }
}
