package org.ayato.animation;

import org.ayato.animation.text.properties.IProperty;
import org.ayato.component.Vector2D;
import org.ayato.system.BaseAbstractObject;
import org.ayato.system.ToonMaster;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.function.Supplier;

public class Animation<T> extends BaseAbstractObject {
    public final Supplier<T> baseObject;
    public T mes;
    public final ToonMaster MASTER;
    protected Properties<T> properties;
    protected BufferedImage baseGraphics;
    public Animation(ToonMaster master, Supplier<T> base, Properties<T> prop){
        baseObject = base;
        mes = base.get();
        MASTER = master;
        properties = prop;
        baseGraphics = new BufferedImage(prop.transform.getW(), prop.transform.getH(), BufferedImage.TYPE_INT_ARGB);
    }

    @Override
    public void display(Graphics g) {
            Graphics2D g2 = (Graphics2D) baseGraphics.getGraphics();
            g2.setComposite(AlphaComposite.Clear);
            g2.fillRect(0, 0, properties.transform.getW(), properties.transform.getH());
            g2.setComposite(AlphaComposite.Src);



            mes = baseObject.get();
            if (properties != null)
                properties.runProp(g2, g, this);
            if(mes != null)
                runGraphics(g2);

            Vector2D vec = properties.transform.getPosition();
            g.drawImage(baseGraphics, vec.x() - baseGraphics.getWidth() / 2, vec.y() - baseGraphics.getHeight() / 2,
                properties.transform.getW(), properties.transform.getH(), null);

            Color now = g.getColor();
            g.setColor(new Color(now.getRGB()));

            Color now2 = g2.getColor();
            g2.setColor(new Color(now2.getRGB()));

    }
    protected void runGraphics(Graphics2D g2){
        properties.run(g2, mes, baseGraphics.getWidth() / 2, baseGraphics.getHeight() / 2);
    }
    public <A extends IProperty> A getPropertyClass(Class<A> cls){
        A a = null;
        for (IProperty p : properties.init_properties){
            if(cls.isInstance(p)){
                a = (A) p;
            }
        }
        return a;
    }

    public void setViewObject(T mes) {
        this.mes = mes;
    }

    public T getViewObject() {
        return baseObject.get();
    }

    public void init(){
        properties.init();
    }
}
