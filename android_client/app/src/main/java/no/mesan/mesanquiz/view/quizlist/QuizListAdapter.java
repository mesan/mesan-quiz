package no.mesan.mesanquiz.view.quizlist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import no.mesan.mesanquiz.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import no.mesan.mesanquiz.model.GameDto;

/**
 * Created by janeh on 11.08.2015.
 */
public class QuizListAdapter extends RecyclerView.Adapter<QuizListAdapter.ViewHolder> {

    private List<GameDto> games = new ArrayList<>();

    public static class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView gameTextView;
        public ViewHolder(View v) {
            super(v);
            gameTextView = (TextView) v.findViewById(R.id.quiz_list_item);
        }
    }

    public void setGames(List<GameDto> games) {
        this.games.clear();
        this.games.addAll(games);
        notifyDataSetChanged();
    }

    @InjectView(R.id.quiz_list_item)
    TextView listItem;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quizlist_item, null);

        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(QuizListAdapter.ViewHolder holder, int position) {
        holder.gameTextView.setText(games.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return games.size();
    }
}
