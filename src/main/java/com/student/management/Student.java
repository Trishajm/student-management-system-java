package com.student.management;

public class Student {
    private int id;
    private String name;
    private String course;
    private String branch;
    private int marks;

    public Student(int id,String name,String course,String branch,int marks)
    {
        this.id=id;
        this.name=name;
        this.course=course;
        this.branch=branch;
        this.marks=marks;
    }
    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id=id;
    }

    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name=name;
    }
    public String getBranch()
    {
        return branch;
    }
    public void setBranch(String branch)
    {
        this.branch=branch;
    }
    public String getCourse()
    {
        return course;
    }
    public void setCourse(String course)
    {
        this.course=course;
    }
    public int getMarks()
    {
        return marks;
    }
    public void setMarks(int marks)
    {
        this.marks=marks;
    }


    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Branch: " + branch + ", Course: " + course + " ,Marks: " + marks;


    }
}
