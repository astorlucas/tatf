package uy.com.ces.capacitacion.automation.pageobject;

public interface GoogleSearch {

	void goHome(String homepage);

	void searcHome(String text);

	String getTitle();
}