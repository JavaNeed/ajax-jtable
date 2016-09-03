package com.programmingfree.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.programmingfree.ajaxdemo.Countries;

public class CountryDAOImpl implements CountryDAO{
	private static final Logger LOGGER = LoggerFactory.getLogger(CountryDAOImpl.class);
	
	private DataSource dataSource; 
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public List<Countries> getAllCountries() {
		LOGGER.debug("~~~~~~ Fetch All the countries ~~~~~~~~~~");
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sql = "select * from country";
		
		List<Countries> countryList = new ArrayList<>();
		
		List<Map<String,Object>> countries = jdbcTemplate.queryForList(sql);
		for (Map<String, Object> country : countries) {
			Countries c = new Countries();
			c.setCode(String.valueOf(country.get("code")));
			c.setName(String.valueOf(country.get("name")));
			c.setContinent(String.valueOf(country.get("continent")));
			c.setRegion(String.valueOf(country.get("region")));
			c.setPopulation(Integer.parseInt(String.valueOf(country.get("population"))));
			c.setCapital(String.valueOf(country.get("")));
			
			countryList.add(c);
		}
		return countryList;
	}
}