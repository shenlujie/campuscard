package org.slj.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slj.domain.ConsumeTermDetail;
import org.slj.service.ConsumeTermDetailService;
import org.slj.web.utils.json.MsgJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
*@Description: 校园卡消费明细
*@create: 2019/3/17
*@Author: SLJ
*/
@RestController
@RequestMapping("/consumeTermDetail/")
public class ConsumeTermDetailController {

    /**
     * 日志工具
     */
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    ConsumeTermDetailService consumeTermDetailService;

    @RequestMapping(value = "add",method = RequestMethod.POST)
    public String add(ConsumeTermDetail consumeTermDetail) {
        consumeTermDetailService.saveModel(consumeTermDetail);
        return "";
    }

    @RequestMapping(value = "delete",method = RequestMethod.DELETE)
    public String delete(@RequestParam Integer id) {
	    consumeTermDetailService.deleteById(id);
	    return "";
    }

    @RequestMapping(value = "update",method = RequestMethod.PUT)
    public String update(ConsumeTermDetail consumeTermDetail) {
	    consumeTermDetailService.update(consumeTermDetail);
	    return "";
    }

    @RequestMapping(value = "detail",method = RequestMethod.GET)
    public String detail(@RequestParam Integer id) {
        MsgJson msgJson;
        ConsumeTermDetail termDetail = consumeTermDetailService.findById(id);
        if (null == termDetail){
            msgJson = MsgJson.not_found("无");
        }else {
            msgJson = MsgJson.success(termDetail);
        }
        return msgJson.toJson();
    }

    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<ConsumeTermDetail> list = consumeTermDetailService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return list.toString();
    }
}
