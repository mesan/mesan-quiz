package no.mesan.mobil.mesanquiz.service;

import no.mesan.mobil.mesanquiz.dao.AlternativeDao;
import no.mesan.mobil.mesanquiz.domain.Alternative;

import java.util.List;

public class AlternativeService {

    private AlternativeDao alternativeDao;

    public AlternativeService(AlternativeDao alternativeDao) {
        this.alternativeDao = alternativeDao;
    }

    public void insert(int questionId, List<Alternative> alternatives) {
        for (Alternative alternative : alternatives) {
            alternativeDao.insert(questionId, alternative.getAlternative(), alternative.isAnswer());
        }
    }

    public void update(List<Alternative> alternatives) {
        for (Alternative alternative : alternatives) {
            alternativeDao.insert(alternative.getQuestionId(), alternative.getAlternative(), alternative.isAnswer());
        }
    }
}
