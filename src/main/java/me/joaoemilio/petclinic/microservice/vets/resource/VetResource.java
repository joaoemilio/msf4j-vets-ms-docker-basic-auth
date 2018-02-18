/*
 * Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *   WSO2 Inc. licenses this file to you under the Apache License,
 *   Version 2.0 (the "License"); you may not use this file except
 *   in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package me.joaoemilio.petclinic.microservice.vets.resource;

import me.joaoemilio.petclinic.microservice.vets.dao.VetRepository;
import me.joaoemilio.petclinic.microservice.vets.model.Vet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Annotated Resource class.
 */

@Component
@Path("/vets")
public class VetResource {

    @GET
    @Path("/health")
    public Response healthcheck() {
        return Response.status(Response.Status.ACCEPTED).entity("OK").build();
    }

    @Autowired
    private VetRepository repository;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVetById(@PathParam("id") long id) {
        System.out.println("ID:" + id );
        Vet vet = repository.find(id);
        System.out.println("depois do find" );

        return Response.status(Response.Status.ACCEPTED).entity(vet).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllVets() {
        List<Vet> allVets = repository.findAll();
        return Response.status(Response.Status.ACCEPTED).entity(allVets).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addVet(Vet model) {
        System.out.println("model: " + model );
        repository.add(model);
        return Response.status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("/{id}")
    public Response removeVet(@PathParam("id") long id) {
        repository.remove(id);
        return Response.status(Response.Status.ACCEPTED).build();
    }

    @PUT
    public Response updateVet(Vet model) {
        repository.update(model);
        return Response.status(Response.Status.ACCEPTED).build();
    }


}
