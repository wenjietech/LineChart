package com.conways.linechart;

/**
 * Created by Conways on 2017/4/21.
 */

public class ChartModel {
    /**
     * y-axis value
     */
    private int value;
    /**
     * x-axis display text
     */
    private String index = "";
    private String title = "";


    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
