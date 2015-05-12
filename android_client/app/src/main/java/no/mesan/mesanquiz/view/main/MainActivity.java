package no.mesan.mesanquiz.view.main;

import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.squareup.otto.Subscribe;

import butterknife.InjectView;
import no.mesan.mesanquiz.R;
import no.mesan.mesanquiz.event.GameEvent;
import no.mesan.mesanquiz.job.GameJob;
import no.mesan.mesanquiz.view.AbstractActivity;

public class MainActivity extends AbstractActivity {

    @InjectView(R.id.quiz_toolbar)
    Toolbar toolbar;

    @InjectView(R.id.textViewGame)
    TextView textViewGame;

    @Override
    protected int getViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected Toolbar getToolbar() {
        return toolbar;
    }

    @Subscribe
    public void gameFetched(GameEvent gameEvent) {
        if (gameEvent.hasErrors()) {
            Log.d("LOG", "ERROR: " + gameEvent.getErrorMessage());
            return;
        }
        textViewGame.setText("Antall innlegg: " + (gameEvent.getGameDto().getQuestions() != null? gameEvent.getGameDto().getQuestions().size() : "null"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_new_game) {
            getJobManager().addJobInBackground(new GameJob(getApplicationContext()));
        }

        return super.onOptionsItemSelected(item);
    }
}
