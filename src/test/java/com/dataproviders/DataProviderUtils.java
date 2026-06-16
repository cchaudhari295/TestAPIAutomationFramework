package com.dataproviders;

import java.util.Iterator;

import org.testng.annotations.DataProvider;

import com.api.request.model.CreateJobPayload;
import com.api.utils.FakerDataGenerator;

public class DataProviderUtils {

	
	
	@DataProvider(name="CreateJobAPIFakerDataProvider",parallel=true)
	public static Iterator<CreateJobPayload> createJobFakeDataProvider(){
		String fakerCount=System.getProperty("fakeCount", "5");
		int fakerCountInt=Integer.parseInt(fakerCount);
		Iterator<CreateJobPayload> payloadIterator= FakerDataGenerator.generateFakeCreateJobPayload(fakerCountInt);	
		return payloadIterator;
	}
}
