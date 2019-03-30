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
    private static final String FILE_NAME = "campuscard-introduce.txt";

    @RequestMapping(value = "/introRead",method = RequestMethod.GET)
    public String introRead(){
        String res = TxtParseUtil.readFile(FILE_NAME);
        System.out.println(res);
        MsgJson msgJson = MsgJson.success(res);
        return msgJson.toJson();
    }

    @RequestMapping(value = "/introWrite",method = RequestMethod.PUT)
    public String introWrite(@RequestParam String content){
        boolean res = TxtParseUtil.writeFile(FILE_NAME, content);
        MsgJson msgJson = MsgJson.success(res);
        msgJson.setSuccess(res);
        return msgJson.toJson();
    }
}
