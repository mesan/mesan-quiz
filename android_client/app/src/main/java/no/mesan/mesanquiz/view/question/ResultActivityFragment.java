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

        View view = super.onCreateView(inflater, container, savedInstanceState);

        Intent i = getActivity().getIntent();
        int points = i.getIntExtra(QuestionFragment.ARG_POINTS, 0);
        int size = i.getIntExtra(QuestionFragment.ARG_SIZE, 0);

        resultTextView.setText(points + "/" + size + " riktige!");
        resultCommentTextView.setText("Ganske bra :)"); // TODO: Make comment string based on result

        getJobManager().addJobInBackground(new ScoreJob(getContext()));

        return view;
    }

    @Subscribe
    public void scoreReceived(ScoreEvent scoreEvent) {
        scores = scoreEvent.getScoreDtoList();
        //((ActionBarActivity) getActivity()).getSupportActionBar().setTitle(scores.get(0).getCorrectAnswers());

        Log.d("SCORE", scores.get(0).toString());
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
