package com.hvktmm.at13.report;

import com.hvktmm.at13.model.UserReport;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ReportUser {
    public static void main(String[] args) throws FileNotFoundException, JRException {
        ArrayList<UserReport> list = new ArrayList<>();
        String output = "E:\\data\\" + "test.pdf";
        UserReport userReport = new UserReport(1,"son","san pham",3);
        list.add(userReport);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
        Map<String, Object> prameters = new HashMap<>();
        prameters.put("parameter", dataSource);
        InputStream inputStream = new FileInputStream(new File("E:\\data\\quanLyNguoiBan.jrxml"));
        JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,prameters, new JREmptyDataSource());
        JasperViewer.viewReport(jasperPrint);
        OutputStream outputStream = new FileOutputStream(new File(output));
        JasperExportManager.exportReportToPdfStream(jasperPrint,outputStream);


    }
}