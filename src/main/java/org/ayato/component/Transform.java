package org.ayato.component;

import org.ayato.system.ToonMaster;

public class Transform {
    public final Position position;
    public final Scale scale;
    public final Rotate rotate;

    public Transform(int x, int y, int w, int h){
        this(new Position(x, y), new Scale(w, h), new Rotate());
    }
    public Transform(Position position){
        this(position, new Scale(), new Rotate());
    }

    public Transform(Position position, Scale scale, Rotate rotate) {
        this.position = position;
        this.scale = scale;
        this.rotate = rotate;
    }
    public Vector2D getPosition(){
        return new Vector2D(position.getX(),position.getY());
    }
    public Vector2D getPositionAdd(int ax, int ay){
        Vector2D newV = new Vector2D( ax * ToonMaster.getINSTANCE().DW, ay * ToonMaster.getINSTANCE().DH);

        return rotate.transRotate(newV).add(position.getX(), position.getY());
    }
    public int getW(){
        return scale.getW();
    }
    public int getH(){
        return scale.getH();
    }

    public boolean isCollision(int x, int y) {
        return x >= position.getX() && x  <= position.getX() + scale.getW()
                && y >= position.getY() && y <= position.getY() + scale.getH();
    }
    public boolean isCollision(Transform transform){
        return position.getX() < transform.position.getX() + transform.scale.getW() &&
                position.getX() + getW() > transform.position.getX() &&
                position.getY() < transform.position.getY() + transform.getH()
                && position.getY() + getH() > transform.position.getY();
    }
}
