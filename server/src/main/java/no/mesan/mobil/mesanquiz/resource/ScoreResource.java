package no.mesan.mobil.mesanquiz.resource;

import com.codahale.metrics.annotation.Timed;
import no.mesan.mobil.mesanquiz.domain.Score;
import no.mesan.mobil.mesanquiz.service.ScoreService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/scores")
@Produces(MediaType.APPLICATION_JSON + "; charset=utf-8")
public class ScoreResource {

    private ScoreService scoreService;

    public ScoreResource(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @GET
    @Timed
    public List<Score> getScores() {
        return new ArrayList<>();
    }

    @GET
    @Path("/{game}")
    @Timed
    public List<Score> getHighScores(@PathParam("game") long game) {
        return scoreService.getHighScores(game);
    }

    @GET
    @Path("/{game}/{player}")
    @Timed
    public List<Score> getHighScores(@PathParam("game") long game, @PathParam("player") String player) {
        return scoreService.getScore(game, player);
    }

    @POST
    @Timed
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveScore(Score score) {
        scoreService.saveScore(score);

        return Response.status(201).entity("{message: 'ok'}").build();
    }
}
