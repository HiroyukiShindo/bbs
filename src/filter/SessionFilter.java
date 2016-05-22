package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bean.UserBean;

public class SessionFilter implements Filter {
	private static Logger log = LoggerFactory.getLogger(SessionFilter.class);
	private String ignoreServlet;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.debug("method call.");
		this.ignoreServlet = filterConfig.getInitParameter("ignoreServlet");
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		log.debug("method call.");
		
		try {
			if (req instanceof HttpServletRequest) {
				HttpServletRequest request = (HttpServletRequest) req;
				HttpSession session = request.getSession(true);
				UserBean user = (UserBean) session.getAttribute("userData");
				
				if (!ignoreServlet.equals(((HttpServletRequest) request).getServletPath())) {
					if (user == null) {
						log.info("セッションチェック実行：セッションタイムアウト");
						
						HttpServletResponse response = (HttpServletResponse) res;
						response.sendRedirect("LoginServlet");
						return;
						
					} else {
						log.info("セッションチェック実行：セッション継続");
						chain.doFilter(req, res);
					}
					
				} else {
					log.info("セッションチェック未実行");
					chain.doFilter(req, res);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void destroy() {
		log.debug("method call.");
	}
}
