package org.acme.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.acme.model.LogReader;
import org.acme.model.Pollution;
import org.acme.model.Sample;

@Path("/logs")
@Consumes("application/json")
@Produces("application/json")
public class LogResource {

    @GET
    public List<Pollution> get() {
        return new LogReader().read();
    }

    @GET
    @Path("/{index}")
    public Sample getSample(@PathParam("index") int index) {
        return new LogReader().readSample(index);
    }

}