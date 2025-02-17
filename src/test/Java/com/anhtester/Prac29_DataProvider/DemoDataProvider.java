package com.anhtester.Prac29_DataProvider;

import com.anhtester.dataproviders.DataProviderFactory;
import org.openqa.selenium.devtools.v129.autofill.model.Address;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DemoDataProvider {
    @Test(dataProvider = "data_provider_01", dataProviderClass = DataProviderFactory.class)
    public void testDataProvider1(String value) {
        System.out.println("Passed Parameter is: " + value);
    }
    @Test(dataProvider = "data_provider_02", dataProviderClass = DataProviderFactory.class)
    public void testDataProvider2(String a, String b, String c ) {
        System.out.println("Passed Parameter is: " + a);
        System.out.println("Passed Parameter is: " + b);
        System.out.println("Passed Parameter is: " + c);
    }
    @Test(dataProvider = "data_provider_03", dataProviderClass = DataProviderFactory.class)
    public void testDataProvider3(String companyName, String tax, String address, int phone){
        System.out.println("Passed Parameter 1 is: " + companyName);
        System.out.println("Passed Parameter 2 is: " + tax);
        System.out.println("Passed Parameter 3 is: " + address);
        System.out.println("Passed Parameter 4 is: " + phone);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
}
