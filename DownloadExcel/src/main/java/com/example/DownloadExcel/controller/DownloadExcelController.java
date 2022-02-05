package com.example.DownloadExcel.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.example.DownloadExcel.entity.Contact;
import com.example.DownloadExcel.repository.ContactRepository;
import com.example.DownloadExcel.services.ExcelFileService;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class DownloadExcelController {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ExcelFileService excelFileService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/downloadExcelFile")
    public void downloadExcelFile(HttpServletResponse response) throws IOException {
        try {
            List<Contact> contacts = contactRepository.findAll();
            ByteArrayInputStream byteArrayInputStream = excelFileService.export(contacts);
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=contacts.xlsx");
            IOUtils.copy(byteArrayInputStream, response.getOutputStream());
        } catch (IOException e) {
            e.getMessage();
        }
    }

}
