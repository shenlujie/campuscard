package org.slj.web.utils.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slj.enums.EmCode;

import java.io.Serializable;

/**
 * @Description: 通用信息处理 实体类
 * @create: 2019/3/20
 * @Author: SLJ
 */
public class MsgJson implements Serializable{

    private static final long serialVersionUID = 3799118320834762116L;

    /**
     * 成功标记
     */
    private boolean success = false;

    /**
     * 状态代码
     */
    private Integer code = 0;

    /**
     * 请求消息
     */
    private String msg = "";

    /**
     * 数据本体
     */
    private Object obj = null;

    /**
     * 数据总数
     */
    private int count = 0;

    /**
     * 数据返回成功
     * @param data 返回的数据本体
     * @return this
     */
    public static MsgJson success(Object data) {
        MsgJson msgJson = new MsgJson();
        msgJson.setSuccess(true)
                .setCode(EmCode.SUCCESS.getCode())
                .setMsg(EmCode.SUCCESS.getMsg())
                .setObj(data);
        return msgJson;
    }

    /**
     * 请求错误
     * @param data 返回的数据本体
     * @return this
     */
    public static MsgJson badRequest(Object data) {
        MsgJson msgJson = new MsgJson();
        msgJson.setSuccess(false)
                .setCode(EmCode.BAD_REQUEST.getCode())
                .setMsg(EmCode.BAD_REQUEST.getMsg())
                .setObj(data);
        return msgJson;
    }

    /**
     * 未得到认证
     * @param data 返回的数据本体
     * @return this
     */
    public static MsgJson unauthorized(Object data) {
        MsgJson msgJson = new MsgJson();
        msgJson.setSuccess(false)
                .setCode(EmCode.UNAUTHORIZED.getCode())
                .setMsg(EmCode.UNAUTHORIZED.getMsg())
                .setObj(data);
        return msgJson;
    }

    /**
     * 访问没有权限
     * @param data 返回的数据本体
     * @return this
     */
    public static MsgJson forbidden(Object data) {
        MsgJson msgJson = new MsgJson();
        msgJson.setSuccess(false)
                .setCode(EmCode.FORBIDDEN.getCode())
                .setMsg(EmCode.FORBIDDEN.getMsg())
                .setObj(data);
        return msgJson;
    }

    /**
     * 资源不存在
     * @param data 返回的数据本体
     * @return this
     */
    public static MsgJson notFound(Object data) {
        MsgJson msgJson = new MsgJson();
        msgJson.setSuccess(false)
                .setCode(EmCode.NOT_FOUND.getCode())
                .setMsg(EmCode.NOT_FOUND.getMsg())
                .setObj(data);
        return msgJson;
    }

    /**
     * 内部错误
     * @param data 返回的数据本体
     * @return this
     */
    public static MsgJson serverError(Object data) {
        MsgJson msgJson = new MsgJson();
        msgJson.setSuccess(false)
                .setCode(EmCode.SERVER_ERROR.getCode())
                .setMsg(EmCode.SERVER_ERROR.getMsg())
                .setObj(data);
        return msgJson;
    }

    /**
     * MsgJson转化为json
     * @return MsgJson的序列化
     */
    public String toJson(){
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }
    //setter and getter

    public boolean isSuccess() {
        return success;
    }

    public MsgJson setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public MsgJson setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public MsgJson setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getObj() {
        return obj;
    }

    public MsgJson setObj(Object obj) {
        this.obj = obj;
        return this;
    }

    public int getCount() {
        return count;
    }

    public MsgJson setCount(int count) {
        this.count = count;
        return this;
    }
}
