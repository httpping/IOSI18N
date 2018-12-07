package com.tp.i18n.utils;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import java.io.*;

public class InitConfig {


    public static String[] parse(String path) throws ParserConfigurationException, SAXException, TransformerConfigurationException, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path))));

        String line = br.readLine();

        String root = line.split("=")[1];

        line = br.readLine();
        String excel = line.split("=")[1];


        String[] paths = new String[2];
        paths[0] = root;
        paths[1] = excel;

        return paths;
    }
}
