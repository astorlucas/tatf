package uy.com.ces.capacitacion.cuatro;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uy.com.ces.capacitacion.automation.ConfigInject;
import uy.com.ces.capacitacion.automation.ConfigInjectResources;
import uy.com.ces.capacitacion.automation.DependencyInject;
import uy.com.ces.capacitacion.automation.pageobject.GoogleGmail;
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
public class LoginGmailTest extends DriverManagerAbstract {

	GoogleGmail gm;

	@BeforeEach
	@DependencyInject({ PageObjectFactoryImpl.class })
	public void setUp(PageObjectFactory pageObjectFactory) {
		gm = pageObjectFactory.factoryGoogleGmail(driverManager);
	}

	@Test
	public void testLoginGmail(@ConfigInject("pagina.google.home.page") String homepage,
			@ConfigInject("pagina.google.user.name") String userName,
			@ConfigInject("pagina.google.user.pass") String userPass) {

		gm.goHome(homepage);

		gm.login(userName, userPass);

		gm.goGmailApp();

		String currentTitle = gm.getTitle();

		gm.logout();

		/**
		 * Recibidos - user@gmail.com - Gmail Recibidos (1) - user@gmail.com - Gmail
		 * Recibidos | - user@gmail.com - Gmail
		 */
		String startTitle = "Recibidos";
		String endTitle = " - " + userName + " - Gmail";

		assertThat(currentTitle).startsWith(startTitle).endsWith(endTitle);
	}
}
