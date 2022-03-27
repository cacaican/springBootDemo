package com.xiaocai.springboot.javase.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/27 11:28
 */
public class DomParseDemo {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {

        String path = DomParseDemo.class.getResource("").getPath();
        System.out.println(path);
        System.out.println(path);
        Document document = getDefaultDocument(new File("aa.xml"));

        Element documentElement = document.getDocumentElement();

    }

    public static Document getDefaultDocument(File file) throws ParserConfigurationException, IOException, SAXException {
        // 创建DocumentBuilderFactory，用于取得DocumentBuilder
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        // 使用DocumentBuilder进行DOM树的转换操作，读取指定的xml文件
        return builder.parse(file);
    }
}
