package no.mesan.mesanquiz.service.restservice;

import no.mesan.mesanquiz.model.PersonDto;
import retrofit.http.GET;
import retrofit.http.Path;

public interface PersonRestService {
    @GET("/person/{id}")
    PersonDto getPerson(@Path("id") int id);
}
