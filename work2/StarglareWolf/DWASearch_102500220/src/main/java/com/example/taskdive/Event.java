package com.example.taskdive;

import java.util.*;
import java.util.ArrayList;

// 查询相应赛事
public class Event {
    private String EventName;
    public Event(){
        this.EventName = "World Aquatics Diving World Cup 2026 - Super Final - Stop 1(Beijing)";
    }
    public List<Country>Countries = new ArrayList<>();

    public Event(String EventName){
        this.EventName = EventName;
    }

    public String getEventName(){
        return this.EventName;
    }
    public void setEventName(String EventName){
        this.EventName = EventName;
    }

    public String toString(){
        return this.EventName;
    }
}
