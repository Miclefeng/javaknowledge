package com.baseknowledge.xml.dom4j;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.util.List;

/**
 * @author miclefengzss
 * 2021/3/17 下午3:58
 */
public class XPathTest {

    public void xpath(String xpathExp) {
        String fileName = "src/main/java/com/baseknowledge/xml/hr-schema.xml";

        SAXReader saxReader = new SAXReader();

        try {
            Document document = saxReader.read(fileName);

            List<Node> nodes = document.selectNodes(xpathExp);

            for (Node node : nodes) {
                Element element = (Element) node;
                System.out.print(element.attribute("no").getText() + " ");
                System.out.print(element.element("name").getText() + " ");
                System.out.print(element.element("age").getText() + " ");
                System.out.print(element.element("salary").getText() + " ");
                System.out.println("\n======================");
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        XPathTest xPathTest = new XPathTest();
//        xPathTest.xpath("/hr/employee");
        xPathTest.xpath("//employee");
//        xPathTest.xpath("//employee[name='linxuan']");
//        xPathTest.xpath("//employee[@no=3309]");
//        xPathTest.xpath("//employee[2]");
//        xPathTest.xpath("//employee[last()]");
//        xPathTest.xpath("//employee[position() <= 2]");
//        xPathTest.xpath("//employee[1] | //employee[2]");
    }
}
