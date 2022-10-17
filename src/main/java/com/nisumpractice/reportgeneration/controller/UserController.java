package com.nisumpractice.reportgeneration.controller;

import com.lowagie.text.DocumentException;
import com.nisumpractice.reportgeneration.model.User;
import com.nisumpractice.reportgeneration.repository.UserRepository;
import com.nisumpractice.reportgeneration.util.PDFExporter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController

public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/users")
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @GetMapping("/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<User> listUsers = userRepository.findAll();

        PDFExporter exporter = new PDFExporter(listUsers);
        exporter.export(response);

    }
}
