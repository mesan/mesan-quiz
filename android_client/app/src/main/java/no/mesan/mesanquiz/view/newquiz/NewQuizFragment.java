package no.mesan.mesanquiz.view.newquiz;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.InjectView;
import no.mesan.mesanquiz.R;
import no.mesan.mesanquiz.common.ValueHolder;
import no.mesan.mesanquiz.view.AbstractFragment;
import no.mesan.mesanquiz.view.main.MainActivity;

/**
 * Created by Thomas on 11.08.2015.
 */
public class NewQuizFragment extends AbstractFragment {

    @InjectView(R.id.editTextQuizName)
    EditText editTextQuizName;

    @InjectView(R.id.spinnerQuizTheme)
    Spinner spinnerQuizTheme;

    @InjectView(R.id.buttonCreate)
    Button buttonCreate;

    @InjectView(R.id.seekBarQuizTime)
    SeekBar seekBarQuizTime;

    @InjectView(R.id.textViewTime)
    TextView textViewTime;

    private String[] themes = new String[] {"Velg tema", "Natur", "Geografi", "Teknologi", "Meteorologi", "Ymse"};

    private String selectedTheme;
    private int numberOfSeconds;

    @Override
    protected int getViewId() {
        return R.layout.fragment_new_quiz;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((ActionBarActivity) getActivity()).getSupportActionBar().setTitle("Opprett quiz");

        View view = super.onCreateView(inflater, container, savedInstanceState);
        initGui();
        ValueHolder.getInstance(getContext()).createNewGameTemp();
        return view;
    }

    private void initGui() {
        seekBarQuizTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                setTime();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        spinnerQuizTheme.setAdapter(new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1,
                themes));

        spinnerQuizTheme.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedTheme = (i > 0) ? themes[i] : null;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = editTextQuizName.getText().toString();
                if (name.isEmpty()) {
                    Toast.makeText(getContext(), "Du må skrive navn på quizen", Toast.LENGTH_LONG).show();
                    return;
                }

                if (selectedTheme == null) {
                    Toast.makeText(getContext(), "Du må velge tema", Toast.LENGTH_LONG).show();
                    return;
                }

                ValueHolder.getInstance(getContext()).getNewGame().setName(name);
                ValueHolder.getInstance(getContext()).getNewGame().setTimeLimit(numberOfSeconds);
                ValueHolder.getInstance(getContext()).getNewGame().setTopic(selectedTheme);

                ((MainActivity) getActivity()).goToFragment(NewQuizQuestionListFragment.class, false);
            }
        });

        setTime();
    }

    private void setTime() {
        numberOfSeconds = seekBarQuizTime.getProgress() * 10 + 20;
        textViewTime.setText(numberOfSeconds + "s");
    }
}