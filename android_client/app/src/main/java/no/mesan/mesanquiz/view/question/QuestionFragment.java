package no.mesan.mesanquiz.view.question;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.otto.Subscribe;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.OnItemClick;
import no.mesan.mesanquiz.R;
import no.mesan.mesanquiz.event.GameEvent;
import no.mesan.mesanquiz.job.GameJob;
import no.mesan.mesanquiz.model.AlternativeDto;
import no.mesan.mesanquiz.model.GameDto;
import no.mesan.mesanquiz.model.QuestionDto;
import no.mesan.mesanquiz.view.AbstractFragment;
import no.mesan.mesanquiz.view.adapter.AlternativeAdapter;

public class QuestionFragment extends AbstractFragment implements AdapterView.OnItemClickListener{

    @InjectView(R.id.statusTextView)
    TextView statusTextView;

    @InjectView(R.id.questionTextView)
    TextView questionTextView;

    /*@InjectView(R.id.button1)
    Button button1;

    @InjectView(R.id.button2)
    Button button2;

    @InjectView(R.id.button3)
    Button button3;

    @InjectView(R.id.button4)
    Button button4;*/

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

    public QuestionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((ActionBarActivity) getActivity()).getSupportActionBar().setTitle("Navn på quiz");

        View view = super.onCreateView(inflater, container, savedInstanceState);

        // TODO: Spinner before load
        getJobManager().addJobInBackground(new GameJob(getContext()));

        ttimer = new Timer();

        return view;
    }

    @Subscribe
    public void gameReceived(GameEvent gameEvent) {
        // TODO: Spinner off
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

    /*
    @OnClick({R.id.button1, R.id.button2, R.id.button3})
    public void questionAnswered(Button b) {

        timer.cancel();

        int pressedButton = getPressedButtonId(b);

        if(game.getQuestions().get(currentQuestion).getAlternatives().get(pressedButton).isAnswer()) {
            points += 1;
            b.setBackgroundColor(getResources().getColor(R.color.green));
        }
        else {
            b.setBackgroundColor(getResources().getColor(R.color.mesan_red_dark));
            // Button correct
            if(game.getQuestions().get(currentQuestion).getAlternatives().get(0).isAnswer()) {
                button1.setBackgroundColor(getResources().getColor(R.color.green));
            }
            else if(game.getQuestions().get(currentQuestion).getAlternatives().get(1).isAnswer()) {
                button2.setBackgroundColor(getResources().getColor(R.color.green));
            }
            else if(game.getQuestions().get(currentQuestion).getAlternatives().get(2).isAnswer()) {
                button3.setBackgroundColor(getResources().getColor(R.color.green));
                // TODO: Nicer colors, perhaps blink?
            }
        }

        ttimer.schedule(new QuestionTimerTask(), 1000);
    }*/

    private void moveToNextQuestion() {
        if(currentQuestion < game.getQuestions().size() - 1 ){
            currentQuestion++;
            updateQuestion(game.getQuestions().get(currentQuestion));
        }
        else {
            // TODO: Send to result page
            Toast.makeText(getContext(), "Du fikk " + points + " poeng!", Toast.LENGTH_LONG).show();
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

            for (AlternativeDto alternative : game.getQuestions().get(currentQuestion).getAlternatives()) {
                if (alternative.isAnswer()) {
                    //int position = alternative.getId();
                }
            }
            /*
            // Button correct
            if(game.getQuestions().get(currentQuestion).getAlternatives().get(0).isAnswer()) {
                button1.setBackgroundColor(getResources().getColor(R.color.green));
            }
            else if(game.getQuestions().get(currentQuestion).getAlternatives().get(1).isAnswer()) {
                button2.setBackgroundColor(getResources().getColor(R.color.green));
            }
            else if(game.getQuestions().get(currentQuestion).getAlternatives().get(2).isAnswer()) {
                button3.setBackgroundColor(getResources().getColor(R.color.green));
                // TODO: Nicer colors, perhaps blink?
            }*/
        }

        ttimer.schedule(new QuestionTimerTask(), 1000);
    }

    class QuestionTimerTask extends TimerTask {

        @Override
        public void run() {
            getActivity().runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    // Reset all colors

                    /*
                    button1.setBackgroundColor(getResources().getColor(R.color.light_grey));
                    button2.setBackgroundColor(getResources().getColor(R.color.light_grey));
                    button3.setBackgroundColor(getResources().getColor(R.color.light_grey));*/
                    moveToNextQuestion();
                }
            });
        }
    }
}

