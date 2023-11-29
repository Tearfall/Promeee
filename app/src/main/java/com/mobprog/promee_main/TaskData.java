package com.mobprog.promee_main;

public class TaskData {
    String title, date, time, note;

    public TaskData(String title, String date, String time, String note) {
        this.title = title;
        this.date = date;
        this.time = time;
        this.note = note;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
