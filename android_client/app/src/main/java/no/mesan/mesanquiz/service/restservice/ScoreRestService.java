package no.mesan.mesanquiz.service.restservice;

import java.util.List;

import no.mesan.mesanquiz.model.ScoreDto;
import retrofit.http.GET;
import retrofit.http.Path;

public interface ScoreRestService {
    @GET("/scores/{id}")
    List<ScoreDto> getScores(@Path("id") int id);
}
