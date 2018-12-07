package com.tp.i18n.i18nlib;
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

import com.tp.i18n.i18nlib.util.ExcelReader;
import com.tp.i18n.i18nlib.util.FileParse;
import com.tp.i18n.i18nlib.util.IOSStringUtils;
import com.tp.i18n.i18nlib.util.StringUtil;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * 项目名称: z
 * 类描述：
 * 创建时间:2018/11/30 16:40
 *
 * @author tanping
 */
public class IOSI18N {

    /**
     * 资源目录 例如 ： C:\work\demo\AndroidDemos\I18NParse\app\src\main\res
     */
    public static String rootRes  ="E:\\zf-ios\\zf\\Zaful\\Supporting Files\\Language";


    /**
     * xml 目录 ： 默认
     */
    public static String rootXLSX;

    public static void main(String[] args) throws ParserConfigurationException, SAXException, TransformerConfigurationException, IOException {

        System.out.println("hello world!");
        String filepath = System.getProperty("user.dir") ;


        if(rootXLSX ==null){
            rootXLSX = filepath +"\\xml-data.xlsx";
        }

        parse("add by tp ");
    }


    public static void parse(String tag) throws ParserConfigurationException, SAXException, TransformerConfigurationException, IOException {

        //解析 xlsx
        ExcelReader eh = new ExcelReader(rootXLSX,"Sheet1");
        eh.getSheetData();

        //获取目录
        HashMap<String, String> map = FileParse.parseIOS(rootRes);
        System.out.println("x");
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
            IOSStringUtils.parse(path,eh.listData,i,tag);

        }

    }

}
