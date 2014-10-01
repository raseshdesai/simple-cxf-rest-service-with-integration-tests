package demo.cxf.restful;

import demo.cxf.restful.dao.BookShelfDAO;
import demo.cxf.restful.service.BookService;
import demo.cxf.restful.service.CategoryService;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *  Created by Rasesh Desai on 3/18/14.
 */
@ContextConfiguration(locations = {"classpath:demo/cxf/restful/beans.xml"})
public class ServerForRestServices {

    private Server server;

    public  void startServer(String address) {
        JAXRSServerFactoryBean restServer = new JAXRSServerFactoryBean();

        restServer.setResourceClasses(CategoryService.class);
        restServer.setResourceProvider(CategoryService.class, new SingletonResourceProvider(new CategoryService(new BookShelfDAO())));

        restServer.setResourceClasses(BookService.class);
        restServer.setResourceProvider(BookService.class, new SingletonResourceProvider(new BookService(new BookShelfDAO())));

        restServer.setAddress(address);
        server = restServer.create();
    }

    public void keepServerUpAndRunning() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            br.readLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Server Stopped");
        System.exit(0);
    }

    public void stopServer(){
        server.stop();
    }

    public static void main(String[] args) {
        ServerForRestServices serverForRestServices = new ServerForRestServices();
        serverForRestServices.startServer("http://localhost:9000/");
        serverForRestServices.keepServerUpAndRunning();
    }
}
