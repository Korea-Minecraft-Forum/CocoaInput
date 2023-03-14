package jp.axer.cocoainput.util;

public final class Rect {

    private final float x;
    private final float y;
    private final float height;
    private final float width;

    public Rect(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public float getWidth() {
        return this.width;
    }

    public float getHeight() {
        return this.height;
    }
}
