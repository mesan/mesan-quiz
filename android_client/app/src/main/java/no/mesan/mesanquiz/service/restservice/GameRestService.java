package no.mesan.mesanquiz.service.restservice;

import java.util.List;

import no.mesan.mesanquiz.model.GameDto;
import retrofit.http.GET;
import retrofit.http.Path;

public interface GameRestService {
    @GET("/games/{id}")
    GameDto getGame(@Path("id") int id);

    @GET("/games")
    List<GameDto> getGames();
}
