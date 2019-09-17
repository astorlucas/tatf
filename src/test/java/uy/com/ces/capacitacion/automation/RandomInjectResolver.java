package uy.com.ces.capacitacion.automation;

import java.lang.reflect.Parameter;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

/**
 * Resuelve la inyección de parámetros con valores random.
 * 
 * @author Dardo De León
 */
public class RandomInjectResolver implements ParameterResolver {

	protected String[][] countries = new String[][] { { "AF", "AFGHANISTAN" }, { "AL", "ALBANIA" }, { "DZ", "ALGERIA" },
			{ "AS", "AMERICAN SAMOA" }, { "AD", "ANDORRA" }, { "AO", "ANGOLA" }, { "AI", "ANGUILLA" },
			{ "AQ", "ANTARCTICA" }, { "AG", "ANTIGUA AND BARBUDA" }, { "AR", "ARGENTINA" }, { "AM", "ARMENIA" },
			{ "AW", "ARUBA" }, { "AU", "AUSTRALIA" }, { "AT", "AUSTRIA" }, { "AZ", "AZERBAIJAN" }, { "BS", "BAHAMAS" },
			{ "BH", "BAHRAIN" }, { "BD", "BANGLADESH" }, { "BB", "BARBADOS" }, { "BY", "BELARUS" }, { "BE", "BELGIUM" },
			{ "BZ", "BELIZE" }, { "BJ", "BENIN" }, { "BM", "BERMUDA" }, { "BT", "BHUTAN" }, { "BO", "BOLIVIA" },
			{ "BA", "BOSNIA AND HERZEGOVINA" }, { "BW", "BOTSWANA" }, { "BV", "BOUVET ISLAND" }, { "BR", "BRAZIL" },
			{ "IO", "BRITISH INDIAN OCEAN TERRITORY" }, { "BN", "BRUNEI DARUSSALAM" }, { "BG", "BULGARIA" },
			{ "BF", "BURKINA FASO" }, { "BI", "BURUNDI" }, { "KH", "CAMBODIA" }, { "CM", "CAMEROON" },
			{ "CA", "CANADA" }, { "CV", "CAPE VERDE" }, { "KY", "CAYMAN ISLANDS" },
			{ "CF", "CENTRAL AFRICAN REPUBLIC" }, { "TD", "CHAD" }, { "CL", "CHILE" }, { "CN", "CHINA" },
			{ "CX", "CHRISTMAS ISLAND" }, { "CC", "COCOS (KEELING) ISLANDS" }, { "CO", "COLOMBIA" },
			{ "KM", "COMOROS" }, { "CG", "CONGO" }, { "CD", "CONGO, THE DEMOCRATIC REPUBLIC OF THE" },
			{ "CK", "COOK ISLANDS" }, { "CR", "COSTA RICA" }, { "CI", "COTE D IVOIRE" }, { "HR", "CROATIA" },
			{ "CU", "CUBA" }, { "CY", "CYPRUS" }, { "CZ", "CZECH REPUBLIC" }, { "DK", "DENMARK" }, { "DJ", "DJIBOUTI" },
			{ "DM", "DOMINICA" }, { "DO", "DOMINICAN REPUBLIC" }, { "TP", "EAST TIMOR" }, { "EC", "ECUADOR" },
			{ "EG", "EGYPT" }, { "SV", "EL SALVADOR" }, { "GQ", "EQUATORIAL GUINEA" }, { "ER", "ERITREA" },
			{ "EE", "ESTONIA" }, { "ET", "ETHIOPIA" }, { "FK", "FALKLAND ISLANDS (MALVINAS)" },
			{ "FO", "FAROE ISLANDS" }, { "FJ", "FIJI" }, { "FI", "FINLAND" }, { "FR", "FRANCE" },
			{ "GF", "FRENCH GUIANA" }, { "PF", "FRENCH POLYNESIA" }, { "TF", "FRENCH SOUTHERN TERRITORIES" },
			{ "GA", "GABON" }, { "GM", "GAMBIA" }, { "GE", "GEORGIA" }, { "DE", "GERMANY" }, { "GH", "GHANA" },
			{ "GI", "GIBRALTAR" }, { "GR", "GREECE" }, { "GL", "GREENLAND" }, { "GD", "GRENADA" },
			{ "GP", "GUADELOUPE" }, { "GU", "GUAM" }, { "GT", "GUATEMALA" }, { "GN", "GUINEA" },
			{ "GW", "GUINEA-BISSAU" }, { "GY", "GUYANA" }, { "HT", "HAITI" },
			{ "HM", "HEARD ISLAND AND MCDONALD ISLANDS" }, { "VA", "HOLY SEE (VATICAN CITY STATE)" },
			{ "HN", "HONDURAS" }, { "HK", "HONG KONG" }, { "HU", "HUNGARY" }, { "IS", "ICELAND" }, { "IN", "INDIA" },
			{ "ID", "INDONESIA" }, { "IR", "IRAN, ISLAMIC REPUBLIC OF" }, { "IQ", "IRAQ" }, { "IE", "IRELAND" },
			{ "IL", "ISRAEL" }, { "IT", "ITALY" }, { "JM", "JAMAICA" }, { "JP", "JAPAN" }, { "JO", "JORDAN" },
			{ "KZ", "KAZAKSTAN" }, { "KE", "KENYA" }, { "KI", "KIRIBATI" },
			{ "KP", "KOREA DEMOCRATIC PEOPLES REPUBLIC OF" }, { "KR", "KOREA REPUBLIC OF" }, { "KW", "KUWAIT" },
			{ "KG", "KYRGYZSTAN" }, { "LA", "LAO PEOPLES DEMOCRATIC REPUBLIC" }, { "LV", "LATVIA" },
			{ "LB", "LEBANON" }, { "LS", "LESOTHO" }, { "LR", "LIBERIA" }, { "LY", "LIBYAN ARAB JAMAHIRIYA" },
			{ "LI", "LIECHTENSTEIN" }, { "LT", "LITHUANIA" }, { "LU", "LUXEMBOURG" }, { "MO", "MACAU" },
			{ "MK", "MACEDONIA, THE FORMER YUGOSLAV REPUBLIC OF" }, { "MG", "MADAGASCAR" }, { "MW", "MALAWI" },
			{ "MY", "MALAYSIA" }, { "MV", "MALDIVES" }, { "ML", "MALI" }, { "MT", "MALTA" },
			{ "MH", "MARSHALL ISLANDS" }, { "MQ", "MARTINIQUE" }, { "MR", "MAURITANIA" }, { "MU", "MAURITIUS" },
			{ "YT", "MAYOTTE" }, { "MX", "MEXICO" }, { "FM", "MICRONESIA, FEDERATED STATES OF" },
			{ "MD", "MOLDOVA, REPUBLIC OF" }, { "MC", "MONACO" }, { "MN", "MONGOLIA" }, { "MS", "MONTSERRAT" },
			{ "MA", "MOROCCO" }, { "MZ", "MOZAMBIQUE" }, { "MM", "MYANMAR" }, { "NA", "NAMIBIA" }, { "NR", "NAURU" },
			{ "NP", "NEPAL" }, { "NL", "NETHERLANDS" }, { "AN", "NETHERLANDS ANTILLES" }, { "NC", "NEW CALEDONIA" },
			{ "NZ", "NEW ZEALAND" }, { "NI", "NICARAGUA" }, { "NE", "NIGER" }, { "NG", "NIGERIA" }, { "NU", "NIUE" },
			{ "NF", "NORFOLK ISLAND" }, { "MP", "NORTHERN MARIANA ISLANDS" }, { "NO", "NORWAY" }, { "OM", "OMAN" },
			{ "PK", "PAKISTAN" }, { "PW", "PALAU" }, { "PS", "PALESTINIAN TERRITORY, OCCUPIED" }, { "PA", "PANAMA" },
			{ "PG", "PAPUA NEW GUINEA" }, { "PY", "PARAGUAY" }, { "PE", "PERU" }, { "PH", "PHILIPPINES" },
			{ "PN", "PITCAIRN" }, { "PL", "POLAND" }, { "PT", "PORTUGAL" }, { "PR", "PUERTO RICO" }, { "QA", "QATAR" },
			{ "RE", "REUNION" }, { "RO", "ROMANIA" }, { "RU", "RUSSIAN FEDERATION" }, { "RW", "RWANDA" },
			{ "SH", "SAINT HELENA" }, { "KN", "SAINT KITTS AND NEVIS" }, { "LC", "SAINT LUCIA" },
			{ "PM", "SAINT PIERRE AND MIQUELON" }, { "VC", "SAINT VINCENT AND THE GRENADINES" }, { "WS", "SAMOA" },
			{ "SM", "SAN MARINO" }, { "ST", "SAO TOME AND PRINCIPE" }, { "SA", "SAUDI ARABIA" }, { "SN", "SENEGAL" },
			{ "SC", "SEYCHELLES" }, { "SL", "SIERRA LEONE" }, { "SG", "SINGAPORE" }, { "SK", "SLOVAKIA" },
			{ "SI", "SLOVENIA" }, { "SB", "SOLOMON ISLANDS" }, { "SO", "SOMALIA" }, { "ZA", "SOUTH AFRICA" },
			{ "GS", "SOUTH GEORGIA AND THE SOUTH SANDWICH ISLANDS" }, { "ES", "SPAIN" }, { "LK", "SRI LANKA" },
			{ "SD", "SUDAN" }, { "SR", "SURINAME" }, { "SJ", "SVALBARD AND JAN MAYEN" }, { "SZ", "SWAZILAND" },
			{ "SE", "SWEDEN" }, { "CH", "SWITZERLAND" }, { "SY", "SYRIAN ARAB REPUBLIC" }, { "TW", "TAIWAN/TAIPEI" },
			{ "TJ", "TAJIKISTAN" }, { "TZ", "TANZANIA, UNITED REPUBLIC OF" }, { "TH", "THAILAND" }, { "TG", "TOGO" },
			{ "TK", "TOKELAU" }, { "TO", "TONGA" }, { "TT", "TRINIDAD AND TOBAGO" }, { "TN", "TUNISIA" },
			{ "TR", "TURKEY" }, { "TM", "TURKMENISTAN" }, { "TC", "TURKS AND CAICOS ISLANDS" }, { "TV", "TUVALU" },
			{ "UG", "UGANDA" }, { "UA", "UKRAINE" }, { "AE", "UNITED ARAB EMIRATES" }, { "GB", "UNITED KINGDOM" },
			{ "US", "UNITED STATES" }, { "UM", "UNITED STATES MINOR OUTLYING ISLANDS" }, { "UY", "URUGUAY" },
			{ "UZ", "UZBEKISTAN" }, { "VU", "VANUATU" }, { "VE", "VENEZUELA" }, { "VN", "VIET NAM" },
			{ "VG", "VIRGIN ISLANDS, BRITISH" }, { "VI", "VIRGIN ISLANDS, U.S." }, { "WF", "WALLIS AND FUTUNA" },
			{ "EH", "WESTERN SAHARA" }, { "YE", "YEMEN" }, { "YU", "YUGOSLAVIA" }, { "ZM", "ZAMBIA" },
			{ "ZW", "ZIMBABWE" }, };

	/**
	 * Retorna true si el parámetro fue decorado con la anotación RandomInject
	 */
	@Override
	public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
			throws ParameterResolutionException {

		return getAnnotation(parameterContext) != null;
	}

	/**
	 * Retorna el valor que debe inyectarse como parámetro, en base al tipo de dato
	 * del parámetro, se genera y retorna un valor random
	 */
	@Override
	public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
			throws ParameterResolutionException {

		RandomInject annotation = getAnnotation(parameterContext);

		if (annotation == null) {
			throw new ParameterResolutionException("No se encotró el decorador @RandomInject.");
		}

		Object result = null;

		int options = annotation.options().length;
		if (options == 0) {
			Class<?> type = parameterContext.getParameter().getType();

			if (type.equals(String.class)) {
				switch (annotation.type()) {
				case RandomInject.COUNTRY_ID:
				case RandomInject.COUNTRY_NAME:
					result = this.getCountry(annotation.type());
					break;
				case RandomInject.COLOR_HEX:
					result = this.getColorHex();
					break;
				default:
					result = RandomStringUtils.randomAlphanumeric(annotation.max());
				}
			} else if (type.equals(Integer.class)) {
				result = this.getInteger(annotation.min(), annotation.max());
			} else if (type.equals(Float.class)) {
				result = this.getFloat(annotation.min(), annotation.max());
			} else {
				throw new ParameterResolutionException(
						"El tipo de parámetro " + type + " no está soportado por RandomInjectResolver.");
			}
		} else {
			result = annotation.options()[this.getInteger(options)];
		}

		return result;
	}

	/**
	 * @param parameterContext contexto de ejecución del parámetro.
	 * @return Retorna la anotación RandomInject asociada al parámetro recibido en
	 *         el contexto de ejecución.
	 */
	protected static RandomInject getAnnotation(ParameterContext parameterContext) {

		Parameter param = parameterContext.getParameter();

		return param.getAnnotation(RandomInject.class);
	}

	/**
	 * @param max Maximo valor a retornar
	 * @return Retorna un número entre 0 y max - 1
	 */
	public Integer getInteger(Integer max) {
		return this.getInteger(0, max);
	}

	/**
	 * @param min Minimo valor a retornar
	 * @param max Maximo valor a retornar
	 * @return Retorna un número entre min y max - 1
	 */
	public Integer getInteger(int min, int max) {
		return RandomUtils.nextInt(min, max);
	}

	/**
	 * @param min Minimo valor a retornar
	 * @param max Maximo valor a retornar
	 * @return Retorna un número de coma flotante, entre los enteros min y max - 1
	 */
	public Float getFloat(int min, int max) {
		return RandomUtils.nextFloat(min, max);
	}

	/**
	 * @param RandomInject.COUNTRY_ID o RandomInject.COUNTRY_NAME.
	 * @return por defecto retorna un nombre de pais; si key =
	 *         RandomInject.COUNTRY_ID retorna un identificador ISO_3166-1_alpha-2
	 *         de páis.
	 */
	public String getCountry(int key) {

		key = RandomInject.COUNTRY_ID == key ? 0 : 1;

		return countries[this.getInteger(countries.length)][key];
	}

	/**
	 * @param max Número mayor o igual a cero.
	 * @return Retorna una cadena de texto, con caracteres alfanumericos aleatorios.
	 */
	public String getString(int max) {
		return RandomStringUtils.randomAlphanumeric(max);
	}

	/**
	 * @return Retorna un color en formato hexadecimal, precedido de #
	 */
	public String getColorHex() {

		StringBuilder color = new StringBuilder("#");

		for (int p = 0; p < 6; p++) {
			color.append(Integer.toHexString(this.getInteger(16)));
		}

		return color.toString();
	}
}
