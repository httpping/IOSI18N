package com.tp.i18n.i18nlib;

import com.tp.i18n.i18nlib.util.ExcelReader;
import com.tp.i18n.i18nlib.util.FileParse;
import com.tp.i18n.i18nlib.util.StringUtil;
import com.tp.i18n.i18nlib.util.XmlParseUtil;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * 国际化使用
 */
public class MyI18NClass {

    /**
     * 资源目录 例如 ： C:\work\demo\AndroidDemos\I18NParse\app\src\main\res
     */
    public static String rootRes ;//="C:\\work\\zf\\Zaful\\app\\src\\main\\res";


    /**
     * xml 目录 ： 默认
     */
    public static String rootXLSX;


    public static void main(String[] args) throws Exception {
        System.out.println("start ...");
        String filepath = System.getProperty("user.dir") ;

        if (rootRes == null){
            rootRes  = filepath + "\\app\\src\\main\\res";
        }
        if(rootXLSX ==null){
            rootXLSX = filepath +"\\I18NLib\\xml-data.xlsx";
        }

        parse("v4.4.0");
        System.out.println("ending ... ");

        System.out.println(filepath);
    }

    public static  void parse(String tag) throws Exception {

        //解析 xlsx
        ExcelReader eh = new ExcelReader(rootXLSX,"Sheet1");
        eh.getSheetData();

        //获取目录
        HashMap<String, String> map = FileParse.parse(rootRes);

        List<String> strings =  eh.listData.get(0);

        for (int i = 0 ;i<strings.size();i++){
            String type = strings.get(i);
            if (StringUtil.isEmpty(type)){
                continue;
            }
            String path = map.get(type);
            if (StringUtil.isEmpty(path)){
                continue;
            }

            //添加
            XmlParseUtil.parse(path,eh.listData,i,tag);

        }
    }
}
