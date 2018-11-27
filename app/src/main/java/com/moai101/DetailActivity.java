package com.moai101;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.LoaderManager;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.moai101.data.DiaryContract.DiaryEntry;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class DetailActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    private static final int RESULT_TITLE_SPEECH = 100;
    private static final int RESULT_DESCRIPTION_TEXT = 101;
    private static EditText title_text_view;
    private static TextView date_text_view;
    private static EditText description_text_view;
    private static EditText description1_text_view;
    private static EditText description2_text_view;
    private static EditText description3_text_view;
    private static EditText description4_text_view;
    private static EditText description5_text_view;
    private static EditText description6_text_view;
    private static EditText description7_text_view;
    private static EditText description8_text_view;
    private static EditText description9_text_view;
    private static EditText description10_text_view;
    private static EditText description11_text_view;
    private static EditText description12_text_view;
    private static EditText description13_text_view;
    private static EditText description14_text_view;
    private static EditText description15_text_view;
    private static EditText description16_text_view;
    private static EditText description17_text_view;
    private static EditText description18_text_view;
    private static EditText description19_text_view;
    private static EditText description20_text_view;
    private static EditText description21_text_view;
    private static Uri CURRENT_DIARY_URI;
    private static final int LOADER_ID= 1;
    private static Button date_range;
    private static Calendar myCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.moai101.R.layout.activity_detail);
        Intent intent =getIntent();
        CURRENT_DIARY_URI=intent.getData();
//
        if(CURRENT_DIARY_URI==null){
//            メニューバーとかの部分
            setTitle("Add note");
            invalidateOptionsMenu();
        }
        else {
            setTitle("Edit note");
            getLoaderManager().initLoader(LOADER_ID,null,this);
        }

        title_text_view=(EditText) findViewById(com.moai101.R.id.title_note);
        date_text_view= (TextView)findViewById(com.moai101.R.id.date_detail_activity);
        description_text_view=(EditText)findViewById(com.moai101.R.id.description_detail_Activity);
        description1_text_view=(EditText)findViewById(com.moai101.R.id.description1_detail_Activity);
        description2_text_view=(EditText)findViewById(com.moai101.R.id.description2_detail_Activity);
        description3_text_view=(EditText)findViewById(com.moai101.R.id.description3_detail_Activity);
        description4_text_view=(EditText)findViewById(com.moai101.R.id.description4_detail_Activity);
        description5_text_view=(EditText)findViewById(com.moai101.R.id.description5_detail_Activity);
        description6_text_view=(EditText)findViewById(com.moai101.R.id.description6_detail_Activity);
        description7_text_view=(EditText)findViewById(com.moai101.R.id.description7_detail_Activity);
        description8_text_view=(EditText)findViewById(com.moai101.R.id.description8_detail_Activity);
        description9_text_view=(EditText)findViewById(com.moai101.R.id.description9_detail_Activity);
        description10_text_view=(EditText)findViewById(com.moai101.R.id.description10_detail_Activity);
        description11_text_view=(EditText)findViewById(com.moai101.R.id.description11_detail_Activity);
        description12_text_view=(EditText)findViewById(com.moai101.R.id.description12_detail_Activity);
        description13_text_view=(EditText)findViewById(com.moai101.R.id.description13_detail_Activity);
        description14_text_view=(EditText)findViewById(com.moai101.R.id.description14_detail_Activity);
        description15_text_view=(EditText)findViewById(com.moai101.R.id.description15_detail_Activity);
        description16_text_view=(EditText)findViewById(com.moai101.R.id.description16_detail_Activity);
        description17_text_view=(EditText)findViewById(com.moai101.R.id.description17_detail_Activity);
        description18_text_view=(EditText)findViewById(com.moai101.R.id.description18_detail_Activity);
        description19_text_view=(EditText)findViewById(com.moai101.R.id.description19_detail_Activity);
        description20_text_view=(EditText)findViewById(com.moai101.R.id.description20_detail_Activity);
        description21_text_view=(EditText)findViewById(com.moai101.R.id.description21_detail_Activity);
        date_range =(Button)findViewById(com.moai101.R.id.date_selector);
        myCalendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        date_range.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(DetailActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        date_range.setOnTouchListener(mTouchListener);
        title_text_view.setOnTouchListener(mTouchListener);
        description_text_view.setOnTouchListener(mTouchListener);
        description1_text_view.setOnTouchListener(mTouchListener);
        description2_text_view.setOnTouchListener(mTouchListener);
        description3_text_view.setOnTouchListener(mTouchListener);
        description4_text_view.setOnTouchListener(mTouchListener);
        description5_text_view.setOnTouchListener(mTouchListener);
        description6_text_view.setOnTouchListener(mTouchListener);
        description7_text_view.setOnTouchListener(mTouchListener);
        description8_text_view.setOnTouchListener(mTouchListener);
        description9_text_view.setOnTouchListener(mTouchListener);
        description10_text_view.setOnTouchListener(mTouchListener);
        description11_text_view.setOnTouchListener(mTouchListener);
        description12_text_view.setOnTouchListener(mTouchListener);
        description13_text_view.setOnTouchListener(mTouchListener);
        description14_text_view.setOnTouchListener(mTouchListener);
        description15_text_view.setOnTouchListener(mTouchListener);
        description16_text_view.setOnTouchListener(mTouchListener);
        description17_text_view.setOnTouchListener(mTouchListener);
        description18_text_view.setOnTouchListener(mTouchListener);
        description19_text_view.setOnTouchListener(mTouchListener);
        description20_text_view.setOnTouchListener(mTouchListener);
        description21_text_view.setOnTouchListener(mTouchListener);
    }
    //どの音声入力かを判別し、テキストを入力するまで
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode){
            case RESULT_TITLE_SPEECH:
                if(resultCode==RESULT_OK&& data!=null){
                    ArrayList<String> text = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    title_text_view.setText(text.get(0));
                }
                break;
            case RESULT_DESCRIPTION_TEXT:
                if(resultCode==RESULT_OK && data!=null){
                    ArrayList<String> text= data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    description_text_view.setText(text.get(0));
                }
                break;
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        // If this is a new diary, hide the "Delete" menu item.
//        新しく書かれる日記ならdeleteボタンが使えないけど、もし既存の日記ならdeleteボタンを作るよっていう処理
        if (CURRENT_DIARY_URI == null) {
            MenuItem menuItem = menu.findItem(com.moai101.R.id.action_delete);
            menuItem.setVisible(false);
        }
        return true;
    }
    //メニューアイコンを作るメソッド
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(com.moai101.R.menu.add_note,menu);
        return true;
    }
    private boolean has_filled_diary= false;
    private View.OnTouchListener mTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            has_filled_diary = true;
            return false;
        }
    };



    // 何をしているのかがわからない
    private void showUnsavedChangesDialog(
            DialogInterface.OnClickListener discardButtonClickListener) {
        // Create an AlertDialog.Builder and set the message, and click listeners
        // for the positive and negative buttons on the dialog.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Changes may not be saved!");
        builder.setPositiveButton("Discard", discardButtonClickListener);
        builder.setNegativeButton("Keep Editing", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "Keep editing" button, so dismiss the dialog
                // and continue editing the diary.
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        // Create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    //何をしているのかがわからない
    @Override
    public void onBackPressed() {
        // If the diary hasn't changed, continue with handling back button press
        if (!has_filled_diary) {
            super.onBackPressed();
            return;
        }

        // Otherwise if there are unsaved changes, setup a dialog to warn the user.
        // Create a click listener to handle the user confirming that changes should be discarded.

        DialogInterface.OnClickListener discardButtonClickListener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // User clicked "Discard" button, close the current activity.
                        finish();
                    }
                };

        // Show dialog that there are unsaved changes
        showUnsavedChangesDialog(discardButtonClickListener);
    }

    private void showDeleteConfirmationDialog(){
        AlertDialog.Builder builder= new  AlertDialog.Builder(this);
        builder.setMessage("Delete this diary?");
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteDiary();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(dialog!=null){
                    dialog.dismiss();
                }
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case com.moai101.R.id.action_delete:
                showDeleteConfirmationDialog();
                return true;
            case com.moai101.R.id.action_save:
                if(CURRENT_DIARY_URI==null)
                    saveDiary(DiaryEntry.CONTENT_URI);
                else
                    saveDiary(CURRENT_DIARY_URI);
                return true;
            case android.R.id.home:
                finish();
                return true;
            default:
                return  super.onOptionsItemSelected(item);
        }
    }


    //入力されたデータをinsertし保存するまでの処理
    private void saveDiary(Uri saveUri) {
        ContentValues values = new ContentValues();

        String title= title_text_view.getText().toString();
        String date = date_text_view.getText().toString();
        String description = description_text_view.getText().toString();
        String description1 = description1_text_view.getText().toString();
        String description2 = description2_text_view.getText().toString();
        String description3 = description3_text_view.getText().toString();
        String description4 = description4_text_view.getText().toString();
        String description5 = description5_text_view.getText().toString();
        String description6 = description6_text_view.getText().toString();
        String description7 = description7_text_view.getText().toString();
        String description8 = description8_text_view.getText().toString();
        String description9 = description9_text_view.getText().toString();
        String description10 = description10_text_view.getText().toString();
        String description11 = description11_text_view.getText().toString();
        String description12 = description12_text_view.getText().toString();
        String description13 = description13_text_view.getText().toString();
        String description14 = description14_text_view.getText().toString();
        String description15 = description15_text_view.getText().toString();
        String description16 = description16_text_view.getText().toString();
        String description17 = description17_text_view.getText().toString();
        String description18 = description18_text_view.getText().toString();
        String description19 = description19_text_view.getText().toString();
        String description20 = description20_text_view.getText().toString();
        String description21 = description21_text_view.getText().toString();


        values.put(DiaryEntry.COLUMN_TITLE,title);
        values.put(DiaryEntry.COLUMN_DATE,date);
        values.put(DiaryEntry.COLUMN_DESCRIPTION,description);
        values.put(DiaryEntry.COLUMN_DESCRIPTION1,description1);
        values.put(DiaryEntry.COLUMN_DESCRIPTION2,description2);
        values.put(DiaryEntry.COLUMN_DESCRIPTION3,description3);
        values.put(DiaryEntry.COLUMN_DESCRIPTION4,description4);
        values.put(DiaryEntry.COLUMN_DESCRIPTION5,description5);
        values.put(DiaryEntry.COLUMN_DESCRIPTION6,description6);
        values.put(DiaryEntry.COLUMN_DESCRIPTION7,description7);
        values.put(DiaryEntry.COLUMN_DESCRIPTION8,description8);
        values.put(DiaryEntry.COLUMN_DESCRIPTION9,description9);
        values.put(DiaryEntry.COLUMN_DESCRIPTION10,description10);
        values.put(DiaryEntry.COLUMN_DESCRIPTION11,description11);
        values.put(DiaryEntry.COLUMN_DESCRIPTION12,description12);
        values.put(DiaryEntry.COLUMN_DESCRIPTION13,description13);
        values.put(DiaryEntry.COLUMN_DESCRIPTION14,description14);
        values.put(DiaryEntry.COLUMN_DESCRIPTION15,description15);
        values.put(DiaryEntry.COLUMN_DESCRIPTION16,description16);
        values.put(DiaryEntry.COLUMN_DESCRIPTION17,description17);
        values.put(DiaryEntry.COLUMN_DESCRIPTION18,description18);
        values.put(DiaryEntry.COLUMN_DESCRIPTION19,description19);
        values.put(DiaryEntry.COLUMN_DESCRIPTION20,description20);
        values.put(DiaryEntry.COLUMN_DESCRIPTION21,description21);

        Toast toast;
        String message;

        if(saveUri.equals(DiaryEntry.CONTENT_URI)){
            Uri uri =getContentResolver().insert(DiaryEntry.CONTENT_URI,values);
            if(uri!=null)
                message="Note saved!";
            else
                message="Error saving note";
        }
        else{

            int rows= getContentResolver().update(CURRENT_DIARY_URI,values,null,null);
            if(rows!=0)
                message="Note updated!";
            else
                message="Error updating note";
        }
        toast= Toast.makeText(this,message,Toast.LENGTH_SHORT);
        toast.show();
        MainActivity.has_diary=true;
        finish();
    }

    private void deleteDiary() {

        if(CURRENT_DIARY_URI!=null){
            int rows= getContentResolver().delete(CURRENT_DIARY_URI,null,null);
            Toast toast;
            String message;
//            デリートされる情報があるならばという意味
            if(rows!=0){
                message="Note deleted";
            }
            else
                message="Error deleting note";
            toast= Toast.makeText(this,message,Toast.LENGTH_SHORT);
            toast.show();
            finish();
        }
    }
    //編集画面にした時に前に書いた内容を表示するために処理
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection= {DiaryEntry._ID,
                DiaryEntry.COLUMN_TITLE,
                DiaryEntry.COLUMN_DATE,
                DiaryEntry.COLUMN_DESCRIPTION,
                DiaryEntry.COLUMN_DESCRIPTION1,
                DiaryEntry.COLUMN_DESCRIPTION2,
                DiaryEntry.COLUMN_DESCRIPTION3,
                DiaryEntry.COLUMN_DESCRIPTION4,
                DiaryEntry.COLUMN_DESCRIPTION5,
                DiaryEntry.COLUMN_DESCRIPTION6,
                DiaryEntry.COLUMN_DESCRIPTION7,
                DiaryEntry.COLUMN_DESCRIPTION8,
                DiaryEntry.COLUMN_DESCRIPTION9,
                DiaryEntry.COLUMN_DESCRIPTION10,
                DiaryEntry.COLUMN_DESCRIPTION11,
                DiaryEntry.COLUMN_DESCRIPTION12,
                DiaryEntry.COLUMN_DESCRIPTION13,
                DiaryEntry.COLUMN_DESCRIPTION14,
                DiaryEntry.COLUMN_DESCRIPTION15,
                DiaryEntry.COLUMN_DESCRIPTION16,
                DiaryEntry.COLUMN_DESCRIPTION17,
                DiaryEntry.COLUMN_DESCRIPTION18,
                DiaryEntry.COLUMN_DESCRIPTION19,
                DiaryEntry.COLUMN_DESCRIPTION20,
                DiaryEntry.COLUMN_DESCRIPTION21



        };
        return new CursorLoader(this,CURRENT_DIARY_URI,projection,null,null,null);
    }
    //使ったローダーを終わらせる処理
    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        update(data);
    }

    private void update(Cursor data) {
        if(data.getCount()==0)
            return;
        else {
            data.moveToFirst();
            String title = data.getString(data.getColumnIndex(DiaryEntry.COLUMN_TITLE));
            String date = data.getString(data.getColumnIndex(DiaryEntry.COLUMN_DATE));


            String description = data.getString(data.getColumnIndex(DiaryEntry.COLUMN_DESCRIPTION));
            String description1 = data.getString(data.getColumnIndex(DiaryEntry.COLUMN_DESCRIPTION1));
            String description2 = data.getString(data.getColumnIndex(DiaryEntry.COLUMN_DESCRIPTION2));
            String description3 = data.getString(data.getColumnIndex(DiaryEntry.COLUMN_DESCRIPTION3));
            String description4 = data.getString(data.getColumnIndex(DiaryEntry.COLUMN_DESCRIPTION4));
            String description5 = data.getString(data.getColumnIndex(DiaryEntry.COLUMN_DESCRIPTION5));
            String description6 = data.getString(data.getColumnIndex(DiaryEntry.COLUMN_DESCRIPTION6));
            String description7 = data.getString(data.getColumnIndex(DiaryEntry.COLUMN_DESCRIPTION7));
            String description8 = data.getString(data.getColumnIndex(DiaryEntry.COLUMN_DESCRIPTION8));
            String description9 = data.getString(data.getColumnIndex(DiaryEntry.COLUMN_DESCRIPTION9));
            String description10 = data.getString(data.getColumnIndex(DiaryEntry.COLUMN_DESCRIPTION10));
            String description11 = data.getString(data.getColumnIndex(DiaryEntry.COLUMN_DESCRIPTION11));
            String description12 = data.getString(data.getColumnIndex(DiaryEntry.COLUMN_DESCRIPTION12));
            String description13 = data.getString(data.getColumnIndex(DiaryEntry.COLUMN_DESCRIPTION13));
            String description14 = data.getString(data.getColumnIndex(DiaryEntry.COLUMN_DESCRIPTION14));
            String description15 = data.getString(data.getColumnIndex(DiaryEntry.COLUMN_DESCRIPTION15));
            String description16 = data.getString(data.getColumnIndex(DiaryEntry.COLUMN_DESCRIPTION16));
            String description17 = data.getString(data.getColumnIndex(DiaryEntry.COLUMN_DESCRIPTION17));
            String description18 = data.getString(data.getColumnIndex(DiaryEntry.COLUMN_DESCRIPTION18));
            String description19 = data.getString(data.getColumnIndex(DiaryEntry.COLUMN_DESCRIPTION19));
            String description20 = data.getString(data.getColumnIndex(DiaryEntry.COLUMN_DESCRIPTION20));
            String description21 = data.getString(data.getColumnIndex(DiaryEntry.COLUMN_DESCRIPTION21));


            title_text_view.setText(title);
            date_text_view.setText(date);
            description_text_view.setText(description);
            description1_text_view.setText(description1);
            description2_text_view.setText(description2);
            description3_text_view.setText(description3);
            description4_text_view.setText(description4);
            description5_text_view.setText(description5);
            description6_text_view.setText(description6);
            description7_text_view.setText(description7);
            description8_text_view.setText(description8);
            description9_text_view.setText(description9);
            description10_text_view.setText(description10);
            description11_text_view.setText(description11);
            description12_text_view.setText(description12);
            description13_text_view.setText(description13);
            description14_text_view.setText(description14);
            description15_text_view.setText(description15);
            description16_text_view.setText(description16);
            description17_text_view.setText(description17);
            description18_text_view.setText(description18);
            description19_text_view.setText(description19);
            description20_text_view.setText(description20);
            description21_text_view.setText(description21);
        }

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    private void updateLabel() {

        String myFormat = "MMM dd yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat);

        date_text_view.setText(sdf.format(myCalendar.getTime()));
    }

}
