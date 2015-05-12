package no.mesan.mobil.mesanquiz.resource;

import com.codahale.metrics.annotation.Timed;
import no.mesan.mobil.mesanquiz.domain.Game;
import no.mesan.mobil.mesanquiz.service.GameService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/games")
@Produces(MediaType.APPLICATION_JSON + "; charset=utf-8")
public class GameResource {

    private GameService gameService;

    public GameResource(GameService gameService) {
        this.gameService = gameService;
    }
    @GET
    @Timed
    public List<Game> getGames() {
        return gameService.getGames();
    }

    @GET
    @Path("/{id}")
    @Timed
    public Game getGame(@PathParam("id") long id) {
        return gameService.getGame(id);
    }

    @GET
    @Path("/topic/{topic}")
    @Timed
    public List<Game> getGamesForTopic(@PathParam("topic") String topic) {
        return gameService.getGamesForTopic(topic);
    }

    @POST
    @Timed
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveGame(Game game) {
        gameService.saveGame(game);

        return Response.status(201).entity("{message: 'ok'}").build();
    }
}
