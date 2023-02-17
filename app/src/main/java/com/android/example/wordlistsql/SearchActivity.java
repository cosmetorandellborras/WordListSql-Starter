package com.android.example.wordlistsql;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SearchActivity extends AppCompatActivity {

    private TextView mTextView;
    private EditText mEditWordView;
    private WordListOpenHelper mDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mTextView = (TextView) findViewById(R.id.search_result);
        mEditWordView = (EditText) findViewById(R.id.edit_search_word);
        mDB = new WordListOpenHelper(this);
    }
    public void showResult(View view){
        String word = mEditWordView.getText().toString();
        mTextView.setText("Result for "+word+":\n\n");

        Cursor cursor = mDB.search(word);

        if(cursor!=null && cursor.getCount()>0){
            cursor.moveToFirst();
            int index;
            String result;
            do{
                index = cursor.getColumnIndex(WordListOpenHelper.KEY_WORD);
                result = cursor.getString(index);
                mTextView.append(result+"\n");

            }while(cursor.moveToNext());
            cursor.close();
        }
    }
}