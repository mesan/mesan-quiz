package no.mesan.mobil.mesanquiz.service;

import no.mesan.mobil.mesanquiz.dao.AlternativeDao;
import no.mesan.mobil.mesanquiz.dao.QuestionDao;
import no.mesan.mobil.mesanquiz.domain.Question;

import java.util.List;

public class QuestionService {

    private final QuestionDao questionDao;
    private final AlternativeService alternativeService;

    public QuestionService(QuestionDao questionDao, AlternativeService alternativeService) {
        this.questionDao = questionDao;
        this.alternativeService = alternativeService;
    }

    public void insert(int gameId, List<Question> questions) {
        for (Question question : questions) {
            int questionId = questionDao.insert(gameId, question.getQuestion());
            alternativeService.insert(questionId, question.getAlternatives());
        }
    }

    public void update(List<Question> questions) {
        for (Question question : questions) {
            questionDao.update(question.getId(), question.getGameId(), question.getQuestion());
            alternativeService.update(question.getAlternatives());
        }
    }

}
