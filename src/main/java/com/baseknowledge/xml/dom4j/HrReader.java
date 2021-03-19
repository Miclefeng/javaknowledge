package com.baseknowledge.xml.dom4j;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.List;

/**
 * @author miclefengzss
 */
public class HrReader {

    public void readXml() {
        String fileName = "src/main/java/com/baseknowledge/xml/hr-schema.xml";

        // SAXReader 是读取 xml 文件的核心类，用于将 xml 解析后以树的形式保存在内存中
        SAXReader saxReader = new SAXReader();

        try {
            Document document = saxReader.read(fileName);
            // 获取 xml 文档的根节点，即 hr 标签
            Element rootElement = document.getRootElement();
            // elements 获取指定标签集合
            List<Element> employeeList = rootElement.elements("employee");
            for (Element employee : employeeList) {
                System.out.print(employee.attribute("no").getText() + " ");
                // element 获取唯一子节点对象
                Element name = employee.element("name");
                // getText() 获取标签文本
                String empName = name.getText();
                System.out.print(empName + " ");
                System.out.print(employee.elementText("age") + " ");
                System.out.print(employee.elementText("salary") + " ");
                Element department = employee.element("department");
                System.out.print(department.element("dname").getText() + " | ");
                System.out.print(department.elementText("address") + " ");
                System.out.println();
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        HrReader hrReader = new HrReader();
        hrReader.readXml();
    }
}
