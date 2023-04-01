package org.fr.rsi;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebService(endpointInterface = "org.fr.rsi.HelloWorld", name = "HelloWorldService", targetNamespace = "http://org.fr.rsi")
public class HelloWorldImpl implements HelloWorld {

    @Resource
    WebServiceContext wsctx;
    @Override
    public String getHelloWorldAsString() {
        MessageContext mctx = wsctx.getMessageContext();

        Map http_headers = (Map) mctx.get(MessageContext.HTTP_REQUEST_HEADERS);
        List userList = (List) http_headers.get("Username");
        List passList = (List) http_headers.get("Password");

        String username = "";
        String password = "";

        if(userList!=null){
            //get username
            username = userList.get(0).toString();
        }

        if(passList!=null){
            //get password
            password = passList.get(0).toString();
        }

        //Should validate username and password with database
        if (username.equals("mkyong") && password.equals("password")){
            return "Hello World JAX-WS - Valid User!";
        }else{
            return "Unknown User!";
        }
    }

    @Override
    public String getProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("komputer", "urzadzenie do pracy", 2500));
        products.add(new Product("rower", "pojazd na dwoch kolach", 1300));
        products.add(new Product("mikrofalowka", "urzadzenie do podgrzewania jedzenia", 10000));

        String productsString = "";
        for (int i = 0; i < products.size(); i++) {
            productsString = productsString + "Produkt: " + products.get(i).nazwa + " Opis: " + products.get(i).opis
                    +" Cena: " + products.get(i).cena + " ";
        }

        return productsString;
    }
}
