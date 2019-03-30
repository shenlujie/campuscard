package org.slj.web.utils.parse;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slj.domain.ConsumeTermDetail;
import org.slj.domain.FrontUserStudent;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description: xml文件解析
 * @create: 2019/3/18
 * @Author: SLJ
 */
public class XmlParseUtil {

    /**
     * 日志工具
     */
    private static final Logger LOG = LoggerFactory.getLogger(XmlParseUtil.class);

    /**
     * xml解析实现类
     * @param path
     * @return 批量学生信息
     */
    public static List<FrontUserStudent> transferUserStudent(String path){
        SAXReader reader = new SAXReader();
        //核验集Map，学号为key,student实体类为value，为保证学生信息的唯一性
        Map<String,FrontUserStudent> checkMap = new HashMap<>();
        //结果集List
        List<FrontUserStudent> resultList = new ArrayList<>();
        try {
            //加载源文件
            Document document = reader.read(new File(path));
            //获取根节点<ROWDATA>
            Element rowData = document.getRootElement();
            Iterator rootIterator = rowData.elementIterator();
            //遍历根节点
            while (rootIterator.hasNext()){
                //获取次节点<row>
                Element row = (Element) rootIterator.next();
                Iterator rowIterator = row.elementIterator();
                //遍历次节点
                while (rowIterator.hasNext()){
                    FrontUserStudent student = new FrontUserStudent();
                    //学号节点
                    Element stNumELement = (Element) rowIterator.next();
                    //姓名节点
                    Element stNameELement = (Element) rowIterator.next();
                    //性别节点
                    Element stSexELement = (Element) rowIterator.next();
                    //学院节点
                    Element stDepartmentELement = (Element) rowIterator.next();
                    //专业节点
                    Element stMajorELement = (Element) rowIterator.next();

                    //获取每个节点的值，Email和password为默认值
                    String stNum = stNumELement.getStringValue();
                    //排除校外人员临时卡
                    if (!stNum.startsWith("1")){
                        break;
                    }
                    String stName = stNameELement.getStringValue();
                    String stSex = stSexELement.getStringValue();
                    String stDepartment = stDepartmentELement.getStringValue();
                    if ("其他".equals(stDepartment)){
                        break;
                    }else if ("临时卡".equals(stDepartment) || "教工卡".equals(stDepartment)){
                        break;
                    }
                    String stMajor = stMajorELement.getStringValue();
                    if ("延期卡".equals(stMajor)){
                        break;
                    }
                    String stEmail = stNum + "@st.nuc.edu.cn";
                    String stPassword = "123456";

                    //装载获取到的每个值
                    student.setStNum(stNum);
                    student.setStName(stName);
                    //判断性别
                    if ("0".equals(stSex)){
                        student.setStSex("女");
                    }else {
                        student.setStSex("男");
                    }
                    student.setStDepartment(stDepartment);
                    student.setStMajor(stMajor);
                    student.setStEmail(stEmail);
                    student.setStPassword(stPassword);

                    //以学号作为key存储到map中，不允许出现重复值
                    if (!checkMap.containsKey(stNum)){
                        checkMap.put(stNum, student);
                        resultList.add(student);
                    }
                    break;
                }
            }
        } catch (DocumentException e) {
            LOG.error("解析xml失败...");
        }

        return resultList;
    }

    /**
     * xml解析实现类
     * @param path
     * @return 批量消费明细
     */
    public static List<ConsumeTermDetail> transferTermDetail(String path){
        SAXReader reader = new SAXReader();
        //结果集List
        List<ConsumeTermDetail> resultList = new ArrayList<>();
        try {
            //加载源文件
            Document document = reader.read(new File(path));
            //获取根节点<ROWDATA>
            Element rowData = document.getRootElement();
            Iterator rootIterator = rowData.elementIterator();
            //遍历根节点
            while (rootIterator.hasNext()){
                //获取次节点<row>
                Element row = (Element) rootIterator.next();
                Iterator rowIterator = row.elementIterator();
                //遍历次节点
                while (rowIterator.hasNext()){
                    ConsumeTermDetail termDetail = new ConsumeTermDetail();
                    //学号节点
                    Element stNumELement = (Element) rowIterator.next();
                    //姓名节点
                    Element stNameELement = (Element) rowIterator.next();
                    //性别节点
                    Element stSexELement = (Element) rowIterator.next();
                    //学院节点
                    Element stDepartmentELement = (Element) rowIterator.next();
                    //专业节点
                    Element stMajorELement = (Element) rowIterator.next();
                    //年级节点
                    Element stGradeElement = (Element) rowIterator.next();
                    //班级节点
                    Element stClassElement = (Element) rowIterator.next();
                    //消费项目节点
                    Element consumeRechargeElement = (Element) rowIterator.next();
                    //金额节点
                    Element sumElement = (Element) rowIterator.next();
                    //时间节点
                    Element happenTimeElement = (Element) rowIterator.next();

                    //获取每个节点的值，根据相关条件进行相应的排除
                    String stNum = stNumELement.getStringValue();
                    //排除校外人员临时卡
                    if (!stNum.startsWith("1")){
                        break;
                    }
                    String stDepartment = stDepartmentELement.getStringValue();
                    if ("其他".equals(stDepartment)){
                        break;
                    }else if ("临时卡".equals(stDepartment) || "教工卡".equals(stDepartment)){
                        break;
                    }
                    String stMajor = stMajorELement.getStringValue();
                    if ("延期卡".equals(stMajor)){
                        break;
                    }
                    SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    Date happenTime = null;
                    try {
                        happenTime = format.parse(happenTimeElement.getStringValue());
                    } catch (ParseException e) {
                        LOG.error("发生日期解析错误...");
                    }
                    String consumeRecharge = consumeRechargeElement.getStringValue();
                    Float sum = Float.valueOf(sumElement.getStringValue());
                    //装载获取到的每个值
                    termDetail.setHappenTime(happenTime);
                    termDetail.setConsumeRecharge(consumeRecharge);
                    termDetail.setSum(sum);
                    termDetail.setStNum(stNum);
                    resultList.add(termDetail);
                    break;
                }
            }
        } catch (DocumentException e) {
            LOG.error("解析xml失败...");
        }

        return resultList;
    }
}
