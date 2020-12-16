package org.mydotey.caravan.web.configuration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.mydotey.java.StringExtension;

/**
 * Created by Qiang Zhao on 10/05/2016.
 */
public class ServletContextConfigurationSourceTest {

    ServletContextConfigurationSourceConfig config = new ServletContextConfigurationSourceConfig.Builder()
        .setServletContext(new MyServletContext()).build();

    @Test
    public void testConfiguration() {
        ServletContextConfigurationSource source = new ServletContextConfigurationSource(config);
        assertTrue(!StringExtension.isBlank(source.getPropertyValue("env")));
    }

    @Test
    public void testGetPropertyValue() {
        ServletContextConfigurationSource source = new ServletContextConfigurationSource(config);
        assertEquals(source.getPropertyValue("service-port"), "8090");
        assertEquals(source.getPropertyValue("env"), "fws");
    }

}
