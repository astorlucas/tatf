package uy.com.ces.capacitacion.test.moodle;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uy.com.ces.capacitacion.automation.ConfigInject;
import uy.com.ces.capacitacion.automation.ConfigInjectResources;
import uy.com.ces.capacitacion.automation.DependencyInject;
import uy.com.ces.capacitacion.automation.selenium.DriverManagerAbstract;
import uy.com.ces.capacitacion.pageobject.PageObjectFactory;
import uy.com.ces.capacitacion.pageobject.PageObjectFactoryImpl;
import uy.com.ces.capacitacion.pageobject.moodle.CesCapacitacion;

/**
 * @author Dardo De León
 */
@ConfigInjectResources({
	"src\\test\\resources\\config.properties", 
	"src\\test\\resources\\credentials.properties"
})
public class SearchInCapacitacionCes extends DriverManagerAbstract {

	protected CesCapacitacion cc;

	@BeforeEach
	@DependencyInject({ PageObjectFactoryImpl.class })
	public void setUp(PageObjectFactory pageObjectFactory) {
		this.cc = pageObjectFactory.factoryCesCapacitacion(driverManager);
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
