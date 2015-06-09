package no.mesan.mobil.mesanquiz.service;

import no.mesan.mobil.mesanquiz.dao.GameDao;
import no.mesan.mobil.mesanquiz.dao.QuestionDao;
import no.mesan.mobil.mesanquiz.domain.Alternative;
import no.mesan.mobil.mesanquiz.domain.Game;
import no.mesan.mobil.mesanquiz.domain.Question;

import java.util.List;

public class GameService {

    private final GameDao gameDao;
    private final QuestionService questionService;

    public GameService(GameDao gameDao, QuestionService questionService) {
        this.gameDao = gameDao;
        this.questionService = questionService;
    }

    public List<Game> getGames() {
        List<Game> games = gameDao.getGames();

        for(Game game : games) {
            addQuestionsAndAlternativesForGame(game);
        }

        return games;
    }

    public Game getGame(long id) {
        Game game = gameDao.getGame(id);
        addQuestionsAndAlternativesForGame(game);
        return game;
    }

    private void addQuestionsAndAlternativesForGame(Game game) {
        List<Question> questionsForGame = gameDao.getQuestionsForGame(game.getId());

        for (Question question : questionsForGame) {
            List<Alternative> alternativesForQuestion = gameDao.getAlternativesForQuestion(question.getId());
            question.setAlternatives(alternativesForQuestion);
        }

        game.setQuestions(questionsForGame);
    }

    public List<Game> getGamesForTopic(String topic) {
        List<Game> games = gameDao.getGamesForTopic(topic);

        for (Game game : games) {
            addQuestionsAndAlternativesForGame(game);
        }

        return games;
    }

    public void saveGame(Game game) {
        if (game.getId() == 0) {
            int gameId = gameDao.insert(
                    game.getName(),
                    game.getCreator().getShortName(),
                    game.getTopic(),
                    game.getTimeLimit());

            questionService.insert(gameId, game.getQuestions());
        } else {
            gameDao.update(
                    game.getId(),
                    game.getName(),
                    game.getCreator().getShortName(),
                    game.getTopic(),
                    game.getTimeLimit());
            questionService.update(game.getQuestions());
        }
    }


}
