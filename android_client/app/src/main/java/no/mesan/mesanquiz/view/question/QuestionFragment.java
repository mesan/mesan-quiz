package no.mesan.mesanquiz.view.question;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import no.mesan.mesanquiz.R;
import no.mesan.mesanquiz.model.GameDto;
import no.mesan.mesanquiz.view.AbstractFragment;

public class QuestionFragment extends AbstractFragment {
    private GameDto game = new GameDto();

    public QuestionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((ActionBarActivity) getActivity()).getSupportActionBar().setTitle("Navn på quiz");

        // Inflate the layout for this fragment
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected int getViewId() {
        return R.layout.fragment_question;
    }
}
