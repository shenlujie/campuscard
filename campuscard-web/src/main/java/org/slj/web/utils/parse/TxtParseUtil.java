package org.slj.web.utils.parse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * @Description: 静态txt文档读取和写入
 * @create: 2019/3/30
 * @Author: SLJ
 */
public class TxtParseUtil {

    /**
     * 日志工具
     */
    private static final Logger LOG = LoggerFactory.getLogger(TxtParseUtil.class);

    /**
     * 静态文件的基本路径
     */
    private static final String BASE_PATH = "A:\\IntelliJ IDEA\\workspace_backup\\campuscard\\campuscard-web\\src\\main\\resources\\static\\";

    /**
     * 读取文档
     * @param fileName 文档名
     * @return 文档内容
     */
    public static String readFile(String fileName) {
        InputStreamReader reader = null;
        BufferedReader bufferedReader = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //读取将GBK转换成UTF-8
            reader = new InputStreamReader(new FileInputStream(BASE_PATH + fileName));
            LOG.info("编码为：" + reader.getEncoding());
            bufferedReader = new BufferedReader(reader);
            String s;
            while ((s=bufferedReader.readLine()) != null){
                stringBuilder.append(s);
            }
        } catch (FileNotFoundException e) {
            LOG.error("读取文件名找不到");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null){
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return stringBuilder.toString();
    }

    /**
     * 写入文档
     * @param fileName 文档名
     * @return 是否成功
     */
    public static boolean writeFile(String fileName,String content){
        OutputStreamWriter writer = null;
        BufferedWriter bufferedWriter = null;
        boolean res = false;
        try {
            //写入将UTF-8转换成GBK
            writer = new OutputStreamWriter(new FileOutputStream(BASE_PATH + fileName),"GBK");
            System.out.println("编码为：" + writer.getEncoding());
            bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(content);
            bufferedWriter.flush();
            res = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null){
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return res;
    }
}
