package com.example.chatgptapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chatgptapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements OnTaskCompleted {

    private ActivityMainBinding binding;
    DataManager dataManager = new DataManager(this);

    // Declare UI elements
    private EditText promptEditText;
    private Button sendButton;
    private TextView responseTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize UI elements
        promptEditText = binding.promptEditText;
        sendButton = binding.sendButton;
        responseTextView = binding.responseTextView;

        // Set OnClickListener for the Send button
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Logic for sending message to GPT
                String userInput = promptEditText.getText().toString();
                new CallOpenAITask(MainActivity.this).execute(userInput);
            }
        });

        // The cancel button logic is removed as the button is not present in the updated layout

        // If you reintroduce the saveAuditButton, update its logic here

    }

    @Override
    public void onTaskCompleted(String result) {
        String existingText = responseTextView.getText().toString();
        String newText = existingText + "\n\n" + result;
        responseTextView.setText(newText);
    }

}
