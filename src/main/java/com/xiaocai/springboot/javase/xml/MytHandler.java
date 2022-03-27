package com.xiaocai.springboot.javase.xml;

import org.xml.sax.Attributes;
import org.xml.sax.HandlerBase;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.Stack;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/27 11:33
 */
public class MytHandler extends DefaultHandler {

    // 使用栈这个数据结构来保存
    private Stack<String> stack = new Stack<String>();
    // 数据
    private String title;
    private String author;
    private String year;
    private double price;

    @Override
    public void startDocument() throws SAXException {
        System.out.println("start document -> parse begin");
    }

    @Override
    public void endDocument() throws SAXException {

        System.out.println("end document -> parse finished");
    }

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        // System.out.println("start element-----------");
        // System.out.println("    localName: " + localName);
        // System.out.println("    qName: " + qName);

        // 将标签名压入栈
        stack.push(qName);

        // 处理属性
        for (int i = 0; i < attributes.getLength(); ++i) {
            String attrName = attributes.getQName(i);
            String attrValue = attributes.getValue(i);
            System.out.println("属性： " + attrName + "=" + attrValue);

        }

    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        // System.out.println("characters-----------");
        // System.out.println("    ch: " + Arrays.toString(ch) );
        // System.out.println("    ch: " + ch);
        // System.out.println("    start: " + start);
        // System.out.println("    length: " + length);

        // 取出标签名
        String tag = stack.peek();

        if ("title".equals(tag)) {
            title = new String(ch, start, length);

        } else if ("author".equals(tag)) {
            author = new String(ch, start, length);
        } else if ("year".equals(tag)) {
            year = new String(ch, start, length);
        } else if ("price".equals(tag)) {
            price = Double.parseDouble(new String(ch, start, length));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        // System.out.println("end element-----------");
        // System.out.println("    localName: " + localName);
        // System.out.println("    qName: " + qName);
        stack.pop();// 表示该元素解析完毕，需要从栈中弹出标签
        if ("book".equals(qName)) {
            System.out.println("Book info: -------");
            System.out.println("    title: " + title);
            System.out.println("    author: " + author);
            System.out.println("    year: " + year);
            System.out.println("    price: " + price);
            System.out.println();
        }
    }
}
