package no.mesan.mesanquiz.view.game;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.otto.Subscribe;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.InjectView;
import butterknife.OnItemClick;
import no.mesan.mesanquiz.R;
import no.mesan.mesanquiz.event.GameEvent;
import no.mesan.mesanquiz.job.GameJob;
import no.mesan.mesanquiz.model.AlternativeDto;
import no.mesan.mesanquiz.model.GameDto;
import no.mesan.mesanquiz.model.QuestionDto;
import no.mesan.mesanquiz.view.AbstractFragment;
import no.mesan.mesanquiz.view.adapter.AlternativeAdapter;
import no.mesan.mesanquiz.view.main.MainActivity;

public class QuestionFragment extends AbstractFragment implements AdapterView.OnItemClickListener{

    @InjectView(R.id.statusTextView)
    TextView statusTextView;

    @InjectView(R.id.questionTextView)
    TextView questionTextView;

    @InjectView(R.id.alternativeList)
    ListView alternativeListView;

    @InjectView(R.id.timeLeftTextView)
    TextView timeLeftTextView;

    private GameDto game;
    private int currentQuestion = 0;
    private int points = 0;
    private int numberOfQuestions;
    private long timePerQuestion = 30000;
    private CountDownTimer timer;
    private Timer ttimer;
    private AlternativeAdapter adapter;
    final static String ARG_POINTS = "POINTS";
    final static String ARG_SIZE = "SIZE";

    public QuestionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((ActionBarActivity) getActivity()).getSupportActionBar().setTitle("Navn på quiz");

        View view = super.onCreateView(inflater, container, savedInstanceState);

        getJobManager().addJobInBackground(new GameJob(getContext()));

        ttimer = new Timer();

        return view;
    }

    @Subscribe
    public void gameReceived(GameEvent gameEvent) {
        game = gameEvent.getGameDto();
        ((ActionBarActivity) getActivity()).getSupportActionBar().setTitle(game.getName());
        startGame(game);
    }

    private void startGame(GameDto game) {
        List<QuestionDto> questions = game.getQuestions();
        numberOfQuestions = questions.size();
        QuestionDto question = questions.get(currentQuestion);
        updateQuestion(question);
    }

    private void updateQuestion(QuestionDto question){
        statusTextView.setText(createStatusText(currentQuestion));
        questionTextView.setText(question.getQuestion());

        adapter = new AlternativeAdapter(getContext(), question.getAlternatives());
        alternativeListView.setAdapter(adapter);
        alternativeListView.setOnItemClickListener(this);

        timer = new CountDownTimer(timePerQuestion, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftTextView.setText((int)millisUntilFinished/1000 + " " + getString(R.string.seconds_left));
            }

            @Override
            public void onFinish() {
                moveToNextQuestion();
            }
        }.start();
    }

    private void moveToNextQuestion() {
        currentQuestion++;
        if (currentQuestion < game.getQuestions().size()) {
            updateQuestion(game.getQuestions().get(currentQuestion));
        } else {
            Bundle bundle = new Bundle();
            bundle.putInt(ARG_POINTS, points);
            bundle.putInt(ARG_SIZE, game.getQuestions().size());

            ((MainActivity)getActivity()).goToResults();
        }
    }

    private String createStatusText(int number) {
        return (number+1) + "/" + numberOfQuestions;
    }

    @Override
    protected int getViewId() {
        return R.layout.fragment_question;
    }

    @OnItemClick(R.id.alternativeList)
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        timer.cancel();
        if(game.getQuestions().get(currentQuestion).getAlternatives().get(position).isAnswer()) {
            points += 1;
            view.setBackgroundColor(getResources().getColor(R.color.green));
        }
        else {
            view.setBackgroundColor(getResources().getColor(R.color.mesan_red));
            List<AlternativeDto> alternatives = game.getQuestions().get(currentQuestion).getAlternatives();
            for (int i = 0; i < alternatives.size(); i++) {
                AlternativeDto alternative = alternatives.get(i);
                if (alternative.isAnswer()) {
                    View v = alternativeListView.getChildAt(i);
                    final AnimationDrawable drawable = new AnimationDrawable();
                    final Handler handler = new Handler();
                    drawable.addFrame(new ColorDrawable(getResources().getColor(R.color.green)), 100);
                    drawable.addFrame(new ColorDrawable(getResources().getColor(R.color.mesan_light_grey)), 100);

                    drawable.setOneShot(false);

                    v.setBackgroundDrawable(drawable);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            drawable.start();
                        }
                    }, 100);
                    //v.setBackgroundColor(getResources().getColor(R.color.green));
                    // TODO: Nicer colors, perhaps blink?
                }
            }
        }

        ttimer.schedule(new QuestionTimerTask(), 1000);
    }

    class QuestionTimerTask extends TimerTask {

        @Override
        public void run() {
            getActivity().runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    moveToNextQuestion();
                }
            });
        }
    }
}

