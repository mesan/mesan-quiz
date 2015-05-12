package no.mesan.mesanquiz.service.restservice;

import no.mesan.mesanquiz.model.GameDto;
import retrofit.http.GET;

public interface GameRestService {
    @GET("/game")
    GameDto getGame();
}
