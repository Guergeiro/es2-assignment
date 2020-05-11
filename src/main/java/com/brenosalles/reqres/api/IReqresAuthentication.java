package com.brenosalles.reqres.api;

import com.brenosalles.reqres.http.Response;
import com.brenosalles.users.User;

public interface IReqresAuthentication {
    Response register(User user, String password);

    Response login(User user, String password);
}