package com.example.practicalfive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MUNICIPALITY = "municipality_name";
    private EditText inputText;
    private TextProcessor processor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputText = findViewById(R.id.municipalityInput);
        processor = new TextProcessor();

        inputText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_GO) {
                checkInput();
                return true;
            }
            return false;
        });
    }

    private void checkInput() {
        String userInput = inputText.getText().toString();
        String result = processor.processMunicipality(userInput);

        if (result == null) {
            if (userInput.trim().isEmpty()) {
                inputText.setError("Please enter a municipality name");
            } else {
                inputText.setError("Not a valid municipality, try Beijing, Shanghai, Tianjin or Chongqing");
            }
        } else {
            Intent intent = new Intent(MainActivity.this, Municipality.class);
            intent.putExtra(EXTRA_MUNICIPALITY, result);
            startActivity(intent);
        }
    }
}
