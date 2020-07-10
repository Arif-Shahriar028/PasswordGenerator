package com.example.passwordgenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Feedback extends AppCompatActivity implements View.OnClickListener{

    private EditText nameText, messageText;
    private Button sendButton, clearButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setTitle("Feedback");

        nameText = (EditText) findViewById(R.id.nameEditTextId);
        messageText = (EditText) findViewById(R.id.messageEditTextId);
        sendButton = (Button) findViewById(R.id.sendButtonId);
        clearButton = (Button) findViewById(R.id.clearButtonId);

        sendButton.setOnClickListener(this);
        clearButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        try{
            String name = nameText.getText().toString();
            String message = messageText.getText().toString();

            if(v.getId()== R.id.sendButtonId)
            {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/email");

                intent.putExtra(Intent.EXTRA_EMAIL, new String[] {"arifshahriar028@gmail.com"});

                intent.putExtra(Intent.EXTRA_TEXT, "Name : " + name + "\nMessage : "+message);

                startActivity(Intent.createChooser(intent, "Feedback with"));
            }

            else if(v.getId()==R.id.clearButtonId)
            {
                nameText.setText("");
                messageText.setText("");
            }
        } catch (Exception e){

            Toast.makeText(getApplicationContext(), "Exeption : "+e, Toast.LENGTH_SHORT).show();
        }
    }


    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId()== android.R.id.home)
        {
            Intent intent = new Intent(Feedback.this, MainActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}