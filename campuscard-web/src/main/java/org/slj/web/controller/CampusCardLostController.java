package org.slj.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slj.domain.CampusCardLost;
import org.slj.enums.EmCode;
import org.slj.service.CampusCardLostService;
import org.slj.web.utils.json.MsgJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
*@Description: 校园卡失物招领
*@create: 2019/3/17
*@Author: SLJ
*/
@RestController
@RequestMapping("/campusCardLost/")
public class CampusCardLostController {

    /**
     * 日志工具
     */
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private CampusCardLostService campusCardLostService;

    @RequestMapping(value = "add",method = RequestMethod.POST)
    public String add(@RequestBody CampusCardLost campusCardLost) {
        MsgJson msgJson;
        campusCardLostService.saveModel(campusCardLost);
        msgJson = MsgJson.success("提交成功");
        return msgJson.toJson();
    }

    @RequestMapping(value = "delete",method = RequestMethod.DELETE)
    public String delete(@RequestParam Integer id) {
	    campusCardLostService.deleteById(id);
        MsgJson msgJson = MsgJson.success("删除成功");
        return msgJson.toJson();
    }

    @RequestMapping(value = "update",method = RequestMethod.PUT)
    public String update(@RequestParam int id, @RequestParam int status) {
        CampusCardLost campusCardLost = campusCardLostService.findById(id);
        if (status == 0){
            campusCardLost.setStatus(1);
        }
	    campusCardLostService.update(campusCardLost);
        MsgJson msgJson = MsgJson.success("推送成功");
	    return msgJson.toJson();
    }

    @RequestMapping(value = "detail",method = RequestMethod.GET)
    public String detail(@RequestParam Integer id) {
        MsgJson msgJson;
        CampusCardLost lost = campusCardLostService.findById(id);
        if (null == lost){
            msgJson = MsgJson.notFound("无");
        }else {
            msgJson = MsgJson.success(lost);
        }
        return msgJson.toJson();
    }

    @RequestMapping(value = "listByCondition",method = RequestMethod.GET)
    public String listByCondition(@RequestParam(defaultValue = "0") Integer page
            , @RequestParam(defaultValue = "0") Integer limit
            , HttpServletRequest request){
        PageHelper.startPage(page, limit);
        Condition condition = new Condition(CampusCardLost.class);
        Example.Criteria criteria = condition.createCriteria();
        String upStNum = request.getParameter("upStNum");
        String lostStNum = request.getParameter("lostStNum");
        String lostStName = request.getParameter("lostStName");
        String status = request.getParameter("status");
        if (!StringUtils.isEmpty(status)){
            Integer curStatus = Integer.valueOf(status);
            criteria.andEqualTo("status", curStatus);
        }
        if (!StringUtils.isEmpty(upStNum)){
            criteria.andLike("upStNum", "%" + upStNum + "%");
        }
        if (!StringUtils.isEmpty(lostStNum)){
            criteria.andLike("lostStNum", lostStNum + "%");
        }
        if (!StringUtils.isEmpty(lostStName)){
            criteria.andLike("lostStName", "%" + lostStName + "%");
        }
        List<CampusCardLost> list = campusCardLostService.findByCondition(condition);
        PageInfo pageInfo = new PageInfo(list);
        MsgJson msgJson = new MsgJson();
        msgJson.setSuccess(true)
                .setCode(EmCode.SUCCESS.getCode())
                .setMsg(EmCode.SUCCESS.getMsg())
                .setCount((int)pageInfo.getTotal())
                .setObj(list);
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
        String fileName = "campuscard-" + curUser + subFileName;
        String path = "A:\\IntelliJ IDEA\\workspace_backup\\campuscard\\campuscard-web\\src\\main\\resources\\static\\cardPic\\";
        File tarFile = new File(path + fileName);
        try {
            file.transferTo(tarFile);
            msgJson = MsgJson.success("上传成功");
        } catch (IOException e) {
            e.printStackTrace();
            msgJson = new MsgJson();
            msgJson.setSuccess(false)
                    .setCode(200)
                    .setMsg("文件名有误，上传失败");
            return msgJson.toJson();
        }
        return msgJson.toJson();
    }
}
