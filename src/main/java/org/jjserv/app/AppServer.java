package org.jjserv.app;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import org.jjserv.security.TokenAuthenticationService;
import org.jjserv.security.UserService;

public class AppServer {

	private static TokenAuthenticationService tokenAuthenticationService;
	public static TokenAuthenticationService getTokenAuthenticationService() {
		return tokenAuthenticationService;
	}

    public static void main(String[] args) throws Exception {
        final UserService userService = new UserService();
        tokenAuthenticationService = new TokenAuthenticationService("tooManySecrets", userService);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");

        ServletHolder jerseyServlet = context.addServlet(MyServletContainer.class, "/api/*");
        jerseyServlet.setInitOrder(0);
        jerseyServlet.setInitParameter("jersey.config.server.provider.packages","org.jjserv.rest.controllers");

        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setDirectoriesListed(false);
        resource_handler.setWelcomeFiles(new String[]{ "index.html" });
        resource_handler.setResourceBase("src/main/resources/webapps/HTML");
        ContextHandler staticCtx = new ContextHandler("/static"); /* the server uri path */
        staticCtx.setHandler(resource_handler);

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[] { staticCtx, context, new DefaultHandler() });

        Server jettyServer = new Server(6060);
        jettyServer.setHandler(handlers);

        try {
            jettyServer.start();
            jettyServer.join();
        } finally {
            jettyServer.destroy();
        }
    }
}
