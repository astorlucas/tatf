package uy.com.ces.capacitacion.cuatro;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import uy.com.ces.capacitacion.automation.ConfigInject;
import uy.com.ces.capacitacion.automation.ConfigInjectResources;
import uy.com.ces.capacitacion.automation.DependencyInject;
import uy.com.ces.capacitacion.automation.pageobject.PageObjectFactory;
import uy.com.ces.capacitacion.automation.pageobject.PageObjectFactoryImpl;
import uy.com.ces.capacitacion.automation.pageobject.moodle.CapacitacionCes;
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
		this.cc = pageObjectFactory.factoryCapacitacionCes(driverManager);
	}

	@Test
	public void testSearchPostCes(@ConfigInject("pagina.ces.home.page") String homepage,
			@ConfigInject("pagina.ces.user.name") String userName,
			@ConfigInject("pagina.ces.user.pass") String userPass,
			@ConfigInject("pagina.ces.link.curso") String textLinkCourse,
			@ConfigInject("pagina.ces.buscar.texto") String textSearch) {

		this.cc.goHome(homepage);

		this.cc.login(userName, userPass);

		this.cc.accessACourse(textLinkCourse);

		this.cc.searchOnCoursePage(textSearch);

		String textOnCoursePage = this.cc.getArticleTextOnCoursePage();

		this.cc.logout();

		assertThat(textOnCoursePage).containsIgnoringCase(textSearch);
	}

}
