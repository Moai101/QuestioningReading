package com.moai101;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.moai101.data.DiaryContract.DiaryEntry;
/**
 * Created by Ishita Sharma on 7/19/2017.
 */

public class DiaryCursorAdapter extends CursorAdapter {

    public DiaryCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(com.moai101.R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        if (cursor.getCount() == 0) {
            return;
        } else {
            TextView title_notes = (TextView) view.findViewById(R.id.title_list_view);
            TextView date_notes = (TextView) view.findViewById(R.id.date_list_view);


            String title = cursor.getString(cursor.getColumnIndex(DiaryEntry.COLUMN_TITLE));
            String date = cursor.getString(cursor.getColumnIndex(DiaryEntry.COLUMN_DATE));

            title_notes.setText(title);
            date_notes.setText(date);
        }
    }
}