import java.util.HashMap;
import java.util.ArrayList;

/**
 * This class is generated by the env3d plugin to make
 * it easier to work with models.  It scans a model
 * directory for all the obj files and organized them.
 */
public class Mountain extends GameObject
{
    /**
     * Zero-argument constructor that places the object in location 0, 0, 0
     */
    public Mountain()
    {
        this(0,0,0);
    }

    /**
     * Parameterized constructor - allows arbitary of object
     */
    public Mountain(double x, double y, double z)
    {
        setX(x);
        setY(y);
        setZ(z);
        setScale(1);
        // --- DO NOT REMOVE ---
        // using mtl file "models/mountain/mountains.mtl"
        //       texture file "models/mountain/generic_grass4_col.png"
        //       texture file "models/mountain/generic_grassoverhang2_col.png"
        //       texture file "models/mountain/generic_grayrock1_col.png"
        //       texture file "models/mountain/generic_redrock_col.png"
        //       texture file "models/mountain/generic_redrock_nor.png"
        //       texture file "models/mountain/generic_redrock_spec.png"
        //       texture file "models/mountain/mountain1_ao.png"
        //       texture file "models/mountain/mountain2_ao.png"
        // ---------------------
        setTexture(null);
        setModel("models/mountain/mountains.obj");
    }
    
    public void move()
    {
        Hunter hunter = getEnv().getObject(Hunter.class);
        if (hunter.distance(this) < hunter.getScale() + this.getScale()) {
            hunter.revert();
        }
        
        for (Zombie z : getEnv().getObjects(Zombie.class)) {
            if (z.distance(this) < this.getScale()+z.getScale()) {
                z.setRotateY(z.getRotateY()+180);
            }
        }
    }

}