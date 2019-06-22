package org.slj.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slj.domain.CampusCardLost;
import org.slj.domain.CampusCardOperation;
import org.slj.enums.EmCode;
import org.slj.service.CampusCardOperationService;
import org.slj.service.FrontUserStudentService;
import org.slj.service.SendMessageService;
import org.slj.web.utils.json.MsgJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

/**
*@Description: 校园卡业务办理
*@create: 2019/3/17
*@Author: SLJ
*/
@RestController
@RequestMapping("/campusCardOperation/")
public class CampusCardOperationController {

    /**
     * 业务处理状态
     */
    private static final String ORDER = "预约中";

    /**
     * 邮箱起始地址
     */
    private final String FROM = "18234175208@163.com";

    /**
     * 邮件标题
     */
    private final String TITLE = "校园一卡通业务进展通知";

    @Autowired
    CampusCardOperationService campusCardOperationService;

    @Autowired
    SendMessageService sendMessageService;

    @Autowired
    FrontUserStudentService frontUserStudentService;

    @RequestMapping(value = "add",method = RequestMethod.POST)
    public String add( @RequestBody CampusCardOperation campusCardOperation) {
        MsgJson msgJson;
        if (campusCardOperation.getApply().equals("0")){
            campusCardOperation.setApply("挂失");
        }else if (campusCardOperation.getApply().equals("1")){
            campusCardOperation.setApply("解挂");
        }else {
            campusCardOperation.setApply("补办");
        }
        campusCardOperation.setStatus("办理中");
        campusCardOperationService.saveModel(campusCardOperation);
        msgJson = MsgJson.success("提交成功");
        return msgJson.toJson();
    }

    @RequestMapping(value = "delete",method = RequestMethod.DELETE)
    public String delete(@RequestParam Integer id) {
	    campusCardOperationService.deleteById(id);
	    return "";
    }

    @RequestMapping(value = "update",method = RequestMethod.PUT)
    public String update(@RequestParam int id, @RequestParam String status) {
        CampusCardOperation campusCardOperation = campusCardOperationService.findById(id);
        if (ORDER.equals(status)){
            campusCardOperation.setStatus("已完成");
        }
        campusCardOperationService.update(campusCardOperation);
        MsgJson msgJson = MsgJson.success("已完成");
        return msgJson.toJson();
    }

    @RequestMapping(value = "detail",method = RequestMethod.GET)
    public String detail(@RequestParam Integer id) {
        MsgJson msgJson;
        CampusCardOperation operation = campusCardOperationService.findById(id);
        if (null == operation){
            msgJson = MsgJson.not_found("无");
        }else {
            msgJson = MsgJson.success(operation);
        }
        return msgJson.toJson();
    }

    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<CampusCardOperation> list = campusCardOperationService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        MsgJson msgJson = new MsgJson();
        msgJson.setSuccess(true)
                .setCode(EmCode.SUCCESS.getCode())
                .setMsg(EmCode.SUCCESS.getMsg())
                .setCount(list.size())
                .setObj(list);
        return msgJson.toJson();
    }

    @RequestMapping(value = "listById",method = RequestMethod.GET)
    public String listById(@RequestParam(defaultValue = "0") Integer page,
                           @RequestParam(defaultValue = "0") Integer size,
                           @RequestParam Integer stId) {
        PageHelper.startPage(page, size);
        tk.mybatis.mapper.entity.Condition condition = new Condition(CampusCardOperation.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("stId", stId);
        List<CampusCardOperation> list = campusCardOperationService.findByCondition(condition);
        PageInfo pageInfo = new PageInfo(list);
        MsgJson msgJson = new MsgJson();
        msgJson.setSuccess(true)
                .setCode(EmCode.SUCCESS.getCode())
                .setMsg(EmCode.SUCCESS.getMsg())
                .setCount(list.size())
                .setObj(list);
        return msgJson.toJson();
    }

    @RequestMapping(value = "/sendMessage",method = RequestMethod.POST)
    public String sendMessage(@RequestParam int id,@RequestParam String apply){
        int stId = campusCardOperationService.findById(id).getStId();
        String to = frontUserStudentService.findById(stId).getStEmail();
        String content = "您申请办理的业务[" + apply + "],已经处理完毕，如是补办，请到一卡通电子中心领取.";
        boolean res = sendMessageService.sendMessage(FROM, to, TITLE, content);
        MsgJson msgJson = MsgJson.success(res);
        return msgJson.toJson();
    }

    @RequestMapping(value = "upLoadPic",method = RequestMethod.POST)
    public String upLoadPic(@RequestParam MultipartFile file, @RequestParam String curUser) {
        MsgJson msgJson;
        if (file.isEmpty()){
            msgJson = new MsgJson();
            msgJson.setSuccess(false)
                    .setCode(200)
                    .setMsg("文件为空，上传失败");
            return msgJson.toJson();
        }
        String oldFileName = file.getOriginalFilename();
        String subFileName = oldFileName.substring(oldFileName.lastIndexOf("."));
        Random random = new Random(10000);
        String fileName = "campuscard-" + curUser + random.nextInt() + subFileName;
        String path = "A:\\IntelliJ IDEA\\workspace_backup\\campuscard\\campuscard-web\\src\\main\\resources\\static\\userIDPic\\";
        File tarFile = new File(path + fileName);
        try {
            file.transferTo(tarFile);
            msgJson = MsgJson.success("上传成功");
        } catch (IOException e) {
            e.printStackTrace();
            msgJson = new MsgJson();
            msgJson.setSuccess(false)
                    .setCode(200)
                    .setMsg("上传失败");
            return msgJson.toJson();
        }
        return msgJson.toJson();
    }
}
