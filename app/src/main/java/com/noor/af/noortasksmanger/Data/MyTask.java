package com.noor.af.noortasksmanger.Data;

import java.util.Date;

/**
 * Created by user on 9/8/2016.
 */
public class MyTask
{
    /**
     * rakam al mahama
     */
    private String id;
    /**
     * 3enwan
     */
    private String title;
    private int priority;
    private Date when;
    private String phone;
    private String address;



    public MyTask(String id, String title, int priority, Date when, String phone, String address)
    {
        this.id = id;
        this.title = title;
        this.priority = priority;
        this.when = when;
        this.phone = phone;
        this.address = address;

    }

    public MyTask()
    {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Date getWhen() {
        return when;
    }

    public void setWhen(Date when) {
        this.when = when;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString()
    {
        return "MyTask{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", priority=" + priority +
                ", when=" + when +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
