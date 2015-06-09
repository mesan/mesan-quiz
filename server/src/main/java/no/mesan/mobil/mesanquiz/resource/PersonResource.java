package no.mesan.mobil.mesanquiz.resource;

import com.codahale.metrics.annotation.Timed;
import no.mesan.mobil.mesanquiz.domain.Person;
import no.mesan.mobil.mesanquiz.service.PersonService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/persons")
@Produces(MediaType.APPLICATION_JSON + "; charset=utf-8")
public class PersonResource {

    private PersonService personService;

    public PersonResource(PersonService personService) {
        this.personService = personService;
    }
    @GET
    @Timed
    public List<Person> getPersons() {
        return personService.getPeople();
    }

    @GET
    @Path("/{short_name}")
    @Timed
    public Person getPerson(@PathParam("short_name") String short_name) {
        return personService.getPerson(short_name);
    }
}
