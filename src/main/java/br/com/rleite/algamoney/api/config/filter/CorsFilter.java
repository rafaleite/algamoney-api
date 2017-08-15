package br.com.rleite.algamoney.api.config.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.com.rleite.algamoney.api.config.property.AlgamoneyApiProperty;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter{
	
	@Autowired
	private AlgamoneyApiProperty apiProperty;
	
	private static final String OPTIONS = "OPTIONS";
	private static final String ORIGIN = "Origin";
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
	    HttpServletResponse response = (HttpServletResponse) res;
	    HttpServletRequest request = (HttpServletRequest) req;
	    
	    response.setHeader("Access-Control-Allow-Origin", apiProperty.getAllowedOrigin());
	    response.setHeader("Access-Control-Allow-Credentials", "true");
	    
	    if(OPTIONS.equals(request.getMethod()) && apiProperty.getAllowedOrigin().equals(request.getHeader(ORIGIN)) ) {
		    	response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
		    	response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, Authorization, remember-me");
		    	response.setHeader("Access-Control-Max-Age", "3600");
		    	
		    	response.setStatus(HttpServletResponse.SC_OK);
	    }else {
	    		chain.doFilter(req, res);
	    }
	    
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
	
	@Override
	public void destroy() {
	}

}
