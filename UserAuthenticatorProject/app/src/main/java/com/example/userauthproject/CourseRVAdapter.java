package com.example.userauthproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CourseRVAdapter extends RecyclerView.Adapter<CourseRVAdapter.ViewHolder> {

    private ArrayList<CourseRVmodel> courseRVmodelArrayList;
    private Context context;
    int lastPos = -1;
    private CourseClickInterface courseClickInterface ;

    public CourseRVAdapter(ArrayList<CourseRVmodel> courseRVmodelArrayList, Context context, CourseClickInterface courseClickInterface) {
        this.courseRVmodelArrayList = courseRVmodelArrayList;
        this.context = context;
        this.courseClickInterface = courseClickInterface;
    }

    @NonNull
    @Override
    public CourseRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return null;
        View view = LayoutInflater.from(context).inflate(R.layout.course_rv_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseRVAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        CourseRVmodel courseRVmodel = courseRVmodelArrayList.get(position);
        holder.courseNameTV.setText(courseRVmodel.getCourseName());
        holder.coursePriceTV.setText("Rs. "+ courseRVmodel.getCoursePrice());
        Picasso.get().load(courseRVmodel.getCourseImg()).into(holder.courseIV);
        setAnimation(holder.itemView, position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                courseClickInterface.onCourseClick(position);
            }
        });
    }

    private void setAnimation(View itemView, int position){
        if(position> lastPos){
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            itemView.setAnimation(animation);
            lastPos = position;
        }
    }

    @Override
    public int getItemCount() {
        return courseRVmodelArrayList.size();
    }

    public interface CourseClickInterface{
        void onCourseClick(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView courseNameTV,coursePriceTV;
        private ImageView courseIV;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            courseNameTV = itemView.findViewById(R.id.idTVCourseName);
            coursePriceTV = itemView.findViewById(R.id.idTVPrice);
            courseIV =itemView.findViewById(R.id.idIVCourses);
        }
    }


}
