package com.conways.linechart;

import java.util.ArrayList;
import java.util.List;

public class SleepModel {

    private String startTime = "";
    private String endTime = "";

    private List<ChartModel> list = new ArrayList<>();


    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public List<ChartModel> getList() {
        return list;
    }

    public void setList(List<ChartModel> list) {
        this.list = list;
    }
}
