package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EncodingFilter implements Filter {
	private static Logger log = LoggerFactory.getLogger(EncodingFilter.class);
	private String encoding = null;
	
	public void init(FilterConfig config) throws ServletException {
		log.debug("method call.");
		encoding = config.getInitParameter("encoding");
	}
		
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
		log.debug("method call.");
		request.setCharacterEncoding(encoding);
		chain.doFilter(request, response);
	}
	
	public void destroy() {
		log.debug("method call.");
		encoding = null;
	}
}
