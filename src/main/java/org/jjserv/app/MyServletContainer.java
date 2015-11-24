package org.jjserv.app;

import org.glassfish.jersey.servlet.ServletContainer;
import org.jjserv.app.AppServer;
import org.jjserv.security.User;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


public class MyServletContainer extends ServletContainer {
	@Override
	public void service(final ServletRequest req, final ServletResponse res)
			throws ServletException, IOException {
		final HttpServletRequest request = (HttpServletRequest) req;
//		List<String> headers = Collections.list(request.getHeaderNames());
//		for (String hdr: headers) {
//			System.out.println( "["+hdr+": "+request.getHeader(hdr)+"] ");
//		}

		User loginUser = null;
		try {
			loginUser = AppServer.getTokenAuthenticationService().getAuthentication(request);
		}
		catch(Exception e) {
			loginUser = null;
		}

		if ( loginUser == null ) { // user is not authenticated, we may need to throw exception
			if ( request.getRequestURI().indexOf("/login")<0 && !request.getRequestURI().equals("/") ) {
				throw new ServletException("Invalid authentication when accessing "+request.getRequestURI());
			}
		}

		AuthHttpServletRequest authReq= new AuthHttpServletRequest( loginUser, request );
		super.service((ServletRequest)authReq, res);
	}
}
