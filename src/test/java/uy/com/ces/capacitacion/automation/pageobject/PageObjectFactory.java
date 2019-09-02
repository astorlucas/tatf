package uy.com.ces.capacitacion.automation.pageobject;

import uy.com.ces.capacitacion.automation.selenium.DriverManager;

public interface PageObjectFactory {

	CapacitacionCes factoryCapacitacionCes(DriverManager driverManager);

	GoogleGmail factoryGoogleGmail(DriverManager driverManager);

	GoogleSearch factoryGoogleSearch(DriverManager driverManager);
}
