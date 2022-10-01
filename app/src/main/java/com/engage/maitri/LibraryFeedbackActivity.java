package com.engage.maitri;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class LibraryFeedbackActivity extends AppCompatActivity {
    private RadioGroup radioGroup1,radioGroup2,radioGroup3,radioGroup4,radioGroup5,radioGroup6,radioGroup7;
    private String answer1,answer2,answer3,answer4,answer5,answer6,answer7;

    private EditText valuableSuggestion;
    private RadioButton radioButton;
    private FirebaseFirestore db;
    private Button submitFeedback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library_feedback);
        radioGroup2=findViewById(R.id.radioGroup_bookCollection_relevance);
        radioGroup3=findViewById(R.id.radioGroup_bookAdequacy_Availability);
        radioGroup4=findViewById(R.id.radioGroup_bookCollection_variety);
        radioGroup5=findViewById(R.id.radioGroup_bookAdequacy_Arrangement);
        radioGroup6=findViewById(R.id.radioGroup_e_resources_relevance);
        radioGroup7=findViewById(R.id.radioGroup_staff_helpfulness);
        radioGroup1=findViewById(R.id.radioGroup);
        submitFeedback=findViewById(R.id.submit_feedback);
        valuableSuggestion=findViewById(R.id.valuable_suggestion);
        db=FirebaseFirestore.getInstance();
        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                radioButton=findViewById(i);
                answer1=radioButton.getText().toString();
            }
        });
        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                radioButton=findViewById(i);
                answer2=radioButton.getText().toString();
            }
        });
        radioGroup3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                radioButton=findViewById(i);
                answer3=radioButton.getText().toString();
            }
        });
        radioGroup4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                radioButton=findViewById(i);
                answer4=radioButton.getText().toString();
            }
        });
        radioGroup5.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                radioButton=findViewById(i);
                answer5=radioButton.getText().toString();
            }
        });
        radioGroup6.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                radioButton=findViewById(i);
                answer6=radioButton.getText().toString();
            }
        });
        radioGroup7.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                radioButton=findViewById(i);
                answer7=radioButton.getText().toString();
            }
        });



        submitFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LibraryFeedbackActivity.this,answer1+answer2+answer4+answer5+answer6+answer7,Toast.LENGTH_SHORT).show();
                String valSuggestion=valuableSuggestion.getText().toString();
                Map<String,String> map=new HashMap<>();
                map.put("Frequency of Library Visit",answer1);
                map.put("Book Collection - Relevance",answer2);
                map.put("Book Collection - Adequacy and Availability",answer3);
                map.put("Book Collection - Variety",answer4);
                map.put("Book Collection - Arrangement",answer5);
                map.put("E-Resources - Relevance",answer6);
                map.put("Staff - Helpfulness",answer7);
                map.put("Suggestion",valSuggestion);
                if(answer1!=null && answer2!=null && answer3!=null&& answer4!=null&& answer5!=null&& answer6!=null&& answer7!=null ){
                    db.collection("Feedback").add(map).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            if(task.isSuccessful()){

                                valuableSuggestion.setText("");
                                Toast.makeText(LibraryFeedbackActivity.this,"Your feedback had been recorded",Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(LibraryFeedbackActivity.this,"An error occurred. Please try again",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else{
                    Toast.makeText(LibraryFeedbackActivity.this,"One or options not selected",Toast.LENGTH_SHORT).show();
                }


            }
        });





    }
}