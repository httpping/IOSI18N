package com.tp.i18n.i18nlib.util;
/*

                   _ooOoo_
                  o8888888o
                  88" . "88
                  (| -_- |)
                  O\  =  /O
               ____/`---'\____
             .'  \\|     |//  `.
            /  \\|||  :  |||//  \
           /  _||||| -:- |||||-  \
           |   | \\\  -  /// |   |
           | \_|  ''\---/''  |   |
           \  .-\__  `-`  ___/-. /
         ___`. .'  /--.--\  `. . __
      ."" '<  `.___\_<|>_/___.'  >'"".
     | | :  `- \`.;`\ _ /`;.`/ - ` : | |
     \  \ `-.   \_ __\ /__ _/   .-` /  /
======`-.____`-.___\_____/___.-`____.-'======
                   `=---='
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
         佛祖保佑       永无BUG

*/

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;

/**
 * 项目名称: z
 * 类描述：
 * 创建时间:2018/12/3 10:35
 *
 * @author tanping
 */
public class IOSStringUtils {

    static  List<String> lines ;
    public static  void parse(String path , List<List<String>> listData, int index, String tag) throws ParserConfigurationException, SAXException, TransformerConfigurationException, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path)),"UTF-8"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path,true),"UTF-8"));
        lines = new ArrayList<String>();
        while (true) {
            String line = br.readLine();
            if (line !=null){
                lines.add(line);
            }else {
                break;
            }
        }

        if (tag!=null){
            String line = createComment("",tag);
            bw.newLine();
            bw.write(line);
            bw.newLine();
        }

        createBody(listData,index,bw);

        bw.flush();;
        bw.close();;
        br.close();
    }


    private static void createBody(List<List<String>> listData, int index,BufferedWriter bw) throws IOException {
        for (int i =1 ;i<listData.size();i++) {
            List<String> datas = listData.get(i);
            String name = datas.get(0);
            String value = datas.get(index);

            if (StringUtil.isEmpty(name) || StringUtil.isEmpty(value)) {
                continue;
            }

            if (isExist( name, value)) {
                continue;
            }

            String line =  createLine(name,value);
            bw.write(line);
            bw.newLine();

        }
    }

    public static String  createLine(String name,String value){

        name= name.replaceAll("\"","\\\\\"");
        value= value.replaceAll("\"","\\\\\"");

        String line = "\"" + name + "\"";
        line += " = ";

        line += "\"" + value + "\" ; " ;
        return line;
    }


    public static String  createComment(String name,String value){

        String line = "//" + value;
        return line;
    }

    private static boolean isExist(String name, String value) {

        for (String line :lines){
            if (line == null){
                continue;
            }
            if (line.contains(name)){
                return true;
            }
        }

        return false;
    }
}
