package no.mesan.mesanquiz.view.quizlist;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.otto.Subscribe;

import butterknife.InjectView;
import no.mesan.mesanquiz.R;
import no.mesan.mesanquiz.common.ValueHolder;
import no.mesan.mesanquiz.event.GameListEvent;
import no.mesan.mesanquiz.job.GameListJob;
import no.mesan.mesanquiz.view.AbstractFragment;

/**
 * Created by Thomas on 11.08.2015.
 */
public class QuizListFragment extends AbstractFragment {

    private ValueHolder valueHolder;

    @InjectView(R.id.quiz_list)
    RecyclerView quizList;

    private QuizListAdapter quizListAdapter;

    @Override
    protected int getViewId() {
        return R.layout.fragment_quizlist;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        valueHolder = ValueHolder.getInstance(context);

        quizList.setLayoutManager(new LinearLayoutManager(context));
        quizListAdapter = new QuizListAdapter();
        quizList.setAdapter(quizListAdapter);

        getJobManager().addJobInBackground(new GameListJob(getContext()));

        return view;
    }

    @Subscribe
    public void gamesReceived(GameListEvent gameListEvent) {
        Log.d("gamesReceived()", "list size: " + gameListEvent.getGameDtoList().size());
        quizListAdapter.setGames(gameListEvent.getGameDtoList());
    }
}
