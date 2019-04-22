package coleo.com.sample;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class HistoryActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_word);

        textView = findViewById(R.id.history_words);

        final Button search = findViewById(R.id.search_button);
        final EditText input = findViewById(R.id.input_word);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word = input.getText().toString();
                saveWord(word);
                input.setText("");
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        String[] words = loadWord();
        if (words != null)
            textView.setText(words[words.length - 1]);
    }

    private void saveWord(String word) {
        Context context = this;
        String name = "savedWord";
        SharedPreferences sharedPreferences = context.getSharedPreferences(name, MODE_PRIVATE);
        String lastWords = sharedPreferences.getString("words", null);

        String newWords;
        if (lastWords == null) {
            newWords = word;
        } else {
            newWords = lastWords + "," + word;
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("words", newWords);

        editor.apply();
    }

    private String[] loadWord() {
        Context context = this;
        String name = "savedWord";
        SharedPreferences sharedPreferences = context.getSharedPreferences(name, MODE_PRIVATE);
        String lastWords = sharedPreferences.getString("words", null);

        if (lastWords != null) {
            return lastWords.split(",");
        }
        return null;
    }

}
