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
		notes.add(new Note("Урок 1", "Введение."));
		notes.add(new Note("Урок 2",
				"Установка и настройка среды разработки Eclipse и SDK Tools"));
		notes.add(new Note("Урок 3",
				"Создание AVD. Первое приложение. Структура Android-проекта."));
		notes.add(new Note("Урок 4", "Элементы экрана и их свойства"));
		notes.add(new Note("Урок 5",
				"Layout-файл в Activity. XML представление. Смена ориентации экрана."));
		notes.add(new Note("Урок 6",
				"Виды Layouts. Ключевые отличия и свойства."));
		notes.add(new Note("Урок 7", "Layout параметры для View-элементов."));
		notes.add(new Note("Урок 8", "Работаем с элементами экрана из кода"));
		notes.add(new Note("Урок 9", "Обработчики событий на примере Button."));
		notes.add(new Note("Урок 10", "Оптимизируем реализацию обработчиков."));
		notes.add(new Note("Урок 11",
				"Папка res/values. Используем ресурсы приложения."));
		notes.add(new Note("Урок 12", "Логи и всплывающие сообщения"));
		notes.add(new Note("Урок 13", "Создание простого меню"));
		notes.add(new Note("Урок 14",
				"Меню, группы, порядок. MenuInflater и xml-меню."));
		notes.add(new Note("Урок 15", "Контекстное меню"));
		notes.add(new Note("Урок 16",
				"Программное создание экрана. LayoutParams"));
		notes.add(new Note("Урок 17",
				"Создание View-компонент в рабочем приложении"));
		notes.add(new Note("Урок 18",
				"Меняем layoutParams в рабочем приложении"));
		notes.add(new Note("Урок 19", "Пишем простой калькулятор"));
		notes.add(new Note("Урок 20", "Анимация"));
		notes.add(new Note("Урок 21", "Создание и вызов Activity"));
		notes.add(new Note("Урок 22", "Intent, Intent Filter, Context - теория"));
		notes.add(new Note("Урок 23",
				"Activity Lifecycle. В каких состояниях может быть Activity"));
		notes.add(new Note("Урок 24",
				"Activity Lifecycle, пример смены состояний с двумя Activity"));
		notes.add(new Note("Урок 25", "Task. Что это такое и как формируется"));
		notes.add(new Note("Урок 26", "Intent Filter - практика"));
		notes.add(new Note("Урок 27", "Читаем action из Intent"));
		notes.add(new Note("Урок 28",
				"Extras - передаем данные с помощью Intent"));
		notes.add(new Note("Урок 29",
				"Вызываем Activity и получаем результат. Метод startActivityForResult "));
		notes.add(new Note("Урок 30",
				"Подробнее про onActivityResult. Зачем нужны requestCode и resultCode"));
		notes.add(new Note(
				"Урок 31",
				"Зачем у Intent есть атрибут data. Что такое Uri. Вызываем системные приложения"));
		notes.add(new Note("Урок 32", "Пишем простой браузер"));
		notes.add(new Note("Урок 33", "Хранение данных. Preferences."));
		notes.add(new Note("Урок 34", "Хранение данных. SQLite"));
		notes.add(new Note("Урок 35",
				"SQLite. Методы update и delete с указанием условия"));
		notes.add(new Note("Урок 36",
				"SQLite. Подробнее про метод query. Условие, сортировка, группировка"));
		notes.add(new Note("Урок 37",
				"Запросы из связанных таблиц. INNER JOIN в SQLite. Метод rawQuery."));
		notes.add(new Note("Урок 38",
				"Транзакции в SQLite. Небольшой FAQ по SQLite."));
		notes.add(new Note("Урок 39", "onUpgrade. Обновляем БД в SQLite"));
		notes.add(new Note("Урок 40", "LayoutInflater. Учимся использовать."));
		notes.add(new Note("Урок 41",
				"Используем LayoutInflater для создания списка"));
		notes.add(new Note("Урок 42", "Список - ListView"));
		notes.add(new Note("Урок 43",
				"Одиночный и множественный выбор в ListView"));
		notes.add(new Note("Урок 44", "События в ListView"));
		notes.add(new Note("Урок 45", "Список-дерево ExpandableListView"));
		notes.add(new Note("Урок 46", "События ExpandableListView"));
		notes.add(new Note("Урок 47", "Обзор адаптеров"));
		notes.add(new Note("Урок 48", "Используем SimpleAdapter."));
		notes.add(new Note("Урок 49",
				"SimpleAdapter. Методы SetViewText и SetViewImage"));
		notes.add(new Note("Урок 50", "SimpleAdapter. Используем ViewBinder"));
		notes.add(new Note("Урок 51",
				"SimpleAdapter, добавление и удаление записей"));
		notes.add(new Note("Урок 52",
				"SimpleCursorAdapter, пример использования"));
		notes.add(new Note("Урок 53",
				"SimpleCursorTreeAdapter, пример использования"));
		notes.add(new Note("Урок 54",
				"Кастомизация списка. Создаем свой адаптер"));
		notes.add(new Note("Урок 55",
				"Header и Footer в списках. HeaderViewListAdapter"));
		notes.add(new Note("Урок 56", "Spinner – выпадающий список"));
		notes.add(new Note("Урок 57", "GridView и его атрибуты"));
		notes.add(new Note("Урок 58", "Диалоги. TimePickerDialog"));
		notes.add(new Note("Урок 59", "Диалоги. DatePickerDialog"));
		notes.add(new Note("Урок 60",
				"Диалоги. AlertDialog: Title, Message, Icon, Buttons"));
		notes.add(new Note("Урок 61",
				"Диалоги. AlertDialog.Метод onPrepareDialog"));
		notes.add(new Note("Урок 62", "Диалоги. AlertDialog. Список"));
		notes.add(new Note("Урок 63",
				"Диалоги. AlertDialog. Список с одиночным выбором"));
		notes.add(new Note("Урок 64",
				"Диалоги. AlertDialog. Список с множественным выбором"));
		notes.add(new Note("Урок 65", "Диалоги. AlertDialog. Кастомизация"));
		notes.add(new Note("Урок 66", "Диалоги. Обработчики и операции"));
		notes.add(new Note("Урок 67", "Диалоги. ProgressDialog"));
		notes.add(new Note("Урок 68", "Немного о Parcel"));
		notes.add(new Note("Урок 69",
				"Передаем Parcelable объекты с помощью Intent"));
		notes.add(new Note("Урок 70",
				"onSaveInstanceState. Сохранение данных Activity при повороте экрана"));
		notes.add(new Note("Урок 71",
				"Preferences как настройки приложения. PreferenceActivity"));
		notes.add(new Note("Урок 72", "Preferences. Список, экраны и категории"));
		notes.add(new Note("Урок 73",
				"Preferences. Управляем активностью настроек (setEnabled)"));
		notes.add(new Note("Урок 74",
				"Preferences. Программное создание экрана настроек"));
		notes.add(new Note("Урок 75", "Хранение данных. Работа с файлами."));
		notes.add(new Note("Урок 76", "Tab - вкладки. Общий обзор"));
		notes.add(new Note("Урок 77",
				"Tab - вкладки. TabActivity. Activity, как содержимое вкладки"));
		notes.add(new Note("Урок 78",
				"Tab - вкладки. TabContentFactory, ручное создание содержимого вкладки"));
		notes.add(new Note("Урок 79", "XmlPullParser. Парсим XML "));
		notes.add(new Note("Урок 80",
				"Handler. Немного теории.  Наглядный пример использования"));
		notes.add(new Note("Урок 81", "Handler. Посылаем простое сообщение"));
		notes.add(new Note("Урок 82",
				"Handler. Пример с более содержательными сообщениями"));
		notes.add(new Note("Урок 83",
				"Handler. Отложенные сообщения, удаление из очереди, Handler.Callback"));
		notes.add(new Note("Урок 84", "Handler. Обработка Runnable"));
		notes.add(new Note("Урок 85",
				"Еще несколько способов выполнения кода в UI-потоке"));
		notes.add(new Note("Урок 86", "AsyncTask. Знакомство, несложный пример"));
		notes.add(new Note("Урок 87",
				"AsyncTask. Параметры. Промежуточные результаты"));
		notes.add(new Note("Урок 88",
				"AsyncTask. Итоговый результат. Метод get"));
		notes.add(new Note("Урок 89",
				"AsyncTask. Cancel – отменяем задачу в процессе выполнения"));
		notes.add(new Note("Урок 90", "AsyncTask. Status – статусы задачи"));
		notes.add(new Note("Урок 91", "AsyncTask. Поворот экрана"));
		notes.add(new Note("Урок 92", "Service. Простой пример"));
		notes.add(new Note("Урок 93",
				"Service. Передача данных в сервис. Методы остановки сервиса"));
		notes.add(new Note("Урок 94", "Service. Подробно про onStartCommand"));
		notes.add(new Note("Урок 95",
				"Service. Обратная связь с помощью PendingIntent"));
		notes.add(new Note("Урок 96",
				"Service. Обратная связь с помощью BroadcastReceiver"));
		notes.add(new Note("Урок 97", "Service. Биндинг. ServiceConnection"));
		notes.add(new Note("Урок 98", "Service. Локальный биндинг"));
		notes.add(new Note("Урок 99", "Service. Уведомления - notifications"));
		notes.add(new Note("Урок 100",
				"Service. IntentService. Foreground. Автозагрузка сервиса"));
		notes.add(new Note("Урок 101", "Создаем свой ContentProvider"));
		notes.add(new Note("Урок 102", "Touch – обработка касания"));
		notes.add(new Note("Урок 103",
				"MultiTouch – обработка множественных касаний"));
		notes.add(new Note("Урок 104", "Android 3. Fragments. Lifecycle"));
		notes.add(new Note("Урок 105",
				"Android 3. Fragments. Динамическая работа"));
		notes.add(new Note("Урок 106",
				"Android 3. Fragments. Взаимодействие с Activity"));
		notes.add(new Note("Урок 107",
				"Android 3. ActionBar. Размещение элементов"));
		notes.add(new Note("Урок 108",
				"Android 3. ActionBar. Навигация - табы и выпадающий список"));
		notes.add(new Note("Урок 109",
				"Android 3. Fragments. ListFragment - список"));
		notes.add(new Note("Урок 110",
				"Android 3. Fragments. DialogFragment - диалог"));
		notes.add(new Note("Урок 111",
				"Android 3. Fragments. PreferenceFragment - настройки. Headers"));
		notes.add(new Note("Урок 112",
				"Android 3. ActionBar. Динамическое размещение элементов"));
		notes.add(new Note("Урок 113",
				"Android 3. ActionMode, как альтернатива контекстному меню"));
		notes.add(new Note(
				"Урок 114",
				"Android 3. Библиотека Support Library. Зачем нужна и как ее использовать на примере фрагментов"));
		notes.add(new Note("Урок 115", "Одно приложение на разных экранах"));
		notes.add(new Note("Урок 116",
				"Поведение Activity в Task. Intent-флаги, launchMode, affinity"));
		notes.add(new Note("Урок 117", "Виджеты. Создание. Lifecycle"));
		notes.add(new Note("Урок 118",
				"Виджеты. Конфигурационный экран. Обновление"));
		notes.add(new Note("Урок 119",
				"PendingIntent – флаги, requestCode. AlarmManager"));
		notes.add(new Note("Урок 120", "Виджеты. Обработка нажатий"));
		notes.add(new Note("Урок 121", "Виджеты. Список"));
		notes.add(new Note("Урок 122",
				"Виджеты. Превью, изменение размера, экран блокировки, ручное обновление"));
		notes.add(new Note("Урок 123",
				"Как подписать приложение. Утилиты keytool и jarsigner"));
		notes.add(new Note("Урок 124", "Что такое Package для приложения"));
		notes.add(new Note("Урок 125", "ViewPager"));
		notes.add(new Note("Урок 126",
				"Медиа. MediaPlayer – аудио/видео плеер, основные возможности"));
		notes.add(new Note("Урок 127", "Медиа. SoundPool"));
		notes.add(new Note("Урок 128", "Медиа. Audio Focus"));
		notes.add(new Note("Урок 129",
				"Медиа. Запись звука с помощью MediaRecorder"));
		notes.add(new Note("Урок 130",
				"Медиа. Запись звука с помощью AudioRecorder"));
		notes.add(new Note("Урок 131",
				"Камера. Используем системное приложение"));
		notes.add(new Note("Урок 132",
				"Камера. Вывод изображения на экран. Обработка поворота устройства"));
		notes.add(new Note("Урок 133", "Камера. Делаем снимок и пишем видео"));
		notes.add(new Note("Урок 134", "Камера. Настройки"));
		notes.add(new Note("Урок 135", "Loader. LoaderManager. AsyncTaskLoader"));
		notes.add(new Note("Урок 136", "CursorLoader"));
		notes.add(new Note("Урок 137", "Сенсоры. Ускорение, ориентация."));
		notes.add(new Note("Урок 138",
				"Определение местоположения. GPS координаты."));
		notes.add(new Note("Урок 139",
				"Google maps. Создание и настройка проекта. Карта, камера, события"));
	}

	public static ListOfNotes getInstance() {
		return INSTANCE;
	}
}
