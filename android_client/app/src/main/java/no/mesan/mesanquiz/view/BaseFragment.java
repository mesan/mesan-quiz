package no.mesan.mesanquiz.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by kristint on 5/12/2015.
 */
public abstract class BaseFragment extends Fragment {
    protected Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = getActivity();

        return super.onCreateView(inflater, container, savedInstanceState);
    }

}
