package com.lewap02.learning.controller;

import com.lewap02.learning.util.Util;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/hello")
public class Hello {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayPlainTextHello() {
        return "Hello Jersey TEXT_PLAIN";
    }

    @GET
    @Produces(MediaType.TEXT_XML)
    public String sayXMLHello() {
        return "<?xml version=\"1.0\"?>" + "<hello>Hello Jersey TEXT_XML</hello>";
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response sayHtmlHello() {
        return Response.ok().entity(Util.makeResponse("Hello Jersey TEXT_HTML")).build();
    }

} 