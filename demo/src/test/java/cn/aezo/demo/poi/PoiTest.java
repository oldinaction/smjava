package cn.aezo.demo.poi;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.FileOutputStream;
import java.util.Date;

/**
 * Created by smalle on 2017/9/18.
 */
public class PoiTest {

    @Test
    public void test() throws Exception {
        Workbook wb = new HSSFWorkbook();
        CreationHelper createHelper = wb.getCreationHelper();

        Sheet sheet1 = wb.createSheet("sheet1");
        Row row = sheet1.createRow((short) 0);

        // 创建单元格
        Cell cell = row.createCell(0);
        cell.setCellValue(1);

        row.createCell(1).setCellValue(1.2);
        row.createCell(2).setCellValue("你好。。。。。。。。。。。。。。。。。。。。。。。");
        row.createCell(3).setCellValue(createHelper.createRichTextString("This is a string"));
        row.createCell(4).setCellValue(true); // TRUE

        // 创建日期
        row.createCell(5).setCellValue(new Date()); // 42996.4684197222

        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy/MM/dd HH:mm"));

        cell = row.createCell(6);
        cell.setCellValue(new Date());
        cell.setCellStyle(cellStyle);

        // 自动调整每一列的宽度
        for(int i = 0; i < 10; i++) {
            sheet1.autoSizeColumn(i);
        }


        // 在第二个sheet的第二行增加数据
        Sheet sheet2 = wb.createSheet("sheet2");
        Row row2 = sheet2.createRow((short) 1);
        row2.createCell(0).setCellValue(1.1);

        // 写出数据
        FileOutputStream fileOut = new FileOutputStream("workbook.xls");
        wb.write(fileOut);
        fileOut.close();
    }
}
