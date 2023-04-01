package org.fr.rsi;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;
import java.util.ArrayList;
import java.util.List;

//Service Endpoint Interface
@WebService(name = "HelloWorldService", targetNamespace = "http://org.fr.rsi")
@SOAPBinding(style = Style.DOCUMENT, use = Use.LITERAL) //optional
public interface HelloWorld {
    @WebMethod
    String getHelloWorldAsString();

    @WebMethod(action = "http://org.fr.rsi/produkty",
            operationName = "produkty", exclude = false)
    String getProducts();

    class Product {
        String nazwa;
        String opis;
        int cena;
        public Product(String nazwa, String opis, int cena) {
            this.cena = cena;
            this.nazwa = nazwa;
            this.opis = opis;
        }
    }
}
