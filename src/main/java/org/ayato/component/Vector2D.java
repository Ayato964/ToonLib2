package org.ayato.component;

public record Vector2D(int x, int y) {
    public Vector2D add(int x, int y) {
        return new Vector2D(this.x + x, this.y + y);
    }
}
