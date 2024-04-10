package com.example.loadss;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText load;
    EditText binID;
    EditText goal;
    Button saveButton;
    DatabaseReference databaseReference;
//    private SeekBar bin1SeekBar;
    SeekBar bin1SeekBar = findViewById(R.id.bin1SeekBar);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        load = findViewById(R.id.load);
        binID = findViewById(R.id.binID);
        goal = findViewById(R.id.goal);
        saveButton = findViewById(R.id.pushToFirebase);


        databaseReference = FirebaseDatabase.getInstance().getReference();


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input1 = load.getText().toString();
                String input2 = binID.getText().toString();
                String input3 = goal.getText().toString();

                try {
                    int loadValue = Integer.parseInt(input1);
                    int goalValue = Integer.parseInt(input3);

                    double percentage = (double) loadValue / goalValue;

                    Map<String, Object> dataMap = new HashMap<>();
                    dataMap.put("weight", input1);
                    dataMap.put("percentage", percentage);

//                    databaseReference.child("0001").child("editText2").setValue(input2);
                    databaseReference.child(input2).updateChildren(dataMap);

                    Toast.makeText(MainActivity.this, "Data uploaded to Firebase", Toast.LENGTH_SHORT).show();
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Invalid input. Please enter valid numbers", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}