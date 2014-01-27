package com.observer.pattern;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.text.format.Time;
import android.util.Log;

public class ListOfNotes implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3050524806716091493L;
	public static final String TIME = "";
	public static final String DATE = "";
	public static final String NAME = "";
	public static final String CONTENT = "";
	public static final int RATING = 0;
	private static final ListOfNotes INSTANCE = new ListOfNotes();
	private static List<Note> notes = new ArrayList<Note>();
	private final List<PropertyChangeListener> listener = new ArrayList<PropertyChangeListener>();

	/**
	 * A note item representing a piece of content.
	 */
	public class Note implements Serializable {
		private static final long serialVersionUID = 5074366749052153204L;
		private String name;
		private String content;
		private String time;
		private String date;
		private int rating;

		public Note() {
			setName("");
			Log.d("myLogs", "Empty name for new note created.");
			setContent("");
			Log.d("myLogs", "Empty content for new note created.");
			Time t = new Time();
			t.setToNow();
			String fullTimeStamp = t.toString();
			String rawDate = fullTimeStamp.split("\\D")[0];
			Log.d("myLogs", "rawDate is : " + rawDate);
			Pattern p = Pattern.compile("(\\d{4})(\\d{2})(\\d{2})");
			Matcher m = p.matcher(rawDate);
			if (m.find()) {
				rawDate = m.replaceFirst("$1-$2-$3");
			}
			setDate(rawDate);
			Log.d("myLogs", "Date for new note was set to : " + this.date);

			String rawTime = fullTimeStamp.split("\\D")[1];
			Log.d("myLogs", "rawTime is : " + rawTime);
			Pattern p2 = Pattern.compile("(\\d{2})(\\d{2})(\\d{2})");
			Matcher m2 = p2.matcher(rawTime);
			if (m2.find()) {
				rawTime = m2.replaceFirst("$1:$2:$3");
			}
			setTime(rawTime);
			Log.d("myLogs", "Time for new note was set to : " + this.time);
			setRating((int) (Math.random() * 5f) + 1);
			Log.d("myLogs", "Rating for new note was set to : " + this.rating);
		}

		public Note(String name, String content) {
			this();
			setName(name);
			Log.d("myLogs", "Name for new note was set to : " + this.name);
			setContent(content);
			Log.d("myLogs", "Content for new note was set to : " + this.content);
		}

		public String getTime() {
			return time;
		}

		public void setTime(String time) {
			notifyListeners(this, TIME, this.time, this.time = time);
		}

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			notifyListeners(this, DATE, this.date, this.date = date);
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			notifyListeners(this, NAME, this.name, this.name = name);
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			notifyListeners(this, CONTENT, this.content, this.content = content);
		}

		@Override
		public String toString() {
			return content;
		}

		public int getRating() {
			return rating;
		}

		public void setRating(int rating) {
			notifyListeners(this, RATING, this.rating, this.rating = rating);
		}
	}

	public static List<Note> getNotesList() {
		return notes;
	}

	private void notifyListeners(Object object, int property, int oldValue,
			int newValue) {
		for (PropertyChangeListener name : listener) {
			name.propertyChange(new PropertyChangeEvent(this, "rating",
					oldValue, newValue));
		}
	}

	private void notifyListeners(Object object, String property,
			String oldValue, String newValue) {
		for (PropertyChangeListener name : listener) {
			name.propertyChange(new PropertyChangeEvent(this, "name", oldValue,
					newValue));
			name.propertyChange(new PropertyChangeEvent(this, "content",
					oldValue, newValue));
			name.propertyChange(new PropertyChangeEvent(this, "time", oldValue,
					newValue));
			name.propertyChange(new PropertyChangeEvent(this, "date", oldValue,
					newValue));
		}
	}

	public void addChangeListener(PropertyChangeListener newListener) {
		listener.add(newListener);
	}

	private ListOfNotes() {
		// Just for testing we hard-code list items here:
		notes.add(new Note("���� 1", "��������."));
		notes.add(new Note("���� 2",
				"��������� � ��������� ����� ���������� Eclipse � SDK Tools"));
		notes.add(new Note("���� 3",
				"�������� AVD. ������ ����������. ��������� Android-�������."));
		notes.add(new Note("���� 4", "�������� ������ � �� ��������"));
		notes.add(new Note("���� 5",
				"Layout-���� � Activity. XML �������������. ����� ���������� ������."));
		notes.add(new Note("���� 6",
				"���� Layouts. �������� ������� � ��������."));
		notes.add(new Note("���� 7", "Layout ��������� ��� View-���������."));
		notes.add(new Note("���� 8", "�������� � ���������� ������ �� ����"));
		notes.add(new Note("���� 9", "����������� ������� �� ������� Button."));
		notes.add(new Note("���� 10", "������������ ���������� ������������."));
		notes.add(new Note("���� 11",
				"����� res/values. ���������� ������� ����������."));
		notes.add(new Note("���� 12", "���� � ����������� ���������"));
		notes.add(new Note("���� 13", "�������� �������� ����"));
		notes.add(new Note("���� 14",
				"����, ������, �������. MenuInflater � xml-����."));
		notes.add(new Note("���� 15", "����������� ����"));
		notes.add(new Note("���� 16",
				"����������� �������� ������. LayoutParams"));
		notes.add(new Note("���� 17",
				"�������� View-��������� � ������� ����������"));
		notes.add(new Note("���� 18",
				"������ layoutParams � ������� ����������"));
		notes.add(new Note("���� 19", "����� ������� �����������"));
		notes.add(new Note("���� 20", "��������"));
		notes.add(new Note("���� 21", "�������� � ����� Activity"));
		notes.add(new Note("���� 22", "Intent, Intent Filter, Context - ������"));
		notes.add(new Note("���� 23",
				"Activity Lifecycle. � ����� ���������� ����� ���� Activity"));
		notes.add(new Note("���� 24",
				"Activity Lifecycle, ������ ����� ��������� � ����� Activity"));
		notes.add(new Note("���� 25", "Task. ��� ��� ����� � ��� �����������"));
		notes.add(new Note("���� 26", "Intent Filter - ��������"));
		notes.add(new Note("���� 27", "������ action �� Intent"));
		notes.add(new Note("���� 28",
				"Extras - �������� ������ � ������� Intent"));
		notes.add(new Note("���� 29",
				"�������� Activity � �������� ���������. ����� startActivityForResult "));
		notes.add(new Note("���� 30",
				"��������� ��� onActivityResult. ����� ����� requestCode � resultCode"));
		notes.add(new Note(
				"���� 31",
				"����� � Intent ���� ������� data. ��� ����� Uri. �������� ��������� ����������"));
		notes.add(new Note("���� 32", "����� ������� �������"));
		notes.add(new Note("���� 33", "�������� ������. Preferences."));
		notes.add(new Note("���� 34", "�������� ������. SQLite"));
		notes.add(new Note("���� 35",
				"SQLite. ������ update � delete � ��������� �������"));
		notes.add(new Note("���� 36",
				"SQLite. ��������� ��� ����� query. �������, ����������, �����������"));
		notes.add(new Note("���� 37",
				"������� �� ��������� ������. INNER JOIN � SQLite. ����� rawQuery."));
		notes.add(new Note("���� 38",
				"���������� � SQLite. ��������� FAQ �� SQLite."));
		notes.add(new Note("���� 39", "onUpgrade. ��������� �� � SQLite"));
		notes.add(new Note("���� 40", "LayoutInflater. ������ ������������."));
		notes.add(new Note("���� 41",
				"���������� LayoutInflater ��� �������� ������"));
		notes.add(new Note("���� 42", "������ - ListView"));
		notes.add(new Note("���� 43",
				"��������� � ������������� ����� � ListView"));
		notes.add(new Note("���� 44", "������� � ListView"));
		notes.add(new Note("���� 45", "������-������ ExpandableListView"));
		notes.add(new Note("���� 46", "������� ExpandableListView"));
		notes.add(new Note("���� 47", "����� ���������"));
		notes.add(new Note("���� 48", "���������� SimpleAdapter."));
		notes.add(new Note("���� 49",
				"SimpleAdapter. ������ SetViewText � SetViewImage"));
		notes.add(new Note("���� 50", "SimpleAdapter. ���������� ViewBinder"));
		notes.add(new Note("���� 51",
				"SimpleAdapter, ���������� � �������� �������"));
		notes.add(new Note("���� 52",
				"SimpleCursorAdapter, ������ �������������"));
		notes.add(new Note("���� 53",
				"SimpleCursorTreeAdapter, ������ �������������"));
		notes.add(new Note("���� 54",
				"������������ ������. ������� ���� �������"));
		notes.add(new Note("���� 55",
				"Header � Footer � �������. HeaderViewListAdapter"));
		notes.add(new Note("���� 56", "Spinner � ���������� ������"));
		notes.add(new Note("���� 57", "GridView � ��� ��������"));
		notes.add(new Note("���� 58", "�������. TimePickerDialog"));
		notes.add(new Note("���� 59", "�������. DatePickerDialog"));
		notes.add(new Note("���� 60",
				"�������. AlertDialog: Title, Message, Icon, Buttons"));
		notes.add(new Note("���� 61",
				"�������. AlertDialog.����� onPrepareDialog"));
		notes.add(new Note("���� 62", "�������. AlertDialog. ������"));
		notes.add(new Note("���� 63",
				"�������. AlertDialog. ������ � ��������� �������"));
		notes.add(new Note("���� 64",
				"�������. AlertDialog. ������ � ������������� �������"));
		notes.add(new Note("���� 65", "�������. AlertDialog. ������������"));
		notes.add(new Note("���� 66", "�������. ����������� � ��������"));
		notes.add(new Note("���� 67", "�������. ProgressDialog"));
		notes.add(new Note("���� 68", "������� � Parcel"));
		notes.add(new Note("���� 69",
				"�������� Parcelable ������� � ������� Intent"));
		notes.add(new Note("���� 70",
				"onSaveInstanceState. ���������� ������ Activity ��� �������� ������"));
		notes.add(new Note("���� 71",
				"Preferences ��� ��������� ����������. PreferenceActivity"));
		notes.add(new Note("���� 72", "Preferences. ������, ������ � ���������"));
		notes.add(new Note("���� 73",
				"Preferences. ��������� ����������� �������� (setEnabled)"));
		notes.add(new Note("���� 74",
				"Preferences. ����������� �������� ������ ��������"));
		notes.add(new Note("���� 75", "�������� ������. ������ � �������."));
		notes.add(new Note("���� 76", "Tab - �������. ����� �����"));
		notes.add(new Note("���� 77",
				"Tab - �������. TabActivity. Activity, ��� ���������� �������"));
		notes.add(new Note("���� 78",
				"Tab - �������. TabContentFactory, ������ �������� ����������� �������"));
		notes.add(new Note("���� 79", "XmlPullParser. ������ XML "));
		notes.add(new Note("���� 80",
				"Handler. ������� ������.  ��������� ������ �������������"));
		notes.add(new Note("���� 81", "Handler. �������� ������� ���������"));
		notes.add(new Note("���� 82",
				"Handler. ������ � ����� ��������������� �����������"));
		notes.add(new Note("���� 83",
				"Handler. ���������� ���������, �������� �� �������, Handler.Callback"));
		notes.add(new Note("���� 84", "Handler. ��������� Runnable"));
		notes.add(new Note("���� 85",
				"��� ��������� �������� ���������� ���� � UI-������"));
		notes.add(new Note("���� 86", "AsyncTask. ����������, ��������� ������"));
		notes.add(new Note("���� 87",
				"AsyncTask. ���������. ������������� ����������"));
		notes.add(new Note("���� 88",
				"AsyncTask. �������� ���������. ����� get"));
		notes.add(new Note("���� 89",
				"AsyncTask. Cancel � �������� ������ � �������� ����������"));
		notes.add(new Note("���� 90", "AsyncTask. Status � ������� ������"));
		notes.add(new Note("���� 91", "AsyncTask. ������� ������"));
		notes.add(new Note("���� 92", "Service. ������� ������"));
		notes.add(new Note("���� 93",
				"Service. �������� ������ � ������. ������ ��������� �������"));
		notes.add(new Note("���� 94", "Service. �������� ��� onStartCommand"));
		notes.add(new Note("���� 95",
				"Service. �������� ����� � ������� PendingIntent"));
		notes.add(new Note("���� 96",
				"Service. �������� ����� � ������� BroadcastReceiver"));
		notes.add(new Note("���� 97", "Service. �������. ServiceConnection"));
		notes.add(new Note("���� 98", "Service. ��������� �������"));
		notes.add(new Note("���� 99", "Service. ����������� - notifications"));
		notes.add(new Note("���� 100",
				"Service. IntentService. Foreground. ������������ �������"));
		notes.add(new Note("���� 101", "������� ���� ContentProvider"));
		notes.add(new Note("���� 102", "Touch � ��������� �������"));
		notes.add(new Note("���� 103",
				"MultiTouch � ��������� ������������� �������"));
		notes.add(new Note("���� 104", "Android 3. Fragments. Lifecycle"));
		notes.add(new Note("���� 105",
				"Android 3. Fragments. ������������ ������"));
		notes.add(new Note("���� 106",
				"Android 3. Fragments. �������������� � Activity"));
		notes.add(new Note("���� 107",
				"Android 3. ActionBar. ���������� ���������"));
		notes.add(new Note("���� 108",
				"Android 3. ActionBar. ��������� - ���� � ���������� ������"));
		notes.add(new Note("���� 109",
				"Android 3. Fragments. ListFragment - ������"));
		notes.add(new Note("���� 110",
				"Android 3. Fragments. DialogFragment - ������"));
		notes.add(new Note("���� 111",
				"Android 3. Fragments. PreferenceFragment - ���������. Headers"));
		notes.add(new Note("���� 112",
				"Android 3. ActionBar. ������������ ���������� ���������"));
		notes.add(new Note("���� 113",
				"Android 3. ActionMode, ��� ������������ ������������ ����"));
		notes.add(new Note(
				"���� 114",
				"Android 3. ���������� Support Library. ����� ����� � ��� �� ������������ �� ������� ����������"));
		notes.add(new Note("���� 115", "���� ���������� �� ������ �������"));
		notes.add(new Note("���� 116",
				"��������� Activity � Task. Intent-�����, launchMode, affinity"));
		notes.add(new Note("���� 117", "�������. ��������. Lifecycle"));
		notes.add(new Note("���� 118",
				"�������. ���������������� �����. ����������"));
		notes.add(new Note("���� 119",
				"PendingIntent � �����, requestCode. AlarmManager"));
		notes.add(new Note("���� 120", "�������. ��������� �������"));
		notes.add(new Note("���� 121", "�������. ������"));
		notes.add(new Note("���� 122",
				"�������. ������, ��������� �������, ����� ����������, ������ ����������"));
		notes.add(new Note("���� 123",
				"��� ��������� ����������. ������� keytool � jarsigner"));
		notes.add(new Note("���� 124", "��� ����� Package ��� ����������"));
		notes.add(new Note("���� 125", "ViewPager"));
		notes.add(new Note("���� 126",
				"�����. MediaPlayer � �����/����� �����, �������� �����������"));
		notes.add(new Note("���� 127", "�����. SoundPool"));
		notes.add(new Note("���� 128", "�����. Audio Focus"));
		notes.add(new Note("���� 129",
				"�����. ������ ����� � ������� MediaRecorder"));
		notes.add(new Note("���� 130",
				"�����. ������ ����� � ������� AudioRecorder"));
		notes.add(new Note("���� 131",
				"������. ���������� ��������� ����������"));
		notes.add(new Note("���� 132",
				"������. ����� ����������� �� �����. ��������� �������� ����������"));
		notes.add(new Note("���� 133", "������. ������ ������ � ����� �����"));
		notes.add(new Note("���� 134", "������. ���������"));
		notes.add(new Note("���� 135", "Loader. LoaderManager. AsyncTaskLoader"));
		notes.add(new Note("���� 136", "CursorLoader"));
		notes.add(new Note("���� 137", "�������. ���������, ����������."));
		notes.add(new Note("���� 138",
				"����������� ��������������. GPS ����������."));
		notes.add(new Note("���� 139",
				"Google maps. �������� � ��������� �������. �����, ������, �������"));
	}

	public static ListOfNotes getInstance() {
		return INSTANCE;
	}
}
