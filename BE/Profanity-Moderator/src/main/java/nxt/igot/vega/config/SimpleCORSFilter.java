package nxt.igot.vega.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
@Order(0)
public class SimpleCORSFilter implements Filter {

	private final Logger log = LoggerFactory.getLogger(SimpleCORSFilter.class);

	public SimpleCORSFilter() {
		log.info("SimpleCORSFilter init");
	}

//@RequestMapping(value= "/feedback/**", method=RequestMethod.OPTIONS)
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		
		HttpServletRequest request = (HttpServletRequest) req;
		
		log.debug(request.getMethod() + "CORSIssue");
		
		HttpServletResponse response = (HttpServletResponse) res;
		response.setHeader("x-content-type-options", "nosniff");
		response.setHeader("X-DNS-Prefetch-Control", "on");
		response.setHeader("X-Download-Options", "noopen");
		response.setHeader("x-frame-options", "DENY");
//response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, HEAD, PUT");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers",
				"rootOrg, wid, hostPath, locale, org, Access-Control-Allow-Headers, Content-Type, Accept, X-Requested-With, Origin, remember-me, Access-Control-Request-Method, Authorization, X-XSRF-TOKEN, token, auth, observe");
		response.setHeader("Access-Control-Expose-Headers", "Authorization");
		response.addHeader("Access-Control-Expose-Headers", "responseType");
		response.addHeader("Access-Control-Expose-Headers", "observe");
		if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			chain.doFilter(req, res);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) {
		System.out.println("+++++++++++++++++++ CORS");
	}

	@Override
	public void destroy() {
	}

}
