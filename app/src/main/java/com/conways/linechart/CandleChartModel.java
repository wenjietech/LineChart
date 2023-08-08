package com.conways.linechart;

public class CandleChartModel extends ChartModel{

    private int length;
    private int color;
    private String markText;
    private Type type;
    private boolean highlight;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getMarkText() {
        return markText;
    }

    public void setMarkText(String markText) {
        this.markText = markText;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean isHighlight() {
        return highlight;
    }

    public void setHighlight(boolean highlight) {
        this.highlight = highlight;
    }

    enum Type{
        HIGH, MEDIUM, LOW, INACTIVE
    }
}
