package no.mesan.mesanquiz.view.profile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.SystemService;
import org.androidannotations.annotations.res.DimensionPixelSizeRes;

import java.util.ArrayList;
import java.util.List;

import no.mesan.thomasp.faghelg.android.R;
import no.mesan.thomasp.faghelg.android.model.domain.Person;
import no.mesan.thomasp.faghelg.android.ui.custom.RoundedImageView;

@EBean
public class PersonAdapter extends BaseAdapter {

	private List<Person> persons = new ArrayList<>();

	@SystemService
	protected LayoutInflater inflater;

	@RootContext
	protected Context context;

	@DimensionPixelSizeRes(R.dimen.list_row_persons_min_height)
	protected int listRowMinHeight;

	@Override
	public int getCount() {
		return persons.size();
	}

	@Override
	public Object getItem(int position) {
		return persons.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;

		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = inflater.inflate(R.layout.row_person, null);
			viewHolder.textViewPersonName = (TextView) convertView.findViewById(R.id.textViewPersonName);
			viewHolder.textViewPersonEmail = (TextView) convertView.findViewById(R.id.textViewPersonEmail);
			viewHolder.imageViewPerson = (RoundedImageView) convertView.findViewById(R.id.imageViewPerson);
			viewHolder.layoutPersonWrapper = convertView.findViewById(R.id.layoutPersonWrapper);

			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		Person person = persons.get(position);

		viewHolder.textViewPersonName.setText(person.getFullName());
		viewHolder.textViewPersonEmail.setText(person.getShortName() + "@mesan.no");

		Picasso.with(context).load(person.getProfileImageUrl()).into(viewHolder.imageViewPerson);

		viewHolder.layoutPersonWrapper.clearAnimation();
		viewHolder.layoutPersonWrapper.getLayoutParams().height = listRowMinHeight;

		viewHolder.textViewPersonName.setTranslationX(0);
		viewHolder.textViewPersonName.setScaleX(1);
		viewHolder.textViewPersonName.setScaleY(1);
		viewHolder.textViewPersonEmail.setAlpha(0f);
		viewHolder.imageViewPerson.setTranslationX(0);
		viewHolder.imageViewPerson.setScaleX(1);
		viewHolder.imageViewPerson.setScaleY(1);

		return convertView;
	}

	public void setData(List<Person> persons) {
		this.persons.clear();
		this.persons.addAll(persons);

		notifyDataSetChanged();
	}

	static class ViewHolder {
		public TextView textViewPersonName;
		public TextView textViewPersonEmail;
		public RoundedImageView imageViewPerson;
		public View layoutPersonWrapper;
	}
}
