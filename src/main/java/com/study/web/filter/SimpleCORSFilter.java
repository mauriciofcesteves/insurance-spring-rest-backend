package com.study.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

/**
 * Filter that enable CORS (Cross Origin Resource Sharing) in the response.
 * 
 * Cross-origin resource sharing (CORS) is a W3C specification implemented by most browsers that allows you to 
 * specify in a flexible way what kind of cross domain requests are authorized, instead of using some less secured 
 * and less powerful hacks like IFrame or JSONP.
 * 
 * For security reasons, browsers prohibit AJAX calls to resources residing outside the current origin. 
 * For example, as you’re checking your bank account in one tab, you could have the evil.com website in another tab. 
 * The scripts from evil.com shouldn’t be able to make AJAX requests to your bank API (withdrawing money from your account!) 
 * using your credentials.
 * 
 * See: https://spring.io/blog/2015/06/08/cors-support-in-spring-framework
 * 
 * PS: I tried to use @CrossOrigin annotation in REST controllers, but it doesn't work. 
 * I tried to use just like that filter implementation (see bellow):
 * 
 * @CrossOrigin(origins = "*", maxAge = 3600, exposedHeaders = { "Location" },
 *	allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept"}, 
 *	methods = { RequestMethod.PUT, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.GET, RequestMethod.OPTIONS, RequestMethod.PATCH})
 * 
 * @author Mauricio Esteves
 */
@Component
public class SimpleCORSFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE, PATCH");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        response.setHeader("Access-Control-Expose-Headers", "Location");
        chain.doFilter(req, res);
    }

    public void init(FilterConfig filterConfig) {}

    public void destroy() {}

}
