package com.example.libraryandhomepage;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/Home/Library")
public class DynamicLibrary {
    private final Path directoryPath = Paths.get("src/main/resources/static");

    @GetMapping
    public String listFiles(Model model) throws IOException {
        List<String> files = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(directoryPath, "*.pdf")) {
            for (Path entry : stream) {
                files.add(entry.getFileName().toString());
            }
        }
        model.addAttribute("files", files);
        return "Library";  // Name of the Thymeleaf template (library.html)
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<Resource> viewPdfInBrowser(@PathVariable String fileName) throws MalformedURLException {
        Path filePath = directoryPath.resolve(fileName).normalize();

        if (!Files.exists(filePath)) {
            return ResponseEntity.notFound().build();
        }
        Resource resource = new UrlResource(filePath.toUri());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "application/pdf")
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + fileName + "\"")
                .body(resource);
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) throws MalformedURLException {
        Path filePath = directoryPath.resolve(fileName).normalize();

        if (!Files.exists(filePath)) {
            return ResponseEntity.notFound().build();
        }
        Resource resource = new UrlResource(filePath.toUri());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "application/pdf")
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    }

    @DeleteMapping("/delete/{fileName}")
    public ResponseEntity<String> deleteFile(@PathVariable String fileName) throws IOException{
        File file = new File(directoryPath.resolve(fileName).toString());

        if (!file.exists()) {
            return ResponseEntity.notFound().build();}

        if (file.delete()) {
            System.out.println("Deleted the file: " + file.getName());
            return ResponseEntity.ok("File deleted successfully.");
        } else {
            System.out.println("Failed to delete the file.");
            return ResponseEntity.status(500).body("Failed to delete the file.");
        }
    }
}

@Controller
class DynamicWelcome {

    @GetMapping("/Home/Welcome")
    public String viewLibrary(Model model) {
        return "Welcome";
    }
}