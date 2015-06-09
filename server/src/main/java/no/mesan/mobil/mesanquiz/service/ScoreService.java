package no.mesan.mobil.mesanquiz.service;

import no.mesan.mobil.mesanquiz.dao.ScoreDao;
import no.mesan.mobil.mesanquiz.domain.Score;

import java.util.List;

public class ScoreService {

    private ScoreDao scoreDao;

    public ScoreService(ScoreDao scoreDao) {
        this.scoreDao = scoreDao;
    }

    public List<Score> getHighScores(long game_id) {
        return scoreDao.getHighScores(game_id);
    }

    public void saveScore(Score score) {
        scoreDao.insert(score.getGame().getId(), score.getPlayer().getShortName(), score.getCorrectAnswers(),
                score.getQuestionCount(), score.getTimeUsed());
    }


}
