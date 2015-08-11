package no.mesan.mesanquiz.view.newquiz;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import no.mesan.mesanquiz.R;
import no.mesan.mesanquiz.model.QuestionDto;

/**
 * Created by Thomas on 11.08.2015.
 */
public class QuizListAdapter extends RecyclerView.Adapter<QuizListAdapter.ViewHolder> {

    private List<QuestionDto> questions = new ArrayList<>();
    private int answerColor;
    private int black;

    public QuizListAdapter(Context context) {
        answerColor = context.getResources().getColor(R.color.mesan_red);
        black = context.getResources().getColor(R.color.black);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_row, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        QuestionDto questionDto = questions.get(position);

        holder.textViewQuestion.setText(questionDto.getQuestion());
        holder.textViewAlternative1.setText(questionDto.getAlternatives().get(0).getAlternative());
        holder.textViewAlternative2.setText(questionDto.getAlternatives().get(1).getAlternative());
        holder.textViewAlternative3.setText(questionDto.getAlternatives().get(2).getAlternative());
        holder.textViewAlternative4.setText(questionDto.getAlternatives().get(3).getAlternative());


        holder.textViewAlternative1.setTextColor(questionDto.getAlternatives().get(0).isAnswer() ? answerColor : black);
        holder.textViewAlternative2.setTextColor(questionDto.getAlternatives().get(1).isAnswer() ? answerColor : black);
        holder.textViewAlternative3.setTextColor(questionDto.getAlternatives().get(2).isAnswer() ? answerColor : black);
        holder.textViewAlternative4.setTextColor(questionDto.getAlternatives().get(3).isAnswer() ? answerColor : black);
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public void setQuestions(List<QuestionDto> questions) {
        this.questions.clear();
        this.questions.addAll(questions);

        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView textViewQuestion;
        protected TextView textViewAlternative1;
        protected TextView textViewAlternative2;
        protected TextView textViewAlternative3;
        protected TextView textViewAlternative4;

        public ViewHolder(View view) {
            super(view);
            this.textViewQuestion = (TextView) view.findViewById(R.id.textViewQuestion);
            this.textViewAlternative1 = (TextView) view.findViewById(R.id.textViewAlternative1);
            this.textViewAlternative2 = (TextView) view.findViewById(R.id.textViewAlternative2);
            this.textViewAlternative3 = (TextView) view.findViewById(R.id.textViewAlternative3);
            this.textViewAlternative4 = (TextView) view.findViewById(R.id.textViewAlternative4);
        }
    }
}
