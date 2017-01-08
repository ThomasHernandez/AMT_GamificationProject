package ch.heigvd.gamification.api;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Antony
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2016-11-29T09:54:16.483Z")

public class ApiOriginFilter implements javax.servlet.Filter {

    /**
     *
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletResponse res = (HttpServletResponse) response;
		res.addHeader("Access-Control-Allow-Origin", "*");
		res.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
		res.addHeader("Access-Control-Allow-Headers", "Content-Type");
		chain.doFilter(request, response);
	}

    /**
     *
     */
    @Override
	public void destroy() {
	}

    /**
     *
     * @param filterConfig
     * @throws ServletException
     */
    @Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}
}