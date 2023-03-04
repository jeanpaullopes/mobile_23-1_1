package br.com.uniritter.app1_2023_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(view -> {
            Toast.makeText(this, "Oieeee!", Toast.LENGTH_LONG).show();
        });
        Button btn1_2 = findViewById(R.id.button1_2);
        //btn1_2.setOnClickListener(this);
        btn1_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Activity2.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onClick(View view) {
       if (view.getId() == R.id.button1_2)  {

       }
    }
}