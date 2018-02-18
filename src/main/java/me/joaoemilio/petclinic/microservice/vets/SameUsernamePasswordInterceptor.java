package me.joaoemilio.petclinic.microservice.vets;

import org.springframework.stereotype.Component;
import org.wso2.msf4j.security.basic.AbstractBasicAuthSecurityInterceptor;

@Component
public class SameUsernamePasswordInterceptor extends AbstractBasicAuthSecurityInterceptor {

    @Override
    protected boolean authenticate(String username, String password) {
        if (username.equals(password)) {
            return true;
        }
        return false;
    }
}
