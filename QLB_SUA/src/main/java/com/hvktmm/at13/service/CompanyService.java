package com.hvktmm.at13.service;

import com.hvktmm.at13.dao.CompayDao;
import com.hvktmm.at13.model.Company;
import com.hvktmm.at13.model.CompanyItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CompanyService {
    private  ObservableList<CompanyItem> data= FXCollections.observableArrayList();
    private ObservableList<Company> company_list = FXCollections.observableArrayList();
    CompayDao compayDao = new CompayDao();

    public ObservableList<CompanyItem> CompanyData() {

        company_list = compayDao.companyList();
        for(int i=0; i< company_list.size();i++){
           String name =  company_list.get(i).getName();
           String address = company_list.get(i).getAddress();
           String  tell = company_list.get(i).getPhone_number();
           CompanyItem companyItem =new CompanyItem(name,address,tell);
           data.addAll(companyItem);

        }
        return data;
    }

}
