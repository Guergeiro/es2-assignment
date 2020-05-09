package com.brenosalles.reqres;

import com.brenosalles.reqres.http.Response;
import com.brenosalles.resources.Resource;

public interface IReqresResource {
    Response readResources();

    Response readResource(Integer id);

    Response createResource(Resource resource);

    Response updateResource(Integer id, Resource resource);

    Response deleteResource(Integer id);
}