package com.hvktmm.at13.service;

import com.hvktmm.at13.dao.ProductDao;
import com.hvktmm.at13.dao.TransactionHistoryDao;
import com.hvktmm.at13.dao.UserDao;
import com.hvktmm.at13.model.TransactionHistory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Timestamp;
import java.util.Date;

public class HistoryService {
    private ObservableList<TransactionHistory> listImport= FXCollections.observableArrayList();
    private ObservableList<TransactionHistory> listExport= FXCollections.observableArrayList();
    private ObservableList<TransactionHistory> history_list = FXCollections.observableArrayList();
    TransactionHistoryDao historyDao = new TransactionHistoryDao();
    ProductDao productDao = new ProductDao();
    UserDao userDao = new UserDao();

    public ObservableList<TransactionHistory> historyExportData() {
        history_list = historyDao.historyList();
        int idx = 0;
        for(int i=0; i< history_list.size();i++){
            Timestamp dataExport = history_list.get(i). getDateExport();
            if (dataExport != null){
                idx += 1;
                int id = history_list.get(i).getId();
                int amount = history_list.get(i).getAmount();
                String note = history_list.get(i).getNote();
                int product_id = history_list.get(i).getProductId();
                String name_product = productDao.productName(product_id);
                int user_id = history_list.get(i).getUserId();
                String user = userDao.userName(user_id);
                TransactionHistory transactionHistory = new TransactionHistory(amount,idx,dataExport,note,name_product,user);
                listImport.addAll(transactionHistory);
            }
            else {
                continue;
            }

        }
        return listImport;
    }
    public ObservableList<TransactionHistory> historyImportData() {
        history_list = historyDao.historyList();
        int idx = 0;
        for(int i=0; i< history_list.size();i++){
            Timestamp dataImport = history_list.get(i). getDateImport();
            if (dataImport != null){
                idx += 1;
                int id = history_list.get(i).getId();
                int amount = history_list.get(i).getAmount();
                String note = history_list.get(i).getNote();
                int product_id = history_list.get(i).getProductId();
                String name_product = productDao.productName(product_id);
                int user_id = history_list.get(i).getUserId();
                String user = userDao.userName(user_id);
                TransactionHistory transactionHistory = new TransactionHistory(amount,idx,dataImport,note,name_product,user);
                listExport.addAll(transactionHistory);
            }
            else {
                continue;
            }

        }
        return listExport;
    }
}
