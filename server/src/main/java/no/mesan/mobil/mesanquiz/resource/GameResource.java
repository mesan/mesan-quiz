package no.mesan.mobil.mesanquiz.resource;

import com.codahale.metrics.annotation.Timed;
import no.mesan.mobil.mesanquiz.domain.Game;
import no.mesan.mobil.mesanquiz.domain.Score;
import no.mesan.mobil.mesanquiz.service.GameService;
import no.mesan.mobil.mesanquiz.service.ScoreService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/games")
@Produces(MediaType.APPLICATION_JSON + "; charset=utf-8")
public class GameResource {

    private GameService gameService;
    private ScoreService scoreService;

    public GameResource(GameService gameService, ScoreService scoreService) {
        this.gameService = gameService;
        this.scoreService = scoreService;
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
    @Path("/{id}/scores")
    @Timed
    public List<Score> getHighScores(@PathParam("id") long id) {
        return scoreService.getHighScores(id);
    }

    @GET
    @Path("/{id}/scores/{player}")
    @Timed
    public List<Score> getHighScores(@PathParam("id") long id, @PathParam("player") String player) {
        return scoreService.getScore(id, player);
    }

    @GET
    @Path("/topic/{topic}")
    @Timed
    public List<Game> getGamesForTopic(@PathParam("topic") String topic) {
        return gameService.getGamesForTopic(topic);
    }

    @PUT
    @Timed
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveGame(Game game) {
        gameService.saveGame(game);
        return Response.status(Response.Status.CREATED).entity("{message: 'ok'}").build();
    }
}
