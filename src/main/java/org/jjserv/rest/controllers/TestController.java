package org.jjserv.rest.controllers;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

@Path("/test")
public class TestController {

    public static class TestObject {
        private String name;
        private int value;

        public TestObject() {

        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public TestObject test(@Context SecurityContext securityContext) {
        System.out.println( "!!!"+securityContext.getUserPrincipal().getName() );
        return new TestObject();
    }
}
