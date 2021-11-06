package com.example.userauthproject;

import android.os.Parcel;
import android.os.Parcelable;

public class CourseRVmodel implements Parcelable {
    private String courseName;
    private String courseDescription;
    private String coursePrice;
    private String courseSuitedFor;
    private String courseImg;
    private String courseLink;
    private String courseID;

    public CourseRVmodel(String courseName, String courseDescription, String coursePrice, String courseSuitFor, String courseImage, String courseId){

    }

    public CourseRVmodel(String courseName, String courseDescription, String coursePrice, String courseSuitedFor, String courseImg, String courseLink, String courseID) {
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.coursePrice = coursePrice;
        this.courseSuitedFor = courseSuitedFor;
        this.courseImg = courseImg;
        this.courseLink = courseLink;
        this.courseID = courseID;
    }

    protected CourseRVmodel(Parcel in) {
        courseName = in.readString();
        courseDescription = in.readString();
        coursePrice = in.readString();
        courseSuitedFor = in.readString();
        courseImg = in.readString();
        courseLink = in.readString();
        courseID = in.readString();
    }

    public static final Creator<CourseRVmodel> CREATOR = new Creator<CourseRVmodel>() {
        @Override
        public CourseRVmodel createFromParcel(Parcel in) {
            return new CourseRVmodel(in);
        }

        @Override
        public CourseRVmodel[] newArray(int size) {
            return new CourseRVmodel[size];
        }
    };

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public String getCoursePrice() {
        return coursePrice;
    }

    public void setCoursePrice(String coursePrice) {
        this.coursePrice = coursePrice;
    }

    public String getCourseSuitedFor() {
        return courseSuitedFor;
    }

    public void setCourseSuitedFor(String courseSuitedFor) {
        this.courseSuitedFor = courseSuitedFor;
    }

    public String getCourseImg() {
        return courseImg;
    }

    public void setCourseImg(String courseImg) {
        this.courseImg = courseImg;
    }

    public String getCourseLink() {
        return courseLink;
    }

    public void setCourseLink(String courseLink) {
        this.courseLink = courseLink;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(courseName);
        dest.writeString(courseDescription);
        dest.writeString(coursePrice);
        dest.writeString(courseSuitedFor);
        dest.writeString(courseImg);
        dest.writeString(courseLink);
        dest.writeString(courseID);
    }
}
