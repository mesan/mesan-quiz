package no.mesan.mobil.mesanquiz.service;

import no.mesan.mobil.mesanquiz.dao.GameDao;
import no.mesan.mobil.mesanquiz.domain.Alternative;
import no.mesan.mobil.mesanquiz.domain.Game;
import no.mesan.mobil.mesanquiz.domain.QuestionAbc;

import java.util.List;

public class GameService {

    private GameDao gameDao;

    public GameService(GameDao gameDao) {
        this.gameDao = gameDao;
    }

    public Game getGame(long id) {
        Game game = gameDao.getGame(id);
        List<QuestionAbc> questionsForGame = gameDao.getQuestionsForGame(id);

        for (QuestionAbc question : questionsForGame) {
            List<Alternative> alternativesForQuestion = gameDao.getAlternativesForQuestion(question.getId());
            question.setAlternatives(alternativesForQuestion);
        }

        game.setQuestions(questionsForGame);
        return game;
    }

    public void saveGame(Game game) {
        //gameDao.insert(game.getName());
    }
}
