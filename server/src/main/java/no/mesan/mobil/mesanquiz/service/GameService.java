package no.mesan.mobil.mesanquiz.service;

import no.mesan.mobil.mesanquiz.dao.GameDao;
import no.mesan.mobil.mesanquiz.domain.Game;

public class GameService {

    private GameDao gameDao;

    public GameService(GameDao gameDao) {
        this.gameDao = gameDao;
    }

    public Game getGame(long id) {
        //return new Game(1L, "Jan Eriks superquiz", 10);

        return gameDao.getGame(id);
    }
}
