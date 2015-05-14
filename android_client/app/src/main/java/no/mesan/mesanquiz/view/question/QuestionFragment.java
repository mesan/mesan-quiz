package no.mesan.mesanquiz.view.question;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.otto.Subscribe;

import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;
import no.mesan.mesanquiz.R;
import no.mesan.mesanquiz.event.GameEvent;
import no.mesan.mesanquiz.job.GameJob;
import no.mesan.mesanquiz.model.GameDto;
import no.mesan.mesanquiz.model.QuestionDto;
import no.mesan.mesanquiz.view.AbstractFragment;

public class QuestionFragment extends AbstractFragment {

    @InjectView(R.id.statusTextView)
    TextView statusTextView;

    @InjectView(R.id.questionTextView)
    TextView questionTextView;

    @InjectView(R.id.button1)
    Button button1;

    @InjectView(R.id.button2)
    Button button2;

    @InjectView(R.id.button3)
    Button button3;

    /*@InjectView(R.id.button4)
    Button button4;*/

    @InjectView(R.id.timeLeftTextView)
    TextView timeLeftTextView;

    private GameDto game;
    private int currentQuestion = 0;
    private int points = 0;
    private int numberOfQuestions;

    public QuestionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((ActionBarActivity) getActivity()).getSupportActionBar().setTitle("Navn på quiz");

        View view = super.onCreateView(inflater, container, savedInstanceState);

        getJobManager().addJobInBackground(new GameJob(getContext()));

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
        // TODO: Dynamic number of buttons ?
        button1.setText(question.getAlternatives().get(0).getAlternative());
        button2.setText(question.getAlternatives().get(1).getAlternative());
        button3.setText(question.getAlternatives().get(2).getAlternative());
        //button4.setText(question.getAlternatives().get(3).getAlternative());
        // TODO: Start timer
    }

    private int getPressedButtonId(Button b) {
        int pressedButton = -1;

        switch(b.getId()) {
            case R.id.button1:
                pressedButton = 0;
                break;
            case R.id.button2:
                pressedButton = 1;
                break;
            case R.id.button3:
                pressedButton = 2;
                break;
            default:
        }
        return pressedButton;
    }

    @OnClick({R.id.button1, R.id.button2, R.id.button3})
    public void questionAnswered(Button b) {

        int pressedButton = getPressedButtonId(b);

        if(game.getQuestions().get(currentQuestion).getAlternatives().get(pressedButton).isAnswer()) {
            // TODO: Animation color on correct
            points += 1;
        }
        else {
            // TODO: Animation color on correct and wrong
        }

        moveToNextQuestion();
    }

    private void moveToNextQuestion() {
        if(currentQuestion < game.getQuestions().size() - 1 ){
            currentQuestion++;
            updateQuestion(game.getQuestions().get(currentQuestion));
        }
        else {
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
}
