package com.itparkbynipun.sahredpreferences;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button recordBtn;
    EditText editText1;
    EditText editText2;

    SharedPreferences sharedPreferences;
    String distance, time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recordBtn = findViewById(R.id.rec_btn);
        textView = findViewById(R.id.tv_1);
        editText1 = findViewById(R.id.et_1);
        editText2 = findViewById(R.id.et_2);

        sharedPreferences = getApplicationContext()
                .getSharedPreferences("myPref", MODE_PRIVATE);

        final SharedPreferences.Editor editor = sharedPreferences.edit();

        if (sharedPreferences.getString("distance", null) == null ||
                sharedPreferences.getString("time", null) == null) {  //if values are null
            editor.putString("distance", "u r lazy");
            editor.putString("time", "0 mins");

            editor.commit(); //actually saves the above key and values

        }

        distance = sharedPreferences.getString("distance", null);
        time = sharedPreferences.getString("time", null);

        editText1.setText(distance);
        editText2.setText(time);

        recordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (recordBtn.getText().toString().equals("EDIT")) {
                    editText1.setEnabled(true);
                    editText2.setEnabled(true);
                    recordBtn.setText("SAVE");
                } else {
                    distance = editText1.getText().toString();
                    time = editText2.getText().toString();

                    editor.putString("distance", distance);
                    editor.putString("time", time);
                    editor.commit();

                    editText1.setEnabled(false);
                    editText2.setEnabled(false);
                    recordBtn.setText("EDIT");

                }

            }
        });

    }
}
