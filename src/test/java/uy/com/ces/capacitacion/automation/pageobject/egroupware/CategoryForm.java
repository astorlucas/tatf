package uy.com.ces.capacitacion.automation.pageobject.egroupware;

public interface CategoryForm {
	boolean validPage();

	/**
	 * @param name
	 * @param description
	 * @param color
	 * @param type        "Blocks", "Books", "Charts", "Clipboard",
	 *                    "Communications", "Configure", "Connect", "Finance",
	 *                    "Gear", "Hardware", "Help", "Idea", "Important", "Info",
	 *                    "Linux", "Mac", "Open_book", "Open_folder", "People",
	 *                    "Person", "Screen", "Security", "Star", "Stats", "Table",
	 *                    "Winclose", "Windows", "World"
	 * @return
	 */
	CategoryList createCategory(String name, String description, String color, String type);
}
