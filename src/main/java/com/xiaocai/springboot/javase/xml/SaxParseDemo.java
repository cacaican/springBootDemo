package com.xiaocai.springboot.javase.xml;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/27 11:28
 */
public class SaxParseDemo {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        saxParser.parse(new File("aa.xml"), new  DefaultHandler());//使用默认的解析器
        saxParser.parse(new File("aa.xml"), new  MytHandler());//使用默认的解析器

    }
}
