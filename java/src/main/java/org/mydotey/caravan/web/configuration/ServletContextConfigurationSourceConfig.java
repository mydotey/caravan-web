package org.mydotey.caravan.web.configuration;

import javax.servlet.ServletContext;

import org.mydotey.java.ObjectExtension;
import org.mydotey.java.StringExtension;
import org.mydotey.scf.DefaultConfigurationSourceConfig;

/**
 * @author koqizhao
 *
 * May 17, 2018
 */
public class ServletContextConfigurationSourceConfig extends DefaultConfigurationSourceConfig {

    private ServletContext _servletContext;

    protected ServletContextConfigurationSourceConfig() {

    }

    public ServletContext getServletContext() {
        return _servletContext;
    }

    @Override
    public String toString() {
        return String.format("%s { name: %s, servletContext: %s }", getClass().getSimpleName(), getName(),
            getServletContext());
    }

    public static class Builder extends
        DefaultConfigurationSourceConfig.DefaultAbstractBuilder<Builder, ServletContextConfigurationSourceConfig> {

        @Override
        protected ServletContextConfigurationSourceConfig newConfig() {
            return new ServletContextConfigurationSourceConfig();
        }

        public Builder setServletContext(ServletContext servletContext) {
            getConfig()._servletContext = servletContext;

            return this;
        }

        @Override
        public ServletContextConfigurationSourceConfig build() {
            ObjectExtension.requireNonNull(getConfig().getServletContext(), "servletContext");

            if (StringExtension.isBlank(getConfig().getName()))
                setName("servlet-context");

            return super.build();
        }

    }

}
