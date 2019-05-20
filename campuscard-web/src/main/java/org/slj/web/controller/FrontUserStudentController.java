package org.slj.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slj.domain.FrontUserStudent;
import org.slj.enums.EmCode;
import org.slj.service.FrontUserStudentService;
import org.slj.web.bo.ChangePswParams;
import org.slj.web.bo.LoginParams;
import org.slj.web.utils.json.MsgJson;
import org.slj.web.utils.jwt.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

/**
 * @Description: 前台学生用户
 * @create: 2019/3/17
 * @Author: SLJ
 */
@RestController
@RequestMapping("/frontUserStudent/")
public class FrontUserStudentController {

    /**
     * 日志工具
     */
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    FrontUserStudentService frontUserStudentService;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(FrontUserStudent frontUserStudent) {
        MsgJson msgJson;
        frontUserStudentService.saveModel(frontUserStudent);
        return "";
    }

    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public String delete(@RequestParam Integer id) {
        MsgJson msgJson;
        frontUserStudentService.deleteById(id);
        return "";
    }

    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public String update(FrontUserStudent frontUserStudent) {
        frontUserStudentService.update(frontUserStudent);
        MsgJson msgJson = MsgJson.success("修改成功");
        return msgJson.toJson();
    }

    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(@RequestParam Integer id) {
        MsgJson msgJson;
        FrontUserStudent frontUserStudent = frontUserStudentService.findById(id);
        if (null == frontUserStudent) {
            msgJson = MsgJson.not_found("无");
        } else {
            msgJson = MsgJson.success(frontUserStudent);
        }
        return msgJson.toJson();
    }

    @RequestMapping(value = "listByCondition", method = RequestMethod.GET)
    public String listByCondition(@RequestParam(defaultValue = "0") Integer page
            , @RequestParam(defaultValue = "0") Integer limit
            , HttpServletRequest request) {
        PageHelper.startPage(page, limit);
        Condition condition = new Condition(FrontUserStudent.class);
        Example.Criteria criteria = condition.createCriteria();
        String stNum = request.getParameter("stNum");
        String stName = request.getParameter("stName");
        String stDepartment = request.getParameter("stDepartment");
        String stMajor = request.getParameter("stMajor");
        if (!StringUtils.isEmpty(stNum)) {
            criteria.andLike("stNum", "%" + stNum + "%");
        }
        if (!StringUtils.isEmpty(stName)) {
            criteria.andLike("stName", "%" + stName + "%");
        }
        if (!StringUtils.isEmpty(stDepartment)) {
            criteria.andLike("stDepartment", "%" + stDepartment + "%");
        }
        if (!StringUtils.isEmpty(stMajor)) {
            criteria.andLike("stMajor", "%" + stMajor + "%");
        }
        List<FrontUserStudent> list = frontUserStudentService.findByCondition(condition);
        PageInfo pageInfo = new PageInfo(list);
        MsgJson msgJson = new MsgJson();
        msgJson.setSuccess(true)
                .setCode(EmCode.SUCCESS.getCode())
                .setMsg(EmCode.SUCCESS.getMsg())
                .setCount((int) pageInfo.getTotal())
                .setObj(list);
        return msgJson.toJson();
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@RequestBody LoginParams params) {
        MsgJson msgJson = new MsgJson();
        String token;
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(params.getStNum());
            if (!passwordEncoder.matches(params.getStPassword(), userDetails.getPassword())) {
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
            msgJson = MsgJson.success(tokenHead + token);
        } catch (UsernameNotFoundException e) {
            msgJson.setSuccess(false).setCode(EmCode.SUCCESS.getCode()).setMsg("该用户不存在");
        } catch (BadCredentialsException e) {
            msgJson.setSuccess(false).setCode(EmCode.SUCCESS.getCode()).setMsg("密码不正确");
        }
        return msgJson.toJson();
    }

    @RequestMapping(value = "getInfo", method = RequestMethod.GET)
    public String getInfo(Principal principal) {
        String stNum = principal.getName();
        FrontUserStudent frontUserStudent = frontUserStudentService.getUserByUsername(stNum);
        MsgJson msgJson = MsgJson.success(frontUserStudent);
        return msgJson.toJson();
    }

    @RequestMapping(value = "changePsw", method = RequestMethod.POST)
    public String changePsw(@RequestBody ChangePswParams changePswParams) {
        MsgJson msgJson;
        UserDetails userDetails = userDetailsService.loadUserByUsername(changePswParams.getStNum());
        if (!passwordEncoder.matches(changePswParams.getCurPsw(), userDetails.getPassword())) {
            msgJson = new MsgJson();
            msgJson.setCode(200).setSuccess(false).setMsg("当前密码不正确");
            return msgJson.toJson();
        }else if (!changePswParams.getNewPsw().equals(changePswParams.getReNewPsw())){
            msgJson = new MsgJson();
            msgJson.setCode(200).setSuccess(false).setMsg("两次密码输入不一致");
            return msgJson.toJson();
        }else {
            FrontUserStudent frontUserStudent = frontUserStudentService.getUserByUsername(changePswParams.getStNum());
            String finalPsw = passwordEncoder.encode(changePswParams.getNewPsw());
            frontUserStudent.setStPassword(finalPsw);
            frontUserStudentService.update(frontUserStudent);
            msgJson = new MsgJson();
            msgJson.setCode(200).setSuccess(true).setMsg("修改成功");
            return msgJson.toJson();
        }
    }
}
