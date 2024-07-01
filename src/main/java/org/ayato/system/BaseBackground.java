package org.ayato.system;

import org.ayato.component.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class BaseBackground{
    protected ArrayList<Layer> layers = new ArrayList<>();
    protected BufferedImage base;
    protected BufferedImage subBase;
    protected Transform subTransform;
    public BaseBackground(){
        this(0, 0);
    }
    public BaseBackground(int x, int y){
        this(x, y, (int) (ToonMaster.getINSTANCE().FRAME.DESCTOP_BOUNDS.getWidth() / ToonMaster.getINSTANCE().DW),
                (int) (ToonMaster.getINSTANCE().FRAME.DESCTOP_BOUNDS.getHeight() / ToonMaster.getINSTANCE().DH));
    }
    public BaseBackground(int x, int y,int width, int height){
        setLayer(layers);

        subTransform = new Transform(
                new Position(x, y),
                new Scale((int) ToonMaster.getINSTANCE().FRAME.DESCTOP_BOUNDS.getWidth() / ToonMaster.getINSTANCE().DW,
                        (int) ToonMaster.getINSTANCE().FRAME.DESCTOP_BOUNDS.getHeight() / ToonMaster.getINSTANCE().DH),
                new Rotate(0)
        );
        base = new BufferedImage(width * ToonMaster.getINSTANCE().DW,
                height * ToonMaster.getINSTANCE().DH,
                BufferedImage.TYPE_INT_ARGB);
    }
    public abstract void setLayer(ArrayList<Layer> layers);
    public final void display(Graphics2D g, int width, int height){
        repaint();

        Vector2D pos = subTransform.getPosition();
        subBase = base.getSubimage(pos.x(), pos.y(), subTransform.getW(), subTransform.getH());
        g.drawImage(subBase, 0, 0,width, height, null);

    }
    private void repaint(){
        Graphics2D g2 = (Graphics2D) base.getGraphics();
        for(Layer l : layers)
            l.display(g2, base.getWidth(), base.getHeight());

    }
}
