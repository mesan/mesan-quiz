package no.mesan.mesanquiz.view.newquiz;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.InjectView;
import no.mesan.mesanquiz.R;
import no.mesan.mesanquiz.common.ValueHolder;
import no.mesan.mesanquiz.model.QuestionDto;
import no.mesan.mesanquiz.view.AbstractFragment;
import no.mesan.mesanquiz.view.main.MainActivity;

/**
 * Created by Thomas on 11.08.2015.
 */
public class NewQuizQuestionListFragment extends AbstractFragment {

    @InjectView(R.id.fabNewQuestion)
    FloatingActionButton fabNewQuestion;

    @InjectView(R.id.recyclerViewQuestions)
    RecyclerView recyclerViewQuestions;

    private QuizListAdapter quizListAdapter;

    @Override
    protected int getViewId() {
        return R.layout.fragment_new_quiz_list;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((ActionBarActivity) getActivity()).getSupportActionBar().setTitle(ValueHolder.getInstance(getContext()).getNewGame().getName());

        View view = super.onCreateView(inflater, container, savedInstanceState);
        initGui();
        return view;
    }

    private void initGui() {

        quizListAdapter = new QuizListAdapter(getContext());
        recyclerViewQuestions.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewQuestions.setAdapter(quizListAdapter);

        fabNewQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).goToFragment(NewQuizQuestionFragment.class, false);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        List<QuestionDto> questions = ValueHolder.getInstance(getContext()).getNewGame().getQuestions();
        if(questions != null && !questions.isEmpty()) {
            quizListAdapter.setQuestions(questions);
        }
    }
}
