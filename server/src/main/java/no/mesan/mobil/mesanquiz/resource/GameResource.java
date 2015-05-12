package no.mesan.mobil.mesanquiz.resource;

import com.codahale.metrics.annotation.Timed;
import no.mesan.mobil.mesanquiz.domain.Game;
import no.mesan.mobil.mesanquiz.service.GameService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/games")
@Produces(MediaType.APPLICATION_JSON + "; charset=utf-8")
public class GameResource {

    private GameService gameService;

    public GameResource(GameService gameService) {
        this.gameService = gameService;
    }

    @GET
    @Path("/{id}")
    @Timed
    public Game getGame(@PathParam("id") long id) {
        return gameService.getGame(id);
    }

}
