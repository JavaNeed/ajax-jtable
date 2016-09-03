package com.programmingfree.ajaxdemo;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;


@WebServlet("/PopulateTable")
public class PopulateTable extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private FetchData fetchData = null;

    public PopulateTable() {
        fetchData = new FetchData();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
				
		List<Countries> country = fetchData.getAllCountries();
		response.getWriter().print(getJsonArray(country));
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	private JsonArray getJsonArray(List<Countries> countries){
		Gson gson = new Gson();
		JsonElement element = gson.toJsonTree(countries, new TypeToken<List<Countries>>() {}.getType());
		JsonArray jsonArray = element.getAsJsonArray();
		
		return jsonArray;
	}

}
