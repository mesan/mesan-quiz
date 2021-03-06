package no.mesan.mobil.mesanquiz.resource;

import com.codahale.metrics.annotation.Timed;
import no.mesan.mobil.mesanquiz.domain.Score;
import no.mesan.mobil.mesanquiz.service.ScoreService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
        List<Score> scores = scoreService.getHighScores(game);
        Collections.sort(scores, new Comparator<Score>() {
            @Override
            public int compare(Score score1, Score score2) {
                return Integer.compare(score1.getCorrectAnswers(), score2.getCorrectAnswers());
            }
        });
        return scores;
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
