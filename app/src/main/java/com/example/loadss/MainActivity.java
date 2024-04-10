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
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText load = findViewById(R.id.load);
    EditText binID = findViewById(R.id.binID);
    EditText goal = findViewById(R.id.goal);
    Button saveButton;

    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        databaseReference = FirebaseDatabase.getInstance().getReference();
        saveButton = findViewById(R.id.pushToFirebase);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input1 = load.getText().toString();
                String input2 = binID.getText().toString();
                String input3 = goal.getText().toString();
                int newVariable = Integer.parseInt(input1) / Integer.parseInt(input3);

                Map<String, Object> dataMap = new HashMap<>();
                dataMap.put("editText1", input1);
                dataMap.put("newVariable", newVariable);

                // Push data to Firebase
                databaseReference.child("root").child("editText2").setValue(input2);
                databaseReference.child("root").updateChildren(dataMap);

                Toast.makeText(MainActivity.this, "Data uploaded to Firebase", Toast.LENGTH_SHORT).show();

                // Do something with the input values, e.g., store them or display them
                String message = "Sent to firebase";
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}