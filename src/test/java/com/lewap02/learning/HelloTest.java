package com.lewap02.learning;

import com.lewap02.learning.controller.Hello;
import com.lewap02.learning.controller.ServiceController;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

//import org.junit.runner.RunWith;
//import org.mockito.junit.MockitoJUnitRunner;

//@RunWith(MockitoJUnitRunner.class)
//@ExtendWith(MockitoExtension.class)
class HelloTest extends JerseyTest {

    //@BeforeEach
    //public void init() {
    //    MockitoAnnotations.openMocks(this);
    //}

    //@InjectMocks
    private ServiceController serviceController;

    @Override
    protected Application configure() {
        ResourceConfig config = new ResourceConfig(Hello.class);
        serviceController = mock(ServiceController.class);
        config.register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(serviceController).to(ServiceController.class);
            }
        }
        );
        return config;
    }

    @Disabled
    @Test
    public void helloResponseCodeOK() {
        Response response = target("hello").request()
                .get();

        assertEquals("Http Response should be 200: ", Response.Status.OK.getStatusCode(), response.getStatus());

    }

    @Disabled
    @Test
    public void helloResponseTextIsCorrect() {
        //init();
        configure();

        when(serviceController.returnSth(any(String.class))).thenReturn("RetFromMock");

        Response response = target("hello").request(MediaType.TEXT_PLAIN)
                .get();

        //System.out.println("AAAAAAAAAAAAAAAAAAAAAAAA" + response.readEntity(String.class));

        assertFalse("Hello Jerseyreturned RETBLA".equals(response.readEntity(String.class)));

    }

}