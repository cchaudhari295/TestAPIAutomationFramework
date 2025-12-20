package com.demo.csv;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class ReadCSVFile {

	public static void main(String[] args) throws IOException, CsvException {

		/*
		 * Read CSV File in Java
		 * 
		 * File file=new File("C:\\Users\\crane\\SDET\\JAVA MODULE\\APITestAutomationProject\\src\\main\\resources\\testData\\LoginCreds.csv"); 
		 * FileReader fr=new FileReader(file);
		 * 
		 */

		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("testData/LoginCreds.csv");
		InputStreamReader isr = new InputStreamReader(is);

		CSVReader csvReader = new CSVReader(isr);

		List<String[]> dataList = csvReader.readAll();

		for (String[] dataArray : dataList) {
			System.out.println(dataArray[0] + " "+ dataArray[1]);
		}
	}
}
