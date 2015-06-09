package no.mesan.mesanquiz.view.profile;

import android.app.ProgressDialog;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.Transformation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.squareup.otto.Subscribe;

import butterknife.InjectView;
import no.mesan.mesanquiz.R;
import no.mesan.mesanquiz.model.PersonDto;
import no.mesan.mesanquiz.view.AbstractFragment;

public class PersonFragment extends AbstractFragment {

	@InjectView(R.id.swipeRefreshPeople)
    protected SwipeRefreshLayout swipeRefreshPeople;

	@InjectView(R.id.listViewPeople)
	protected ListView listViewPeople;

	private ProgressDialog dialog;

	@DimensionPixelSizeRes(R.dimen.list_row_persons_min_height)
	protected int listRowMinHeight;

	private List<PersonDto> persons;

	@Override
	public void init() {
		listViewPeople.setAdapter(personAdapter);
		listViewPeople.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
				LinearLayout layout = (LinearLayout) ((FrameLayout) view).getChildAt(0);
				View imageViewPerson = layout.getChildAt(0);

				RelativeLayout layoutTexts = (RelativeLayout) layout.getChildAt(1);
				View textViewPersonName = layoutTexts.getChildAt(0);
				View textViewPersonEmail = layoutTexts.getChildAt(1);

				if (imageViewPerson.getScaleX() > 1.5f) {
					collapse(layout, 300);
					imageViewPerson.animate().scaleX(1f).scaleY(1f).translationX(0).setDuration(1000).setInterpolator(new BounceInterpolator());
					textViewPersonName.animate().setDuration(300).scaleX(1).scaleY(1).translationX(0);
					textViewPersonEmail.animate().setDuration(300).scaleX(1).scaleY(1).alpha(0f).translationX(0);
				} else {
					expand(layout, 300);
					imageViewPerson.animate().scaleX(3f).scaleY(3f).setDuration(1000).translationX(imageViewPerson.getMeasuredWidth() / 2.0f)
							.setInterpolator(new BounceInterpolator());
					textViewPersonName.animate().setDuration(300).scaleX(1.25f).scaleY(1.25f).translationX(imageViewPerson.getMeasuredWidth() * 1.2f);
					textViewPersonEmail.animate().setDuration(300).scaleX(1.25f).scaleY(1.25f).alpha(1f)
							.translationX(imageViewPerson.getMeasuredWidth() * 1.2f);
				}

			}
		});

		if (this.persons == null) {
			personTask.getPersonsFromDb();
		} else {
			setData();
		}

        swipeRefreshPeople.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                personTask.getPersons();
            }
        });
	}

	@OptionsItem(R.id.menuItemRefresh)
	protected void refreshClicked() {
		dialog = ProgressDialog.show(context, context.getString(R.string.persons_get), context.getString(R.string.loading_data), true, true);
		personTask.getPersons();
	}

	@Subscribe
	public void asyncPersonsFetched(PersonEvent personEvent) {

		if (dialog != null) {
			dialog.dismiss();
			dialog = null;
		}

        if (swipeRefreshPeople.isRefreshing()) {
            swipeRefreshPeople.setRefreshing(false);
        }

        if (personEvent.hasErrors()) {
			UiUtil.showErrorMessage(context, personEvent.getErrorMessage());
			return;
		}

		this.persons = personEvent.getPersons();

		setData();
	}

	private void setData() {
		personAdapter.setData(this.persons);

		if (SharedPreferencesUtil.shouldSyncPersons(context)) {
			refreshClicked();
		}
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

		if (menuItemNewPush != null) {
			menuItemNewPush.setVisible(false);
		}

		if (menuItemRefresh != null) {
			menuItemRefresh.setVisible(true);
		}
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	protected int getViewId() {
		return 0;
	}

	@Override
	public void onResume() {
		super.onResume();

		if (menuItemNewPush != null) {
			menuItemNewPush.setVisible(false);
		}

		if (menuItemRefresh != null) {
			menuItemRefresh.setVisible(true);
		}
		BusProvider.getInstance().register(this);
	}

	@Override
	public void onPause() {
		super.onPause();
		BusProvider.getInstance().unregister(this);

		if (menuItemNewPush != null) {
			menuItemNewPush.setVisible(false);
		}

		if (menuItemRefresh != null) {
			menuItemRefresh.setVisible(false);
		}
	}

	public void expand(final View v, final int duration) {
		final float targetHeight = listRowMinHeight * 1.6f;

		v.getLayoutParams().height = v.getMeasuredHeight();
		Animation a = new Animation() {
			@Override
			protected void applyTransformation(float interpolatedTime, Transformation t) {
				v.getLayoutParams().height = Math.round(listRowMinHeight + (targetHeight - listRowMinHeight) * interpolatedTime);
				v.requestLayout();
			}

			@Override
			public boolean willChangeBounds() {
				return true;
			}
		};

		// Default duration
		if (duration < 0) {
			// 1dp/ms
			a.setDuration((int) (targetHeight / v.getContext().getResources().getDisplayMetrics().density));
		} else {
			a.setDuration(duration);
		}
		// a.setFillAfter(true);
		a.setInterpolator(new AccelerateDecelerateInterpolator());
		v.startAnimation(a);
	}

	public void collapse(final View v, final int duration) {
		final float initialHeight = listRowMinHeight * 1.6f;
		final float targetHeight = listRowMinHeight;

		v.getLayoutParams().height = v.getMeasuredHeight();
		Animation a = new Animation() {
			@Override
			protected void applyTransformation(float interpolatedTime, Transformation t) {
				v.getLayoutParams().height = Math.round(initialHeight - (int) ((initialHeight - targetHeight) * interpolatedTime));
				v.requestLayout();
			}

			@Override
			public boolean willChangeBounds() {
				return true;
			}
		};

		// Default duration
		if (duration < 0) {// 1dp/ms
			a.setDuration((int) (initialHeight / v.getContext().getResources().getDisplayMetrics().density));
		} else {
			a.setDuration(duration);
		}
		a.setInterpolator(new AccelerateDecelerateInterpolator());
		// a.setFillAfter(true);
		v.startAnimation(a);
	}
}
