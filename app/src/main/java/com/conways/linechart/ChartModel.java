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
    private String index;

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
}
