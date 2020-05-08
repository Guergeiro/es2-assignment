package com.brenosalles;

import com.brenosalles.reqres.IReqresAuthentication;
import com.brenosalles.reqres.implementation.ReqresAuthentication;
import com.brenosalles.users.User;
import com.brenosalles.users.UserFactory;

import org.json.simple.parser.ParseException;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) throws ParseException {
        User user = UserFactory.createUser(null, "breno@breno.com", "breno", "salles", "www.brenosalles.com");

        IReqresAuthentication auth = new ReqresAuthentication();
        System.out.println(auth.register(user, "123").toString());
    }
}
