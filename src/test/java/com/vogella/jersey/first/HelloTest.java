package com.vogella.jersey.first;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
//import junit.framework.Test;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import static junit.framework.Assert.assertEquals;

class HelloTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(Hello.class);
    }

    @Test
    public void helloResponseCode() {
        Response response = target("hello").request()
                .get();

        assertEquals("Http Response should be 200: ", Response.Status.OK.getStatusCode(), response.getStatus());
        //assertEquals("Http Content-Type should be: ", MediaType.TEXT_HTML, response.getHeaderString(HttpHeaders.CONTENT_TYPE));

        //String content = response.readEntity(String.class);
        //assertEquals("Content of ressponse is: ", "hi", content);
    }

}