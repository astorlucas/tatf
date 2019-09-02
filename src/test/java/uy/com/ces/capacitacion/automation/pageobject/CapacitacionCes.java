package uy.com.ces.capacitacion.automation.pageobject;

public interface CapacitacionCes {

	void goHome(String homepage);

	void login(String userName, String userPass);

	void accessACourse(String textLinkCourse);

	void searchOnCoursePage(String textSearch);

	String getArticleTextOnCoursePage();

	void logout();
}