package uy.com.ces.capacitacion.suites;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.UseTechnicalNames;
import org.junit.runner.RunWith;

@IncludeTags("dev")
@UseTechnicalNames
@RunWith(JUnitPlatform.class)
@SelectPackages("uy.com.ces.capacitacion")
public class DevelopmentSuite {

}
