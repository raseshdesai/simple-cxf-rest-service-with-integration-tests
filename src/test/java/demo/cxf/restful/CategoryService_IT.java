package demo.cxf.restful;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import demo.cxf.restful.model.Category;
import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 *  Created by Rasesh Desai on 3/18/14.
 */

public class CategoryService_IT {

    private final String testSeverAddressAndPort = "http://localhost:7898/";
    ServerForRestServices serverForRestServices;

    @Before
    public void setUp() throws Exception {
        serverForRestServices = new ServerForRestServices();
        serverForRestServices.startServer(testSeverAddressAndPort);
    }

    @Test
    public  void categoryService() {
        List<Object> providers = new ArrayList<Object>();
        providers.add(new JacksonJaxbJsonProvider());

        WebClient client = WebClient.create(testSeverAddressAndPort + "bookshelf", providers);
        client = client.accept("application/xml").type("application/xml").path("/categories/001");

        Category category = client.get(Category.class);
        assertEquals("Java", category.getCategoryName());
    }

    @After
    public void tearDown() throws Exception {
        serverForRestServices.stopServer();
    }
}
