package uy.com.ces.capacitacion.test.google;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uy.com.ces.capacitacion.automation.ConfigInject;
import uy.com.ces.capacitacion.automation.ConfigInjectResources;
import uy.com.ces.capacitacion.automation.DependencyInject;
import uy.com.ces.capacitacion.automation.pageobject.PageObjectFactory;
import uy.com.ces.capacitacion.automation.pageobject.PageObjectFactoryImpl;
import uy.com.ces.capacitacion.automation.pageobject.google.GoogleSearch;
import uy.com.ces.capacitacion.automation.selenium.DriverManagerAbstract;

/**
 * @author Dardo De León
 */
@ConfigInjectResources({
	"src\\test\\resources\\config.properties", 
	"src\\test\\resources\\credentials.properties" 
})
public class SearchInGoogleTest extends DriverManagerAbstract {

	protected GoogleSearch gs;

	@BeforeEach
	@DependencyInject({ PageObjectFactoryImpl.class })
	public void setUp(PageObjectFactory pageObjectFactory) {
		this.gs = pageObjectFactory.factoryGoogleSearch(driverManager);
	}

	@Test
	public void testSearchTextGoogle(@ConfigInject("pagina.google.home.page") String homepage,
			@ConfigInject("pagina.google.buscar.texto") String text) {

		this.gs.goHome(homepage);

		this.gs.searcHome(text);

		String currentTitle = this.gs.getTitle();
		String spectedTitle = text.concat(" - Buscar con Google");

		assertThat(currentTitle).isEqualTo(spectedTitle);
	}
}
