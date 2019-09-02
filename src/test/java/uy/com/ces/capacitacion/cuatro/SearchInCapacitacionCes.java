package uy.com.ces.capacitacion.cuatro;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uy.com.ces.capacitacion.automation.ConfigInject;
import uy.com.ces.capacitacion.automation.ConfigInjectResources;
import uy.com.ces.capacitacion.automation.DependencyInject;
import uy.com.ces.capacitacion.automation.pageobject.CapacitacionCes;
import uy.com.ces.capacitacion.automation.pageobject.PageObjectFactory;
import uy.com.ces.capacitacion.automation.pageobject.PageObjectFactoryImpl;
import uy.com.ces.capacitacion.automation.selenium.DriverManagerAbstract;

/**
 * @author Dardo De León
 */
@ConfigInjectResources({
	"src\\test\\resources\\config.properties", 
	"src\\test\\resources\\credentials.properties"
})
public class SearchInCapacitacionCes extends DriverManagerAbstract {

	protected CapacitacionCes cc;

	@BeforeEach
	@DependencyInject({ PageObjectFactoryImpl.class })
	public void setUp(PageObjectFactory pageObjectFactory) {
		cc = pageObjectFactory.factoryCapacitacionCes(driverManager);
	}

	@Test
	public void testSearchPostCes(@ConfigInject("pagina.ces.home.page") String homepage,
			@ConfigInject("pagina.ces.user.name") String userName,
			@ConfigInject("pagina.ces.user.pass") String userPass,
			@ConfigInject("pagina.ces.link.curso") String textLinkCourse,
			@ConfigInject("pagina.ces.buscar.texto") String textSearch) {

		cc.goHome(homepage);

		cc.login(userName, userPass);

		cc.accessACourse(textLinkCourse);

		cc.searchOnCoursePage(textSearch);

		String textOnCoursePage = cc.getArticleTextOnCoursePage();

		cc.logout();

		assertThat(textOnCoursePage).containsIgnoringCase(textSearch);
	}

}
