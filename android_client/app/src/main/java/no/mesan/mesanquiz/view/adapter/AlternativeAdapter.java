package no.mesan.mesanquiz.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import no.mesan.mesanquiz.R;
import no.mesan.mesanquiz.model.AlternativeDto;

public class AlternativeAdapter extends ArrayAdapter<AlternativeDto> {
    private List<AlternativeDto> alternatives;

    public AlternativeAdapter(Context context, List<AlternativeDto> alternatives) {
        super(context, 0, alternatives);
        this.alternatives = alternatives;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.list_item_alternative, parent, false);

            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        AlternativeDto alternative = alternatives.get(position);
        if (alternative != null) {
            viewHolder.alternativeTextView.setText(alternative.getAlternative());
        }

        return convertView;
    }

    static class ViewHolder {
        TextView alternativeTextView;

        public ViewHolder(View v) {
            alternativeTextView = (TextView)v.findViewById(R.id.alternative_textView);
        }
    }
}
