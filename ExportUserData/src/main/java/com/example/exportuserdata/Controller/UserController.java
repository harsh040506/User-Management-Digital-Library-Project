package com.example.exportuserdata.Controller;

import com.example.exportuserdata.Entity.Users;
import com.example.exportuserdata.Exporter.ExcelExporter;
import com.example.exportuserdata.Exporter.PDFExporter;
import com.example.exportuserdata.Service.UserService;
import com.lowagie.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import ch.qos.logback.core.model.Model;

@Controller
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/Search/export")
    public String showUserSearchPage(Model model) {
        return "ExportSearch";}

    @GetMapping("/users/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Users> listUsers = service.listAll();  // Adjusted to match Users entity

        ExcelExporter excelExporter = new ExcelExporter(listUsers);  // Corrected class name
        excelExporter.export(response);
    }

    @GetMapping("/users/export/excel/fullname/{fullname}")
    public void exportFullnameToExcel(@PathVariable String fullname, HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Users> listUsers = service.listAllByFullnameSubstring(fullname);

        ExcelExporter excelExporter = new ExcelExporter(listUsers);  // Corrected class name
        excelExporter.export(response);
    }

    @GetMapping("/users/export/excel/username/{username}")
    public void exportUsernameToExcel(@PathVariable String username, HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Users> listUsers = service.listAllByUsernameSubstring(username);

        ExcelExporter excelExporter = new ExcelExporter(listUsers);
        excelExporter.export(response);
    }

    @GetMapping("/users/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Users> listUsers = service.listAll();  // Changed to match the Users entity

        PDFExporter exporter = new PDFExporter(listUsers);  // Updated class name
        exporter.export(response);
    }

    @GetMapping("/users/export/pdf/fullname/{fullname}")
    public void exportFullnameToPDF(@PathVariable String fullname, HttpServletResponse response) throws IOException {
            response.setContentType("application/pdf");
            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
            String currentDateTime = dateFormatter.format(new Date());

            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
            response.setHeader(headerKey, headerValue);

            List<Users> listUsers = service.listAllByFullnameSubstring(fullname);

            PDFExporter exporter = new PDFExporter(listUsers);
            exporter.export(response);
    }

    @GetMapping("/users/export/pdf/username/{username}")
    public void exportUsernameToPDF(@PathVariable String username, HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Users> listUsers = service.listAllByUsernameSubstring(username);

        PDFExporter exporter = new PDFExporter(listUsers);
        exporter.export(response);
    }
}