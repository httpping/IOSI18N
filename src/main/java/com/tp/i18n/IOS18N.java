package com.tp.i18n;

import com.sun.org.apache.xml.internal.security.Init;
import com.tp.i18n.i18nlib.IOSI18N;
import com.tp.i18n.utils.DateUtils;
import com.tp.i18n.utils.ExceptionUtil;
import com.tp.i18n.utils.InitConfig;
import com.tp.i18n.utils.StringUtils;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Date;

public class IOS18N {
    private static JPanel root1;
    private JTextField projectPath;
    private JButton selectProject;
    private JButton selectExcel;
    private JTextField excelPath;
    private JLabel label1;
    private JPanel root;
    private JButton submit;

    public IOS18N() {
        selectProject.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openChooseFile();
            }
        });
        selectExcel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openChooseFileExcel();
            }
        });
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                submit();
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("IOS18N");
        IOS18N ios18N = new IOS18N();
        root1 =ios18N.root;
        frame.setContentPane(root1);

        String filepath = System.getProperty("user.dir") ;
        System.out.println(filepath+"");




//        JOptionPane.showConfirmDialog(root1, "filepath " +filepath, "提示 ", JOptionPane.YES_OPTION);
        int wh = 800;
        frame.setSize(wh, wh);
        int w = (Toolkit.getDefaultToolkit().getScreenSize().width - wh) / 2;
        int h = (Toolkit.getDefaultToolkit().getScreenSize().height - wh) / 2;
//        frame.setLocation(w, h);

        frame.setLocationRelativeTo(root1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);



        try {
            String[] paths = InitConfig.parse(filepath +File.separator + "i18n.cfg");
            ios18N.projectPath.setText(paths[0]);
            ios18N.excelPath.setText(paths[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





    public void submit(){

        String rootPath = projectPath.getText();
        String excelpath = excelPath.getText();

        if (StringUtils.isEmpty(rootPath) || StringUtils.isEmpty(excelpath)){
            int option = JOptionPane.showConfirmDialog(root, "目录必须选择,信息必须填写 ", "提示 ", JOptionPane.YES_OPTION);
            return;
        }


        IOSI18N.rootRes = rootPath;
        IOSI18N.rootXLSX = excelpath;


        try {
//            int a = 1/0;
            IOSI18N.parse(DateUtils.format(new Date()));
            int option = JOptionPane.showConfirmDialog(root,"操作完成", "提示 ", JOptionPane.YES_OPTION);

        }  catch (Exception e) {
            e.printStackTrace();
            int option = JOptionPane.showConfirmDialog(root, ExceptionUtil.getStackTrace(e), "提示 ", JOptionPane.YES_OPTION);

        }


    }




    public void openChooseFile() {
        JFileChooser jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        jfc.showDialog(new JLabel(), "选择");
        File file = jfc.getSelectedFile();
        if (file.isDirectory()) {
            System.out.println("文件夹x:" + file.getAbsolutePath());
            projectPath.setText(file.getAbsolutePath());
        } else if (file.isFile()) {
            System.out.println("文件:" + file.getAbsolutePath());
        }
        System.out.println(jfc.getSelectedFile().getName());

    }

    public void openChooseFileExcel() {
        JFileChooser jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jfc.showDialog(new JLabel(), "选择Excel");
        File file = jfc.getSelectedFile();
        if (file.isDirectory()) {
            System.out.println("文件夹Excel:" + file.getAbsolutePath());
            excelPath.setText(file.getAbsolutePath());
        } else if (file.isFile()) {
            System.out.println("文件:" + file.getAbsolutePath());
            excelPath.setText(file.getAbsolutePath());
        }
        System.out.println(jfc.getSelectedFile().getName());

    }
}
