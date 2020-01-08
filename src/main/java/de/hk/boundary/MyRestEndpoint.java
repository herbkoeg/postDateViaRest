/*
 * JBoss, Home of Professional Open Source
 * Copyright 2015, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.hk.boundary;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.time.LocalDate;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;


/**
 * @author herbert.koeglsperger
 *
 */

@Path("/postdemo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MyRestEndpoint {

    // curl -X GET "http://localhost:8080/postDateViaRest/rest/postdemo/server/sayhello" -H "accept: application/json"  -v
    // --> says 'hello' on the error-console
    @GET
    @Path("/server/sayhello")
    public void sayHello() {
        System.err.println("hallo");
    }


    // curl -X GET "http://localhost:8080/postDateViaRest/rest/postdemo/server/getpojo" -H "accept: application/json"  -v
    @GET
    @Path("/server/getpojo")
    public PojoWithLocalDate getExamplePojo() {
        return new PojoWithLocalDate("jawoi", LocalDate.now());
    }

    @POST
    @Path("/server/postdate")
    public void postDate(PojoWithLocalDate pojoWithLocalDate) {
        System.out.println("-----> /server/postdate successful: " + pojoWithLocalDate);
    }

    // curl -X GET "http://localhost:8080/postDateViaRest/rest/postdemo/client/postdate" -H "accept: application/json"  -v
    @GET
    @Path("/client/postdate")
    public void callPost() {
        PojoWithLocalDate pojoWithLocalDate = new PojoWithLocalDate();
        pojoWithLocalDate.setAction("someAction");
        pojoWithLocalDate.setSomeLocalDate(LocalDate.now());

        System.out.println("-----> /client/postdate successful started: " + pojoWithLocalDate);
        System.out.println("-----> try to post ");

        Response response = ClientBuilder.newClient()
                // <-- @Provider in  ContextResolver - e.g. JacksonProducer.java - does not work here !! Dont know why :(
                // you have to register your Producer ... - remove it if you do not believe it ;)
                .register(JacksonProducer.class)
                .target( "http://localhost:8080/postDateViaRest/rest/postdemo/server/postdate")
                .request(APPLICATION_JSON)
                .post(Entity.json(pojoWithLocalDate));

        System.out.println("-----> Status of post: " + response.getStatus());
    }


}
