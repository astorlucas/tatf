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
import uy.com.ces.capacitacion.automation.pageobject.egroupware.ContactForm;
import uy.com.ces.capacitacion.automation.pageobject.egroupware.ContactList;
import uy.com.ces.capacitacion.automation.pageobject.egroupware.Dashboard;
import uy.com.ces.capacitacion.automation.pageobject.egroupware.Home;
import uy.com.ces.capacitacion.automation.selenium.DriverManagerAbstract;

/**
 * @author Dardo De León
 */
@ConfigInjectResources({ 
	"src\\test\\resources\\config.properties", 
	"src\\test\\resources\\credentials.properties" 
})
public class EGroupwareContactTest extends DriverManagerAbstract {

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
	public void testAddContact(@RandomInject(max = 9) String namePrefix, @RandomInject(max = 9) String nameGiven,
			@RandomInject(max = 9) String nameMiddle, @RandomInject(max = 9) String nameFamily,
			@RandomInject(max = 9) String nameSuffix,

			@RandomInject(max = 9) String title,

			@RandomInject(max = 9) String role,

			@RandomInject(max = 999) Integer room,

			@RandomInject(max = 9) String orgName, @RandomInject(max = 9) String orgUnit,

			@RandomInject(max = 9) String adrOneStreet, @RandomInject(max = 9) String adrOnePostalcode,

			@RandomInject(type = RandomInject.COUNTRY_NAME) String adrOneCountryname,

			@RandomInject(max = 9999) Integer telWork, @RandomInject(max = 9999) Integer telCell,
			@RandomInject(max = 9999) Integer telHome) {

		ContactList contactos = this.dashboard.goContactos();

		ContactForm contacto = contactos.newContacto();

		contactos = contacto.createContacto(namePrefix, nameGiven, nameMiddle, nameFamily, nameSuffix, title, role,
				room, orgName, orgUnit, adrOneStreet, adrOnePostalcode, adrOneCountryname, telWork, telCell, telHome);

		String textMainForm = contactos.getTextMainForm();

		contactos.findContacto(namePrefix);

		String resultSearch = contactos.getTextMainForm();

		contactos.delContacto(nameFamily);

		this.home = contactos.logout();

		assertThat(textMainForm)
				.as("Verifica que el mensaje de contacto creado, se encuentra en el contenedor principal.")
				.contains(contacto.getMessageSaved());

		assertThat(resultSearch)
				.as("Verifica que el nombre del contacto, se encuentre en el contenedor principal.")
				.contains(nameFamily);
	}
}
