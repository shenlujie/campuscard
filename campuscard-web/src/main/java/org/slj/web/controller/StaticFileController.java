package org.slj.web.controller;

import org.slj.web.utils.json.MsgJson;
import org.slj.web.utils.parse.TxtParseUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 静态文件传输
 * @create: 2019/3/30
 * @Author: SLJ
 */
@RestController
@RequestMapping("/file")
public class StaticFileController {

    /**
     * 一卡通简介文档名
     */
    private static final String INTRO_FILE_NAME = "campuscard-introduce.txt";


    /**
     * 一卡通指南文档名
     */
    private static final String GUIDE_FILE_NAME = "campuscard-guide.txt";

    /**
     * 读取简介文档
     * @return 文档内容
     */
    @RequestMapping(value = "/introRead",method = RequestMethod.GET)
    public String introRead(){
        String res = TxtParseUtil.readFile(INTRO_FILE_NAME);
        MsgJson msgJson = MsgJson.success(res);
        return msgJson.toJson();
    }

    /**
     * 写入简介文档
     * @param content 要写入的内容
     * @return 是否成功
     */
    @RequestMapping(value = "/introWrite",method = RequestMethod.PUT)
    public String introWrite(@RequestParam String content){
        boolean res = TxtParseUtil.writeFile(INTRO_FILE_NAME, content);
        MsgJson msgJson = MsgJson.success(res);
        msgJson.setSuccess(res);
        return msgJson.toJson();
    }

    /**
     * 读取指南文档
     * @return 文档的内容
     */
    @RequestMapping(value = "/guideRead",method = RequestMethod.GET)
    public String guideRead(){
        String res = TxtParseUtil.readFile(GUIDE_FILE_NAME);
        MsgJson msgJson = MsgJson.success(res);
        return msgJson.toJson();
    }

    /**
     * 写入指南文档
     * @param content 要写入的内容
     * @return 是否成功
     */
    @RequestMapping(value = "/guideWrite",method = RequestMethod.PUT)
    public String guideWrite(@RequestParam String content){
        boolean res = TxtParseUtil.writeFile(GUIDE_FILE_NAME, content);
        MsgJson msgJson = MsgJson.success(res);
        msgJson.setSuccess(res);
        return msgJson.toJson();
    }
}
