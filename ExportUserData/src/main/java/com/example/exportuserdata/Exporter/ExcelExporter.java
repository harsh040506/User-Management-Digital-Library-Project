package com.example.exportuserdata.Exporter;

import com.example.exportuserdata.Entity.Users;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Users> listUsers;

    public ExcelExporter(List<Users> listUsers) {
        this.listUsers = listUsers;
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("Users");
        writeHeaderLine();
        writeDataLines();
    }

    private void writeHeaderLine() {
        Row row = sheet.createRow(0);
        CellStyle style = createHeaderStyle();

        createCell(row, 0, "User ID", style);
        createCell(row, 1, "Full Name", style);
        createCell(row, 2, "Username", style);
        createCell(row, 3, "Password", style);
    }

    private CellStyle createHeaderStyle() {
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        font.setFontName("Urbanist SemiBold");
        style.setFont(font);
        return style;
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        setCellValue(cell, value);
        cell.setCellStyle(style);
    }

    private void setCellValue(Cell cell, Object value) {
        if (value instanceof Long) {
            cell.setCellValue((Long) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else {
            cell.setCellValue(value.toString());
        }
    }

    private void writeDataLines() {
        CellStyle style = createDataStyle();
        int rowCount = 1;

        for (Users user : listUsers) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, user.getId(), style);
            createCell(row, columnCount++, user.getFullname(), style);
            createCell(row, columnCount++, user.getUsername(), style);
            createCell(row, columnCount++, user.getPassword(), style);
        }
    }

    private CellStyle createDataStyle() {
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        font.setFontName("Urbanist SemiBold"); // Make sure Urbanist is available in the Excel environment
        style.setFont(font);
        return style;
    }

    public void export(HttpServletResponse response) throws IOException {
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}