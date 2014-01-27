package com.example.mynotes2;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.observer.pattern.ListOfNotes;
import com.observer.pattern.ListOfNotes.Note;

public class MainActivity extends Activity implements PropertyChangeListener {
	public static boolean deleteMode;
	ListOfNotes list;
	List<ListOfNotes.Note> items;
	ListView lvMain;
	CustomizedListViewAdapter myAdapter;
	Button addNewNoteButton;
	Button deleteNoteButton;

	@Override
	protected void onResume() {
		super.onResume();
		lvMain.setAdapter(myAdapter);
		Log.d("myLogs", "List adapter re-setted.");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.d("myLogs", "Content view for main activity setted.");

		list = ListOfNotes.getInstance();
		list.addChangeListener(this);
		Log.d("myLogs", "ChangeListener to new List of Notes added.");

		items = ListOfNotes.getNotesList();
		Log.d("myLogs", "List of Notes aquired.");

		lvMain = (ListView) findViewById(R.id.listView1);
		myAdapter = new CustomizedListViewAdapter(this, (Serializable) items);
		Log.d("myLogs", "New customized list adapter created.");

		lvMain.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		Log.d("myLogs", "Multiply choice mode for list view enabled.");

		addNewNoteButton = (Button) findViewById(R.id.button1);
		Log.d("myLogs", "Button for adding new notes added.");

		addNewNoteButton.setOnClickListener(addNoteListener);
		Log.d("myLogs", "OnClickListener for add button setted.");

		deleteNoteButton = (Button) findViewById(R.id.button2);
		deleteNoteButton.setOnClickListener(deleteListener);

		lvMain.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(getApplicationContext(),
						items.get(position).getContent(), Toast.LENGTH_LONG)
						.show();
				Log.d("myLogs", "Click on note : "
						+ items.get(position).getName() + " processed.");
			}
		});

		lvMain.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {

				Intent intent = new Intent(getApplicationContext(),
						EditNote.class);
				intent.putExtra("itemPosition", position);
				startActivityForResult(intent, 0);
				Log.d("myLogs", "Long click on note : "
						+ items.get(position).getName() + " processed.");
				return true;
			}
		});

	}

	public void removeSelectedItems() {

		// SparseBooleanArray checkedItemPositions = lvMain
		// .getCheckedItemPositions();
		//
		// if (checkedItemPositions != null) {
		// for (int i = 0; i < checkedItemPositions.size(); i++) {
		// if (checkedItemPositions.valueAt(i))
		// items.remove(checkedItemPositions.keyAt(i));
		// }
		// }
		List<Note> itemsToRemove = new ArrayList<ListOfNotes.Note>();

		for (int i = 0; i < lvMain.getCount(); i++) {

			CheckBox cBox = (CheckBox) lvMain.getChildAt(i).findViewById(
					R.id.checkToDel);

			if (cBox != null) {
				if (cBox.isChecked()) {
					itemsToRemove.add(items.get(i));
				}
			}
		}

		Log.d("myLogs", "New list of notes to delete created. Its size : "
				+ itemsToRemove.size());
		items.removeAll(itemsToRemove);
		Log.d("myLogs", "Checked items succesfully removed.");
		myAdapter.notifyDataSetChanged();
		Log.d("myLogs", "Adapter notified about chages.");
		lvMain.clearChoices();
		Log.d("myLogs", "Choice cleared.");
		lvMain.setAdapter(lvMain.getAdapter());
		Log.d("myLogs", "List adapter re-setted.");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	OnClickListener deleteListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			deleteMode = !deleteMode;
			if (deleteMode) {
				deleteNoteButton.setText(R.string.delete);
			} else {
				deleteNoteButton.setText(R.string.delete_note);
			}
			myAdapter.notifyDataSetChanged();
			deleteNoteButton.setOnClickListener(checkToDeleteListener);
		}
	};

	OnClickListener checkToDeleteListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			deleteMode = !deleteMode;
			if (deleteMode) {
				deleteNoteButton.setText(R.string.delete);
			} else {
				deleteNoteButton.setText(R.string.delete_note);
			}
			removeSelectedItems();
			myAdapter.notifyDataSetChanged();
			deleteNoteButton.setOnClickListener(deleteListener);
		}
	};

	OnClickListener addNoteListener = new OnClickListener() {

		@Override
		public void onClick(View v) {

			// Set an EditText view to get user input
			final EditText input = new EditText(MainActivity.this);
			String message = "Input name of new note";
			new AlertDialog.Builder(MainActivity.this)
					.setTitle("Input name")
					.setMessage(message)
					.setView(input)
					.setPositiveButton("Ok",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int whichButton) {
									String newName = input.getText().toString();
									ListOfNotes.Note newNote = list.new Note(
											newName, "");
									items.add(newNote);

									Intent intent = new Intent(
											getApplicationContext(),
											EditNote.class);
									startActivityForResult(intent, 0);
								}
							})
					.setNegativeButton("Cancel",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int whichButton) {
									// Do nothing.
								}
							}).show();
		}
	};

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (resultCode != RESULT_OK) {
			Log.d("myLogs", "requestCode = " + requestCode + ", resultCode = "
					+ resultCode);
		}
	}
}