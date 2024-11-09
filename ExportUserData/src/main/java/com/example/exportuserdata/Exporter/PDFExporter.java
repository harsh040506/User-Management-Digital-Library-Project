package com.example.exportuserdata.Exporter;

import com.example.exportuserdata.Entity.Users;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import jakarta.servlet.http.HttpServletResponse;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

public class PDFExporter {
    private List<Users> listUsers;

    public PDFExporter(List<Users> listUsers) {
        this.listUsers = listUsers;
    }

    private Font loadUrbanistFont(float size, Color color) throws IOException, DocumentException {
        String fontPath = "src/main/resources/fonts/Urbanist-Semibold.ttf";
        BaseFont baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font font = new Font(baseFont, size);
        font.setColor(color);
        return font;
    }

    private void writeTableHeader(PdfPTable table) throws IOException, DocumentException {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.GRAY);  // Grey header theme
        cell.setPadding(5);

        Font font = loadUrbanistFont(12, Color.WHITE);  // Reduced font size to 12

        cell.setPhrase(new Phrase("User ID", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Full Name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Username", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Password", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) throws IOException, DocumentException {
        Font font = loadUrbanistFont(10, Color.BLACK);  // Data font size set to 10

        for (Users user : listUsers) {
            PdfPCell cell;

            cell = new PdfPCell(new Phrase(String.valueOf(user.getId()), font));
            cell.setBackgroundColor(Color.LIGHT_GRAY);  // Light grey for data rows
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(user.getFullname(), font));
            cell.setBackgroundColor(Color.LIGHT_GRAY);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(user.getUsername(), font));
            cell.setBackgroundColor(Color.LIGHT_GRAY);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(user.getPassword(), font));
            cell.setBackgroundColor(Color.LIGHT_GRAY);
            table.addCell(cell);
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font titleFont = loadUrbanistFont(16, Color.GRAY);  // Title font size set to 16

        Paragraph title = new Paragraph("List of Users", titleFont);
        title.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(title);

        PdfPTable table = new PdfPTable(4);  // 4 columns for Users
        table.setWidthPercentage(100f);
        table.setWidths(new float[]{1.5f, 3.5f, 3.0f, 3.0f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);
        document.close();
    }
}