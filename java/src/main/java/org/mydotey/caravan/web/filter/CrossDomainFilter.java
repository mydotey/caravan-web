package org.mydotey.caravan.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mydotey.java.StringExtension;

/**
 * Created by Qiang Zhao on 10/05/2016.
 */
public class CrossDomainFilter implements Filter {

    /** The HTTP {@code Access-Control-Allow-Headers} header field name. */
    private static final String ACCESS_CONTROL_ALLOW_HEADERS = "Access-Control-Allow-Headers";
    /** The HTTP {@code Access-Control-Allow-Methods} header field name. */
    private static final String ACCESS_CONTROL_ALLOW_METHODS = "Access-Control-Allow-Methods";
    /** The HTTP {@code Access-Control-Allow-Origin} header field name. */
    private static final String ACCESS_CONTROL_ALLOW_ORIGIN = "Access-Control-Allow-Origin";
    /** The HTTP {@code Access-Control-Allow-Credentials} header field name. */
    private static final String ACCESS_CONTROL_ALLOW_CREDENTIALS = "Access-Control-Allow-Credentials";
    /** The HTTP {@code Access-Control-Expose-Headers} header field name. */
    private static final String ACCESS_CONTROL_EXPOSE_HEADERS = "Access-Control-Expose-Headers";
    /** The HTTP {@code Access-Control-Max-Age} header field name. */
    private static final String ACCESS_CONTROL_MAX_AGE = "Access-Control-Max-Age";
    /** The HTTP {@code Access-Control-Request-Headers} header field name. */
    private static final String ACCESS_CONTROL_REQUEST_HEADERS = "Access-Control-Request-Headers";
    /** The HTTP {@code Access-Control-Request-Method} header field name. */
    private static final String ACCESS_CONTROL_REQUEST_METHOD = "Access-Control-Request-Method";

    private String allowOrigin = "*";
    private String allowMethods = "*";
    private String allowHeaders = "*";
    private String exposeHeaders = "*";
    private String allowCredentials = "true";
    private String maxAge = String.valueOf(60 * 60);

    public String getAllowOrigin() {
        return allowOrigin;
    }

    public void setAllowOrigin(String allowOrigin) {
        if (StringExtension.isBlank(allowOrigin))
            return;

        this.allowOrigin = allowOrigin;
    }

    public String getAllowMethods() {
        return allowMethods;
    }

    public void setAllowMethods(String allowMethods) {
        if (StringExtension.isBlank(allowMethods))
            return;

        this.allowMethods = allowMethods;
    }

    public String getAllowHeaders() {
        return allowHeaders;
    }

    public void setAllowHeaders(String allowHeaders) {
        if (StringExtension.isBlank(allowHeaders))
            return;

        this.allowHeaders = allowHeaders;
    }

    public String getExposeHeaders() {
        return exposeHeaders;
    }

    public void setExposeHeaders(String exposeHeaders) {
        if (StringExtension.isBlank(exposeHeaders))
            return;

        this.exposeHeaders = exposeHeaders;
    }

    public boolean isAllowCredentials() {
        return "true".equals(allowCredentials);
    }

    public void setAllowCredentials(boolean allowCredentials) {
        this.allowCredentials = allowCredentials ? "true" : "false";
    }

    public long getMaxAge() {
        return Long.parseLong(maxAge);
    }

    public void setMaxAge(long maxAge) {
        this.maxAge = String.valueOf(maxAge);
    }

    public CrossDomainFilter() {

    }

    public CrossDomainFilter(String allowOrigin, String allowMethods, String allowHeaders, String exposeHeaders,
        boolean allowCredentials, long maxAge) {
        setAllowOrigin(allowOrigin);
        setAllowMethods(allowMethods);
        setAllowHeaders(allowHeaders);
        setExposeHeaders(exposeHeaders);
        setAllowCredentials(allowCredentials);
        setMaxAge(maxAge);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    @SuppressWarnings("all")
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        if (!(request instanceof HttpServletRequest) || !(response instanceof HttpServletResponse)) {
            chain.doFilter(request, response);
            return;
        }

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        res.setHeader(ACCESS_CONTROL_ALLOW_ORIGIN, allowOrigin);
        res.setHeader(ACCESS_CONTROL_EXPOSE_HEADERS, exposeHeaders);

        if (isAllowCredentials())
            res.setHeader(ACCESS_CONTROL_ALLOW_CREDENTIALS, allowCredentials);

        if (!"OPTIONS".equals(req.getMethod())) {
            chain.doFilter(request, response);
            return;
        }

        String accessControllRequestMethod = req.getHeader(ACCESS_CONTROL_REQUEST_METHOD);
        if (accessControllRequestMethod != null)
            res.setHeader(ACCESS_CONTROL_ALLOW_METHODS, accessControllRequestMethod);

        String accessControllRequestHeaders = req.getHeader(ACCESS_CONTROL_REQUEST_HEADERS);
        if (accessControllRequestHeaders != null)
            res.setHeader(ACCESS_CONTROL_ALLOW_HEADERS, accessControllRequestHeaders);

        res.setHeader(ACCESS_CONTROL_MAX_AGE, maxAge);

        res.setStatus(200);
    }

    @Override
    public void destroy() {

    }

}
