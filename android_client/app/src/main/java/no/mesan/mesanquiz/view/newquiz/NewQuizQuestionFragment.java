package no.mesan.mesanquiz.view.newquiz;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import no.mesan.mesanquiz.R;
import no.mesan.mesanquiz.common.ValueHolder;
import no.mesan.mesanquiz.model.AlternativeDto;
import no.mesan.mesanquiz.model.QuestionDto;
import no.mesan.mesanquiz.view.AbstractFragment;
import no.mesan.mesanquiz.view.main.MainActivity;

/**
 * Created by Thomas on 11.08.2015.
 */
public class NewQuizQuestionFragment extends AbstractFragment implements CompoundButton.OnCheckedChangeListener {

    @InjectView(R.id.radioButtonAlternative1)
    RadioButton radioButtonAlternative1;

    @InjectView(R.id.radioButtonAlternative2)
    RadioButton radioButtonAlternative2;

    @InjectView(R.id.radioButtonAlternative3)
    RadioButton radioButtonAlternative3;

    @InjectView(R.id.radioButtonAlternative4)
    RadioButton radioButtonAlternative4;

    @InjectView(R.id.editTextAlternative1)
    EditText editTextAlternative1;

    @InjectView(R.id.editTextAlternative2)
    EditText editTextAlternative2;

    @InjectView(R.id.editTextAlternative3)
    EditText editTextAlternative3;

    @InjectView(R.id.editTextAlternative4)
    EditText editTextAlternative4;

    @InjectView(R.id.buttonCreate)
    Button buttonCreate;

    @InjectView(R.id.editTextQuizQuestionName)
    EditText editTextQuizQuestionName;

    private boolean isChecked;

    @Override
    protected int getViewId() {
        return R.layout.fragment_new_quiz_question;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((ActionBarActivity) getActivity()).getSupportActionBar().setTitle("Nytt spørsmål");

        View view = super.onCreateView(inflater, container, savedInstanceState);
        initGui();
        return view;
    }

    private void initGui() {

        radioButtonAlternative1.setOnCheckedChangeListener(this);
        radioButtonAlternative2.setOnCheckedChangeListener(this);
        radioButtonAlternative3.setOnCheckedChangeListener(this);
        radioButtonAlternative4.setOnCheckedChangeListener(this);

        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextQuizQuestionName.getText().toString();

                String alt1 = editTextAlternative1.getText().toString();
                String alt2 = editTextAlternative2.getText().toString();
                String alt3 = editTextAlternative3.getText().toString();
                String alt4 = editTextAlternative4.getText().toString();

                if (name.isEmpty()) {
                    Toast.makeText(getContext(), "Du må sette inn tittel", Toast.LENGTH_LONG).show();
                    return;
                }

                if (alt1.isEmpty() || alt2.isEmpty() || alt3.isEmpty() || alt4.isEmpty()) {
                    Toast.makeText(getContext(), "Du må fylle inn alle spørsmål", Toast.LENGTH_LONG).show();
                    return;
                }

                if (!isChecked) {
                    Toast.makeText(getContext(), "Du må velge fasitsvar", Toast.LENGTH_LONG).show();
                    return;
                }

                List<AlternativeDto> alternatives = new ArrayList<AlternativeDto>();
                alternatives.add(new AlternativeDto(alt1, radioButtonAlternative1.isChecked()));
                alternatives.add(new AlternativeDto(alt2, radioButtonAlternative2.isChecked()));
                alternatives.add(new AlternativeDto(alt3, radioButtonAlternative3.isChecked()));
                alternatives.add(new AlternativeDto(alt4, radioButtonAlternative4.isChecked()));

                QuestionDto questionDto = new QuestionDto();
                questionDto.setQuestion(name);
                questionDto.setAlternatives(alternatives);

                ValueHolder.getInstance(getContext()).getNewGame().addQuestion(questionDto);

                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
        radioButtonAlternative1.setChecked(false);
        radioButtonAlternative2.setChecked(false);
        radioButtonAlternative3.setChecked(false);
        radioButtonAlternative4.setChecked(false);

        if(checked) {
            isChecked = true;
            compoundButton.setChecked(true);
        }
    }
}
