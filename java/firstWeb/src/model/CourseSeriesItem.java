package model;

import java.util.Date;

public class CourseSeriesItem {
	private int id;
	private String classDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getClassDate() {
		return classDate;
	}
	public void setClassDate(String classDate) {
		this.classDate = classDate;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public int getSeriesId() {
		return seriesId;
	}
	public void setSeriesId(int seriesId) {
		this.seriesId = seriesId;
	}
	public int getSortCourse() {
		return sortCourse;
	}
	public void setSortCourse(int sortCourse) {
		this.sortCourse = sortCourse;
	}
	private int courseId;
	private int seriesId;
	private int sortCourse;

	
}
