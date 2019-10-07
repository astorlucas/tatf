package uy.com.ces.capacitacion.suites;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.UseTechnicalNames;
import org.junit.runner.RunWith;

@UseTechnicalNames
@RunWith(JUnitPlatform.class)
@SelectPackages({
	"uy.com.ces.capacitacion.test.moodle"
})
public class CesMoodleSuite {

}
