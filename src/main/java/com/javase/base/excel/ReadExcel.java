package com.javase.base.excel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

import com.javase.base.jdbc.Utils.DBUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.crypt.Decryptor;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author miclefengzss
 * 2021/4/6 上午10:37
 */
public class ReadExcel {

    public static void main(String[] args) throws IOException, InvalidFormatException, GeneralSecurityException, SQLException, ClassNotFoundException {

        System.out.println("begin");

//        String excelPath = "/Users/miclefengzss/Downloads/如影智能在职员工(0402)给张尚尚.xls";
        String excelPath = "/Users/miclefengzss/Downloads/如影智能在职员工(0405)给张尚尚.xls";
        String password = "Ryzn2021";
        String prefix = excelPath.substring(excelPath.lastIndexOf(".") + 1);
        Workbook workbook;
        InputStream inp = new FileInputStream(excelPath);

        if (prefix.toUpperCase().equals("XLS")) {
            org.apache.poi.hssf.record.crypto.Biff8EncryptionKey
                    .setCurrentUserPassword(password);
            workbook = WorkbookFactory.create(inp);
            inp.close();
        } else {
            POIFSFileSystem pfs = new POIFSFileSystem(inp);
            inp.close();
            EncryptionInfo encInfo = new EncryptionInfo(pfs);
            Decryptor decryptor = Decryptor.getInstance(encInfo);
            decryptor.verifyPassword(password);
            workbook = new XSSFWorkbook(decryptor.getDataStream(pfs));
        }

        Sheet sheet = workbook.getSheetAt(0);
        int startRowNum = sheet.getFirstRowNum();
        int endRowNum = sheet.getLastRowNum();
        Connection connection = DBUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO `employee` (`employee_id`,`project_id`,`account_id`,`name`,`phone`,`org_id`,`full_org_id`,`update_time`,`create_time`,`create_user_id`) VALUES (?,?,?,?,?,?,?,?,?,?)");
        int currentTime = (int) (System.currentTimeMillis() / 1000);
        for (int rowNum = startRowNum + 1; rowNum <= endRowNum; rowNum++) {
            Row row = sheet.getRow(rowNum);
            if (row == null) {
                continue;
            }
            String employeeId = UUID.randomUUID().toString().replace("-", "");
            preparedStatement.setString(1, employeeId);
            System.out.println(rowNum);
            int startCellNum = row.getFirstCellNum();
            int endCellNum = row.getLastCellNum();
            for (int cellNum = startCellNum; cellNum < endCellNum; cellNum++) {
                Cell cell = row.getCell(cellNum);
                if (cell == null) {
                    continue;
                }
//                preparedStatement.setString(2, "393554d02d364068808dc545280602ce");
//                preparedStatement.setString(3, "account_id");
//                preparedStatement.setString(4, "name");
//                preparedStatement.setString(5, "phone");
//                preparedStatement.setString(6, "741p16q7zt37w2e23q619cshh4znw85z");
//                preparedStatement.setString(7, "741p16q7zt37w2e23q619cshh4znw85z,6arxw1i9amxgu6pd2p33r620u3728ar2");
//                preparedStatement.setInt(8, currentTime);
//                preparedStatement.setInt(9, currentTime);
//                preparedStatement.setString(10, "a8b6085c90f44d6c9a3c96a4827e97ca");
                preparedStatement.addBatch();
                System.out.print(" " + cell.getStringCellValue() + " ");
            }
            preparedStatement.executeBatch();
            System.out.println();
        }
        System.out.println("end");
    }
}
