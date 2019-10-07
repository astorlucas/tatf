package uy.com.ces.capacitacion.test.egroupware;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import uy.com.ces.capacitacion.automation.ConfigInject;
import uy.com.ces.capacitacion.automation.ConfigInjectResources;
import uy.com.ces.capacitacion.automation.DependencyInject;
import uy.com.ces.capacitacion.automation.RandomInject;
import uy.com.ces.capacitacion.automation.selenium.DriverManagerAbstract;
import uy.com.ces.capacitacion.pageobject.PageObjectFactory;
import uy.com.ces.capacitacion.pageobject.PageObjectFactoryImpl;
import uy.com.ces.capacitacion.pageobject.egroupware.ContactForm;
import uy.com.ces.capacitacion.pageobject.egroupware.ContactList;
import uy.com.ces.capacitacion.pageobject.egroupware.Dashboard;
import uy.com.ces.capacitacion.pageobject.egroupware.Home;
import uy.com.ces.capacitacion.pageobject.egroupware.ContactView;

/**
 * @author Dardo De Le�n
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
		
		ContactView viewContact = contactos.viewContacto(nameFamily);
		
		String contactViewText = viewContact.getViewText();

		contactos = viewContact.goContactos();
		
		contactos = contactos.findContacto(namePrefix);

		contactos = contactos.delContacto(nameFamily);

		this.home = contactos.logout();

		// valida mensaje de creado y presentaci�n en listado
		assertThat(textMainForm)
				.as("Verifica que el mensaje de contacto creado, se encuentra en el contenedor principal.")
				.contains(contacto.getMessageSaved());

		assertThat(resultSearch)
				.as("Verifica que el nombre del contacto, se encuentre en el contenedor principal.")
				.contains(nameFamily);

		// valida si los datos del nuevo contacto, estan disponibles

		assertThat(contactViewText)
			.as("Verifica que el nombre de pila, se encuentre en la p�gina de vista.")
			.contains(nameGiven);

		assertThat(contactViewText)
			.as("Verifica que el apellido, se encuentre en la p�gina de vista.")
			.contains(nameFamily);
		
		assertThat(contactViewText)
			.as("Verifica que el t�tulo, se encuentre en la p�gina de vista.")
			.contains(title);

		assertThat(contactViewText)
			.as("Verifica que la oficina, se encuentre en la p�gina de vista.")
			.contains(room.toString());

		assertThat(contactViewText)
			.as("Verifica que el nombre de la organizaci�n, se encuentre en la p�gina de vista.")
			.contains(orgName);

		assertThat(contactViewText)
			.as("Verifica que el nombre de la unidad de la organizaci�n, se encuentre en la p�gina de vista.")
			.contains(orgUnit);

		assertThat(contactViewText)
			.as("Verifica que la direcci�n, se encuentre en la p�gina de vista.")
			.contains(adrOneStreet);

		assertThat(contactViewText)
			.as("Verifica que el c�digo postal, se encuentre en la p�gina de vista.")
			.contains(adrOnePostalcode);

		assertThat(contactViewText)
			.as("Verifica que el pa�s, se encuentre en la p�gina de vista.")
			.contains(adrOneCountryname);

		assertThat(contactViewText)
			.as("Verifica que el t�lefono del trabajo, se encuentre en la p�gina de vista.")
			.contains(telWork.toString());

		assertThat(contactViewText)
			.as("Verifica que el celular, se encuentre en la p�gina de vista.")
			.contains(telCell.toString());

		assertThat(contactViewText)
			.as("Verifica que el t�lefono de su casa, se encuentre en la p�gina de vista.")
			.contains(telHome.toString());

		// validaciones pendientes
		// namePrefix - Verifica que el prefijo de nombre, se encuentre en la p�gina de vista.
		// nameMiddle - Verifica que el segundo nombre, se encuentre en la p�gina de vista.
		// nameSuffix - Verifica que el sufijo de nombre, se encuentre en la p�gina de vista. 
		// role - Verifica que el role, se encuentre en la p�gina de vista.
	}
}
