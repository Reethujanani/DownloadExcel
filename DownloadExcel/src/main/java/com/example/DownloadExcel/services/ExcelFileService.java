package com.example.DownloadExcel.services;


import com.example.DownloadExcel.entity.Contact;

import java.io.ByteArrayInputStream;
import java.util.List;


public interface ExcelFileService {

    ByteArrayInputStream export(List<Contact> contacts);

}
