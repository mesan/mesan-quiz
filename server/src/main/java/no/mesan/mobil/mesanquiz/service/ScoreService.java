package no.mesan.mobil.mesanquiz.service;

import no.mesan.mobil.mesanquiz.dao.ScoreDao;
import no.mesan.mobil.mesanquiz.domain.Score;

import java.util.List;

public class ScoreService {

    private ScoreDao scoreDao;

    public ScoreService(ScoreDao scoreDao) {
        this.scoreDao = scoreDao;
    }

    public List<Score> getHighScores(long game) {
        return scoreDao.getHighScores(game);
    }

    public List<Score> getScore(long game, String player) {
        return scoreDao.getScore(game, player);
    }

    public void saveScore(Score score) {
        scoreDao.insert(score.getGame().getId(), score.getPlayer().getShortName(), score.getCorrectAnswers(),
                score.getQuestionCount(), score.getTimeUsed());
    }


}
