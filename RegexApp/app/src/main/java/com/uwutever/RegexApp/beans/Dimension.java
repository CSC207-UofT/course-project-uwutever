package com.uwutever.RegexApp.beans;

/**
 * The type Dimension.
 */
public class Dimension {

    private int width;
    private int height;

    public Dimension(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setWidth(int width) {
        this.width=width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Dimension) {
            Dimension other = (Dimension) object;
            return (width == other.width) && (height == other.height);
        }
        else {
            return false;
        }
    }
}