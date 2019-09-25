package uy.com.ces.capacitacion.pageobject.egroupware;

public interface Home {

	void goHome(String homepage);

	Dashboard login(String user, String pass);
}
