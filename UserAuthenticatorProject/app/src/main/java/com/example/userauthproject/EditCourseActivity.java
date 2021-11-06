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

import java.util.HashMap;
import java.util.Map;

public class EditCourseActivity extends AppCompatActivity {

    private TextInputEditText cName, cPrice, cSuitedFor, cImg, cLink, cDescp ;
    private Button updateC_Btn, deleteC_Btn;
    private ProgressBar loadingPB;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String CourseId;
    private CourseRVmodel courseRVmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_course);

        cName = findViewById(R.id.idEditCourseName);
        cPrice = findViewById(R.id.idEditCoursePrice);
        cSuitedFor = findViewById(R.id.idEditCourseSuitedFor);
        cImg = findViewById(R.id.idEditCourseImageLink);
        cDescp = findViewById(R.id.idEditCourseDescription);
        updateC_Btn = findViewById(R.id.idBtnUpdateCourse);
        deleteC_Btn = findViewById(R.id.idBtnDeleteCourse);
        loadingPB = findViewById(R.id.idPBLoading);

        courseRVmodel = getIntent().getParcelableExtra("course");
        if(courseRVmodel!=null){
            cName.setText(courseRVmodel.getCourseName());
            cPrice.setText(courseRVmodel.getCoursePrice());
            cSuitedFor.setText(courseRVmodel.getCourseSuitedFor());
            cImg.setText(courseRVmodel.getCourseImg());
            cLink.setText(courseRVmodel.getCourseLink());
            cDescp.setText(courseRVmodel.getCourseDescription());

            CourseId = courseRVmodel.getCourseID();
        }

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Courses").child(CourseId);

        updateC_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingPB.setVisibility(View.VISIBLE);
                String courseName = cName.getText().toString();
                String coursePrice = cPrice.getText().toString();
                String courseSuitFor = cSuitedFor.getText().toString();
                String courseImage = cImg.getText().toString();
                String courseLink = cLink.getText().toString();
                String courseDescription = cDescp.getText().toString();

//                using hash maps to pass the data
                Map<String,Object> map = new HashMap<>();
                map.put("courseName",courseName);
                map.put("coursePrice",coursePrice);
                map.put("courseSuitedFor",courseSuitFor);
                map.put("courseImg",courseImage);
                map.put("courseLink",courseLink);
                map.put("courseDescription",courseDescription);
                map.put("courseID",CourseId);
                //courseId should be unique so it is not edited and is taken form
//  line50         CourseId = courseRVmodel.getCourseID();

            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    loadingPB.setVisibility(View.GONE);
                    databaseReference.updateChildren(map);
                    Toast.makeText(EditCourseActivity.this, "Course Updated!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(EditCourseActivity.this,MainActivity.class));
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(EditCourseActivity.this, "Failed to update course!!", Toast.LENGTH_SHORT).show();
                }
            });

            }
        });

        deleteC_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCourse();
            }
        });
    }

    private void deleteCourse(){
        databaseReference.removeValue();
        Toast.makeText(this, "Course Deleted!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(EditCourseActivity.this, MainActivity.class));
    }
}