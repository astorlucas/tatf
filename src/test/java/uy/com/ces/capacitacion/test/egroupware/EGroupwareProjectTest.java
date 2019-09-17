package uy.com.ces.capacitacion.test.egroupware;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import uy.com.ces.capacitacion.automation.ConfigInject;
import uy.com.ces.capacitacion.automation.ConfigInjectResources;
import uy.com.ces.capacitacion.automation.DependencyInject;
import uy.com.ces.capacitacion.automation.RandomInject;
import uy.com.ces.capacitacion.automation.pageobject.PageObjectFactory;
import uy.com.ces.capacitacion.automation.pageobject.PageObjectFactoryImpl;
import uy.com.ces.capacitacion.automation.pageobject.egroupware.CategoryForm;
import uy.com.ces.capacitacion.automation.pageobject.egroupware.CategoryList;
import uy.com.ces.capacitacion.automation.pageobject.egroupware.Dashboard;
import uy.com.ces.capacitacion.automation.pageobject.egroupware.Home;
import uy.com.ces.capacitacion.automation.pageobject.egroupware.ProjectForm;
import uy.com.ces.capacitacion.automation.pageobject.egroupware.ProjectList;
import uy.com.ces.capacitacion.automation.pageobject.egroupware.ProjectView;
import uy.com.ces.capacitacion.automation.selenium.DriverManagerAbstract;

/**
 * @author Dardo De León
 */
@ConfigInjectResources({ 
	"src\\test\\resources\\config.properties", 
	"src\\test\\resources\\credentials.properties" 
})
public class EGroupwareProjectTest extends DriverManagerAbstract {

	protected Home home;

	protected Dashboard dashboard;

	@BeforeEach
	@DependencyInject({ PageObjectFactoryImpl.class })
	public void setUp(PageObjectFactory pageObjectFactory, @ConfigInject("pagina.egroupware.home.page") String homepage,
			@ConfigInject("pagina.egroupware.user.name") String userName,
			@ConfigInject("pagina.egroupware.user.pass") String userPass) {

		this.home = pageObjectFactory.factoryEgroupwareHome(driverManager);

		this.home.goHome(homepage);

		this.dashboard = this.home.login(userName, userPass);
	}

	@Tag("dev")
	@Test
	public void testAddProject(@RandomInject(max = 15) String categoryName,
			@RandomInject(max = 15) String categoryDescription,
			@RandomInject(options = { "Blocks", "Books", "Charts", "Clipboard", "Communications" }) String categoryType,
			@RandomInject(type = RandomInject.COLOR_HEX) String categoryColor,

			@RandomInject(max = 10) String projectNumber, @RandomInject(max = 10) String projectName,
			@RandomInject(min = 1, max = 10) Integer projectPriority) {

		// setUp
		CategoryList categories = this.prepareCategory(this.dashboard, categoryName, categoryDescription, categoryType,
				categoryColor);

		// addProject
		ProjectList projects = categories.goProjects();

		ProjectForm project = projects.newProject();

		projects = project.createProject(projectNumber, projectName, categoryName, projectPriority);

		String textMainForm = projects.getTextMainForm();

		// delProject
		projects.delProject(projectName);

		// setDown
		this.home = this.removeCategory(projects, categoryName);

		assertThat(textMainForm)
				.as("Verifica que el nombre del proyecto, se encuentre en el contenedor principal.")
				.contains(projectName);

		assertThat(textMainForm)
				.as("Verifica que el mensaje de proyecto creado, se encuentra en el contenedor principal.")
				.contains(project.getMessageSaved());

	}

	@Tag("dev")
	@Test
	public void testGetProject(@RandomInject(max = 15) String categoryName,
			@RandomInject(max = 15) String categoryDescription,
			@RandomInject(options = { "Configure", "Connect", "Finance", "Gear", "Hardware" }) String categoryType,
			@RandomInject(type = RandomInject.COLOR_HEX) String categoryColor,

			@RandomInject(max = 10) String projectNumber, @RandomInject(max = 10) String projectName,
			@RandomInject(min = 1, max = 10) Integer projectPriority) {

		// setUp
		CategoryList categories = this.prepareCategory(this.dashboard, categoryName, categoryDescription, categoryType,
				categoryColor);
		ProjectList projects = this.prepareProject(categories, projectNumber, projectName, categoryName,
				projectPriority);

		// getProject
		ProjectView projectView = projects.viewProject(projectName);

		String projectViewText = projectView.getViewText();

		projects = projectView.goProjects();

		// delProject
		projects.delProject(projectName);

		// setDown
		this.home = this.removeCategory(projects, categoryName);

		assertThat(projectViewText)
				.as("Verifica que la vista del proyecto, posee el número asociado al proyecto buscado.")
				.contains(projectNumber);
		assertThat(projectViewText)
				.as("Verifica que la vista del proyecto, posee la nombre asociado al proyecto buscado.")
				.contains(projectName);
		assertThat(projectViewText)
				.as("Verifica que la vista del proyecto, posee la categoría asociada al proyecto buscado.")
				.contains(categoryName);
	}

	@Tag("dev")
	@Test
	public void testAddProjectResorce(@ConfigInject("pagina.egroupware.resource.name") String resourceMember,
			@RandomInject(min = 1, max = 10) Integer resourceAvailibility,
			@RandomInject(options = { "Coordinator", "Accounting", "Assistant", "Projectmember" }) String resourceRole,

			@RandomInject(max = 15) String categoryName, @RandomInject(max = 15) String categoryDescription,
			@RandomInject(options = { "Help", "Idea", "Important", "Info", "Linux" }) String categoryType,
			@RandomInject(type = RandomInject.COLOR_HEX) String categoryColor,

			@RandomInject(max = 10) String projectNumber, @RandomInject(max = 10) String projectName,
			@RandomInject(min = 1, max = 10) Integer projectPriority) {

		// setUp
		CategoryList categories = this.prepareCategory(this.dashboard, categoryName, categoryDescription, categoryType,
				categoryColor);
		ProjectList projects = this.prepareProject(categories, projectNumber, projectName, categoryName,
				projectPriority);

		// addResource
		ProjectForm project = projects.editProject(projectName);

		projects = project.addResource(resourceMember, resourceAvailibility, resourceRole);

		// getProject
		ProjectView projectView = projects.viewProject(projectName);

		String projectViewText = projectView.getMembersText();

		projects = projectView.goProjects();

		// delProject
		projects.delProject(projectName);

		// setDown
		this.home = this.removeCategory(projects, categoryName);

		assertThat(projectViewText)
				.as("Verifica que la zona de miebros del proyecto, posee el identificador de miembro asignado.")
				.contains(resourceMember);
	}

	/**
	 * Navega desde el dashboar al listado de categorias, crea una nuvea y retorna
	 * el listado de categorias.
	 * 
	 * @param db                  Dashboard
	 * @param categoryName        Nombre de categoria
	 * @param categoryDescription Descripción de categoria
	 * @param categoryType        Tipo de categoria
	 * @param categoryColor       Color de categoria
	 * @return Listado de categorias
	 */
	protected CategoryList prepareCategory(Dashboard db, String categoryName, String categoryDescription,
			String categoryType, String categoryColor) {

		ProjectList projects = db.goProjects();

		CategoryList categories = projects.goCategories();

		CategoryForm category = categories.newCategory();

		return category.createCategory(categoryName, categoryDescription, categoryColor, categoryType);
	}

	/**
	 * Recibe el listado de proyectos, navega al listado de categorias, elimina la
	 * categoría que coincide con el nombre recibido, la elimina, cierra la sessión
	 * y retorna la pagina de inicio.
	 * 
	 * @param pj           Lista de proyectos
	 * @param categoryName Nombre de la categoria que debe ser eliminada
	 * @return Pagina inicial del sitio
	 */
	protected Home removeCategory(ProjectList pj, String categoryName) {

		CategoryList categories = pj.goCategories();

		categories.findCategory(categoryName);
		
		categories.delCategory(categoryName);

		return categories.logout();
	}

	/**
	 * Navega desde el listado de categorias al formulario de alta de proyectos,
	 * crea uno nuevo y retorna el listado de proyectos.
	 * 
	 * @param ctg             Listado de categorias
	 * @param projectNumber   Número del proyecto
	 * @param projectName     Nombre del proyecto
	 * @param categoryName    Categoria del proyecto
	 * @param projectPriority Prioridad del proyecto
	 * @return Retorna la lista de proyectos
	 */
	protected ProjectList prepareProject(CategoryList ctg, String projectNumber, String projectName,
			String categoryName, Integer projectPriority) {

		ProjectList projects = ctg.goProjects();

		ProjectForm project = projects.newProject();

		return project.createProject(projectNumber, projectName, categoryName, projectPriority);
	}
}
