package uy.com.ces.capacitacion.pageobject.google;

public interface GoogleGmail {

	void goHome(String homepage);

	void login(String userName, String userPass);

	void goGmailApp();

	String getTitle();

	void logout();

}
