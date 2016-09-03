package com.programmingfree.ajaxdemo;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.programmingfree.dao.CountryDAO;


public class FetchData {
    
    private CountryDAO countryDAO = null;
    
    public FetchData(){
    	ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
    	countryDAO = ctx.getBean("countryDAOTemplate", CountryDAO.class);
    }
  
    public List<Countries> getAllCountries() {
        List<Countries> countryList = countryDAO.getAllCountries();
        return countryList;
    }
}