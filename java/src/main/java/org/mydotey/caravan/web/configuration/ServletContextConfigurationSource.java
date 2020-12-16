package org.mydotey.caravan.web.configuration;

import org.mydotey.java.StringExtension;
import org.mydotey.scf.source.stringproperty.StringPropertyConfigurationSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Qiang Zhao on 10/05/2016.
 */
public class ServletContextConfigurationSource
    extends StringPropertyConfigurationSource<ServletContextConfigurationSourceConfig> {

    private static final Logger _logger = LoggerFactory.getLogger(ServletContextConfigurationSource.class);

    public ServletContextConfigurationSource(ServletContextConfigurationSourceConfig config) {
        super(config);
    }

    @Override
    public String getPropertyValue(String key) {
        if (StringExtension.isBlank(key)) {
            _logger.warn("ServletContext key is null or empty!");
            return null;
        }
        String value = getConfig().getServletContext().getInitParameter(key);
        return value;
    }

}
