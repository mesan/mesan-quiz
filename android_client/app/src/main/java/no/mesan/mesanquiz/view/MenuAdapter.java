package no.mesan.mesanquiz.view;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import no.mesan.mesanquiz.R;
import no.mesan.mesanquiz.view.model.MenuItem;

public class MenuAdapter extends BaseAdapter {

    private List<MenuItem> menuItems = new ArrayList<>();

    protected LayoutInflater inflater;
    protected Context context;
    protected FragmentManager fragmentManager;

    public MenuAdapter(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    @Override
    public int getCount() {
        return menuItems.size();
    }

    @Override
    public Object getItem(int position) {
        return menuItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView == null) {
            convertView = inflater.inflate(R.layout.row_menu, null);

            viewHolder = new ViewHolder();
            viewHolder.textViewMenuItem = (TextView) convertView.findViewById(R.id.textViewMenuItem);
            viewHolder.imageViewMenuItem = (ImageView) convertView.findViewById(R.id.imageViewMenuItem);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        MenuItem menuItem = menuItems.get(position);

        viewHolder.textViewMenuItem.setText(menuItem.getName());
        viewHolder.imageViewMenuItem.setImageResource(menuItem.getIcon());

        return convertView;
    }


    public void setData(List<MenuItem> menuItems) {
        this.menuItems.clear();
        this.menuItems.addAll(menuItems);

        notifyDataSetChanged();
    }

    public void selectMenuItem(int position) {
        if (position >= menuItems.size()) {
            return;
        }

        Class<? extends Fragment> fragment = menuItems.get(position).getFragment();

        if (fragment != null) {
            Fragment findFragmentByTag = fragmentManager.findFragmentByTag(fragment.getName());

            if (findFragmentByTag == null || !findFragmentByTag.isAdded()) {
//                Log.d("FRAGMENT", fragment.getName());
                // Insert the fragment by replacing any existing fragment
                goToFragment(fragment, true);
            }
        }
    }

    private void goToFragment(Class<? extends Fragment> fragment, boolean animate) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (animate) {
            transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
        }
        try {
            Fragment fragmentInstance = fragment.newInstance();
            transaction.replace(R.id.mainContainer, fragmentInstance, fragment.getName());
            transaction.addToBackStack(null);
            transaction.commitAllowingStateLoss();
        } catch (Exception e) {
            Log.e(getClass().getName(), "Failed to create fragment instance: ", e);
        }
    }

    static class ViewHolder {
        public TextView textViewMenuItem;
        public ImageView imageViewMenuItem;
    }
}
