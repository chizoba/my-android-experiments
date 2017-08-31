package com.github.chizoba.wordprocessor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    // Declare variables (views)
    EditText documentEditText;
    TextView wordCountTextView;

    // Variable that holds the entire text in a document
    String totalText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize variables (views)
        documentEditText = (EditText) findViewById(R.id.editText);
        wordCountTextView = (TextView) findViewById(R.id.textView);

        // Set initial word count to zero (0)
        wordCountTextView.setText(getResources().getString(R.string.no_of_words, String.valueOf(0)));

        documentEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                totalText = documentEditText.getText().toString();
                int numOfWords = getNumOfWords("[A-Za-z]+");
                wordCountTextView.setText(getResources().getString(R.string.no_of_words, String.valueOf(numOfWords)));
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    private int getNumOfWords(String regex) {
        ArrayList<String> listOfWords = new ArrayList<>();
        Pattern splitter = Pattern.compile(regex);
        Matcher m = splitter.matcher(totalText);

        while (m.find()) {
            listOfWords.add(m.group());
        }

        return listOfWords.size();
    }
}
