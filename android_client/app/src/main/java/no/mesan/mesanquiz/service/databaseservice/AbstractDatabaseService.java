package no.mesan.mesanquiz.service.databaseservice;

import android.content.Context;

/**
 * Created by n23995 on 22/04/15.
 */
public abstract class AbstractDatabaseService {

    public Context context;

    public AbstractDatabaseService(Context context) {
        this.context = context;

    }
}
