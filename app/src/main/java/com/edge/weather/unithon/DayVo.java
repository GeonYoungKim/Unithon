package com.edge.weather.unithon;

import java.util.ArrayList;

/**
 * Created by c2619 on 2018-01-26.
 */

public class DayVo {

    private String day;
    private ArrayList<DateVo> scheduleList;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public ArrayList<DateVo> getScheduleList() {
        return scheduleList;
    }

    public void setScheduleList(ArrayList<DateVo> scheduleList) {
        this.scheduleList = scheduleList;
    }
}


