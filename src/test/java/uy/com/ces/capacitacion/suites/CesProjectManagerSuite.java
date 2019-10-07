package uy.com.ces.capacitacion.suites;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.UseTechnicalNames;
import org.junit.runner.RunWith;

@UseTechnicalNames
@RunWith(JUnitPlatform.class)
@SelectClasses({
	uy.com.ces.capacitacion.test.egroupware.EGroupwareProjectTest.class
})
public class CesProjectManagerSuite {

}