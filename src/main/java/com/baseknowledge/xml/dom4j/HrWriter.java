package com.baseknowledge.xml.dom4j;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * @author miclefengzss
 * 2021/3/17 下午3:21
 */
public class HrWriter {

    public void writeXml() {
        String fileName = "src/main/java/com/baseknowledge/xml/hr-schema.xml";

        // SAXReader 是读取 xml 文件的核心类，用于将 xml 解析后以树的形式保存在内存中
        SAXReader saxReader = new SAXReader();
        OutputStreamWriter osw = null;
        try {
            Document document = saxReader.read(fileName);
            Element rootElement = document.getRootElement();
            Element employee = rootElement.addElement("employee");
            employee.addAttribute("no", "3311");
            employee.addElement("name").addText("linxuan");
            employee.addElement("age").addText("1");
            employee.addElement("salary").addText("-2000");
            Element department = employee.addElement("department");
            department.addElement("dname").addText("home");
            department.addElement("address").addText("taian");
            osw = new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8");
            document.write(osw);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (osw != null) {
                try {
                    osw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        HrWriter hrWriter = new HrWriter();
        hrWriter.writeXml();
    }
}
