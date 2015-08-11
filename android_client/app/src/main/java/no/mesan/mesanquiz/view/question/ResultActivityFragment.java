package no.mesan.mesanquiz.view.question;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.squareup.otto.Subscribe;

import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;
import no.mesan.mesanquiz.R;
import no.mesan.mesanquiz.event.GameEvent;
import no.mesan.mesanquiz.event.ScoreEvent;
import no.mesan.mesanquiz.job.GameJob;
import no.mesan.mesanquiz.job.ScoreJob;
import no.mesan.mesanquiz.model.GameDto;
import no.mesan.mesanquiz.model.ScoreDto;
import no.mesan.mesanquiz.view.AbstractFragment;

public class ResultActivityFragment extends AbstractFragment {

    @InjectView(R.id.resultTextView)
    TextView resultTextView;

    @InjectView(R.id.resultCommentTextView)
    TextView resultCommentTextView;

    private List<ScoreDto> scores;

    public ResultActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ((ActionBarActivity) getActivity()).getSupportActionBar().setTitle("Ferdig!");

        View view = super.onCreateView(inflater, container, savedInstanceState);

        Bundle bundle = getArguments();
        int points = bundle.getInt(QuestionFragment.ARG_POINTS, 0);
        int size = bundle.getInt(QuestionFragment.ARG_SIZE, 0);

        resultTextView.setText(points + "/" + size + " riktige!");

        getJobManager().addJobInBackground(new ScoreJob(getContext()));

        return view;
    }

    @Subscribe
    public void scoreReceived(ScoreEvent scoreEvent) {
        scores = scoreEvent.getScoreDtoList();

        resultCommentTextView.setText("Toppliste\n\n");

        for (int i = 0; i < scores.size(); i++) {
            resultCommentTextView.append(i + ") " + scores.get(i).getPlayer().getFullName());
        }

    }

    @Override
    protected int getViewId() {
        return R.layout.fragment_result;
    }

    @OnClick(R.id.returnButton)
    public void onReturnButtonClicked(Button b) {
        // TODO: Return to quiz list?
    }
}
