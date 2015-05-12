package no.mesan.mesanquiz.service.restservice;

import no.mesan.mesanquiz.model.ProgramDto;
import retrofit.http.GET;


public interface ProgramService {
    @GET("/program")
    ProgramDto getProgram();
}
