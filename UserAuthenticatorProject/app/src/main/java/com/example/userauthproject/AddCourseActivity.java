package com.example.userauthproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddCourseActivity extends AppCompatActivity {

    private TextInputEditText cName, cPrice, cSuitedFor, cImg, cLink, cDescp ;
    private Button addCourseBtn;
    private ProgressBar loadingPB;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String CourseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        cName = findViewById(R.id.idEditCourseName);
        cPrice = findViewById(R.id.idEditCoursePrice);
        cSuitedFor = findViewById(R.id.idEditCourseSuitedFor);
        cImg = findViewById(R.id.idEditCourseImageLink);
        cDescp = findViewById(R.id.idEditCourseDescription);
        addCourseBtn = findViewById(R.id.idBtnAddCourse);
        loadingPB = findViewById(R.id.idPBLoading);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Courses");



        addCourseBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                loadingPB.setVisibility(View.VISIBLE);
                String courseName = cName.getText().toString();
                String coursePrice = cPrice.getText().toString();
                String courseSuitFor = cSuitedFor.getText().toString();
                String courseImage = cImg.getText().toString();
                String courseLink = cLink.getText().toString();
                String courseDescription = cDescp.getText().toString();

                CourseId = courseName;
                CourseRVmodel courseRVmodel = new CourseRVmodel(courseName,courseDescription,coursePrice,courseSuitFor, courseImage, courseLink, CourseId);

                /*CourseRVmodel courseRVmodel = new CourseRVmodel(); //i have not added the parameters to CourseRVmodel() coz it was showing me Error.
                    else without error it should be like

                CourseRVmodel courseRVmodel = new CourseRVmodel(courseName,courseDescription,coursePrice,courseSuitFor, courseImage, CourseId);
                */

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        loadingPB.setVisibility(View.GONE);
                        databaseReference.child(CourseId).setValue(courseRVmodel);
                        Toast.makeText(AddCourseActivity.this, "Course Added Successfully!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AddCourseActivity.this, MainActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(AddCourseActivity.this, "Error is: "+ error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });



            }
        });
    }
}