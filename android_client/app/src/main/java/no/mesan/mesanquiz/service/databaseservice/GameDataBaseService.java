package no.mesan.mesanquiz.service.databaseservice;

import android.content.Context;
import android.util.Log;

import java.util.List;

import no.mesan.mesanquiz.model.EventDto;
import no.mesan.mesanquiz.model.GameDto;
import no.mesan.mesanquiz.model.ProgramDto;
import no.mesan.mesanquiz.model.QuestionDto;


public class GameDataBaseService extends AbstractDatabaseService {


    public GameDataBaseService(Context context) {
        super(context);
    }

    public void saveGame(final GameDto gameToBeSaved) {
        gameToBeSaved.save();
        for (QuestionDto question : gameToBeSaved.getQuestions()){
            Log.d("KLOVN", "questions: " + question.getId());
        }

        EventDto.saveInTx(gameToBeSaved.getQuestions());
    }

    public GameDto getGames() {
        List<GameDto> gameList = GameDto.listAll(GameDto.class);
        List<QuestionDto> questionDtoList = QuestionDto.listAll(QuestionDto.class);

        GameDto gameDto = gameList != null && !gameList.isEmpty() ? gameList.get(0) : null;
        if(gameDto!=null){
            gameDto.setQuestions(questionDtoList);
        }
        return gameDto;
    }

}
