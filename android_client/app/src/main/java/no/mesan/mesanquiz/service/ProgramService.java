package no.mesan.mesanquiz.service;

import no.mesan.mesanquiz.model.ProgramDto;
import retrofit.http.GET;

/**
 * Created by n06849 on 22.04.2015.
 */
public interface ProgramService {
    @GET("/program")
    ProgramDto getProgram();
}
