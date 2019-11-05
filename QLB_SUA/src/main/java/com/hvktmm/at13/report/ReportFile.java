package com.hvktmm.at13.report;

import com.hvktmm.at13.model.TransactionHistory;
import com.hvktmm.at13.model.UserReport;
import javafx.collections.ObservableList;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ReportFile {

    public void report(String nameFile, ObservableList list, String namePrameter, String jasper) throws FileNotFoundException, JRException {
        String linkFile = "C:\\Users\\Admin\\JaspersoftWorkspace\\BaoCaoBanSua\\";
        String linkData = "E:\\data\\";
        String output = linkData + nameFile;
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
        Map<String, Object> prameters = new HashMap<>();
        prameters.put(namePrameter, dataSource);
        InputStream inputStream = new FileInputStream(new File(linkFile + jasper));
        JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,prameters, new JREmptyDataSource());
        JasperViewer.viewReport(jasperPrint);
        OutputStream outputStream = new FileOutputStream(new File(output));
        JasperExportManager.exportReportToPdfStream(jasperPrint,outputStream);
    }
}
