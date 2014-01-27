package com.example.mynotes2;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.RatingBar;
import android.widget.TextView;

import com.observer.pattern.ListOfNotes;
import com.observer.pattern.ListOfNotes.Note;

/**
 * @author Andrei. Customized BaseAdapter.
 */
final class CustomizedListViewAdapter extends BaseAdapter implements PropertyChangeListener {
	/**
	 * Interface to global information about an application environment. This is
	 * an abstract class whose implementation is provided by the Android system.
	 * It allows access to application-specific resources and classes, as well
	 * as up-calls for application-level operations such as launching
	 * activities, broadcasting and receiving intents, etc.
	 */
	// private final Context context;

	/**
	 * Instantiates a layout XML file into its corresponding View objects. It is
	 * never used directly. Instead, use getLayoutInflater() or
	 * getSystemService(String) to retrieve a standard LayoutInflater instance
	 * that is already hooked up to the current context and correctly configured
	 * for the device you are running on.
	 */
	private final LayoutInflater layoutInflater;

	/**
	 * Array of ListView element's data.
	 */
	private final List<ListOfNotes.Note> items;

	/**
	 * @param context
	 *            Android system context.
	 * @param itms
	 *            Array of items with data to show in ListView.
	 */
	@SuppressWarnings("unchecked")
	protected CustomizedListViewAdapter(final Context context, final Serializable itms) {
		this.items = (List<ListOfNotes.Note>) itms;
		layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(final int pos) {
		return items.get(pos);
	}

	@Override
	public long getItemId(final int pos) {
		return pos;
	}

	public Note getNote(final int pos) {
		return items.get(pos);
	}

	/**
	 * @param pos
	 *            Position (int) in list.
	 * @return Name of note (string) at target position.
	 */
	public String getName(final int pos) {
		return items.get(pos).getName();
	}

	/**
	 * @param pos
	 *            Position (int) in list.
	 * @return Content of note (string) at target position.
	 */
	public String getContent(final int pos) {
		return items.get(pos).getContent().toString();
	}

	@Override
	public View getView(final int pos, final View convertView,
			final ViewGroup parent) {
		View view = convertView;

		if (view == null) {
			view = layoutInflater.inflate(R.layout.item, parent, false);
		}

		RatingBar rating = (RatingBar) view.findViewById(R.id.ratingBar1);
		rating.setRating(items.get(pos).getRating());
		final CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkToDel);

		if (pos % 2 == 0) {
			view.setBackgroundColor(Color.GRAY);
		} else {
			view.setBackgroundColor(Color.LTGRAY);
		}

		if (MainActivity.deleteMode) {
			rating.setVisibility(View.GONE);
			checkBox.setVisibility(View.VISIBLE);

		} else {
			rating.setVisibility(View.VISIBLE);
			checkBox.setVisibility(View.GONE);
		}

		TextView id = (TextView) view.findViewById(R.id.textView2);
		id.setTextColor(Color.WHITE);

		id.setText(getNote(pos).getTime());
		id.append(getNote(pos).getTime());

		TextView contents = (TextView) view.findViewById(R.id.textView1);
		contents.setTextColor(Color.WHITE);
		contents.setText(getName(pos) + ": " + getContent(pos));

		return view;
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		// TODO Auto-generated method stub

	}

}
