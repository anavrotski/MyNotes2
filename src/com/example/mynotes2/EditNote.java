package com.example.mynotes2;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.observer.pattern.ListOfNotes;
import com.observer.pattern.ListOfNotes.Note;

public class EditNote extends Activity implements PropertyChangeListener {

	private int year;
	private int month;
	private int day;
	private int hour;
	private int minutes;

	static final int DATE_PICKER_ID = 1111;
	static final int TIME_PICKER_ID = 2222;

	private Note item;
	private Button dateButton;
	private Button timeButton;
	private Button saveButton;
	private EditText content;
	private List<ListOfNotes.Note> items;
	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit);

		final Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);

		items = ListOfNotes.getNotesList();
		intent = getIntent();
		item = items.get(intent.getIntExtra("itemPosition", items.size() - 1));
		content = (EditText) findViewById(R.id.editText1);
		content.append(item.getContent());
		TextView name = (TextView) findViewById(R.id.textView1);
		name.setText(item.getName());

		saveButton = (Button) findViewById(R.id.buttonSave);
		saveButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// items = ListOfNotes.getNotesList();
				item.setContent(content.getText().toString());
				setResult(RESULT_OK, intent);
			}
		});

		dateButton = (Button) findViewById(R.id.buttonDate);
		dateButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showDialog(DATE_PICKER_ID);
			}

		});

		timeButton = (Button) findViewById(R.id.buttonTime);
		timeButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showDialog(TIME_PICKER_ID);
			}

		});

	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_PICKER_ID:
			return new DatePickerDialog(this, datePickerListener, year, month,
					day);

		case TIME_PICKER_ID:
			return new TimePickerDialog(this, timePickerListener, hour,
					minutes, true);
		}
		return null;
	}

	private final DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int selectedYear,
				int selectedMonth, int selectedDay) {

			year = selectedYear;
			month = selectedMonth;
			day = selectedDay;
		}
	};

	private final TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {

		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

			hour = hourOfDay;
			minutes = minute;
		}
	};

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		// TODO Auto-generated method stub

	}
}
