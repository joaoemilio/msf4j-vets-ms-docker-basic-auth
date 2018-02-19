package me.joaoemilio.petclinic.microservice.vets;

import org.springframework.stereotype.Component;
import org.wso2.carbon.messaging.Header;
import org.wso2.msf4j.Interceptor;
import org.wso2.msf4j.Request;
import org.wso2.msf4j.Response;
import org.wso2.msf4j.ServiceMethodInfo;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Component
public class HeaderLogInterceptor implements Interceptor {
    @Override
    public boolean preCall(Request request, Response response, ServiceMethodInfo serviceMethodInfo) throws Exception {
        System.out.println( "precall ");
        List<Header> list = request.getHeaders().getAll();
        for( Header h: list) {
            System.out.println("Header Name: " + h.getName() + " value : " + h.getValue());

        }
        return true;
    }

    @Override
    public void postCall(Request request, int i, ServiceMethodInfo serviceMethodInfo) throws Exception {
        System.out.println( "postCall ");
    }
}