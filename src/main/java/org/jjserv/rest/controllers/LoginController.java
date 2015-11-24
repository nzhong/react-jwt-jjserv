package org.jjserv.rest.controllers;

import org.jjserv.app.AppServer;
import org.jjserv.security.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Objects;

@Path("/login")
public class LoginController {

	public static class LoginAuth {
		private String username;
		private String password;
		private String token;
		public LoginAuth() {}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getToken() {
			return token;
		}
		public void setToken(String token) {
			this.token = token;
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public LoginAuth login(final LoginAuth auth) throws Exception {
		final String username = auth.getUsername();
		final String password = auth.getPassword();
		final String token = AppServer.getTokenAuthenticationService().authenticateByUsernameAndPassword(username, password);
		auth.setPassword("");
		auth.setToken(token);
		return auth;
	}
}