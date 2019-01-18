package ru.ivanov.todoproject.bootstrap;

import ru.ivanov.todoproject.client.ClientServiceRemote;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;

public class Bootstrap {

    public static void main(String[] args) throws Exception {
        URL url = new URL("http://localhost:8080/hello");
        QName qname = new QName("http://uc.javajeff.ca/",
                "UCImplService");
        Service service = Service.create(url, qname);
        qname = new QName("http://uc.javajeff.ca/", "UCImplPort");
        ClientServiceRemote remote = service.getPort(qname, ClientServiceRemote.class);
    }
}