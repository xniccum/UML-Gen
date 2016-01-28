package asm.impl2.Actions;

import asm.StorageApi.IAction;
import asm.impl2.DesignParts.ComponetDesign;
import asm.impl2.DesignParts.TargetDesign;
import asm.impl2.Klass;

import java.util.HashMap;

/**
 * Created by Steven on 1/27/2016.
 */
public class TargetAction implements IAction {
    private String targetClass;

    public TargetAction(String targetClass) {
        this.targetClass = targetClass;
    }

    @Override
    public void setTargetClass(String className) {
        this.targetClass = className;
    }

    @Override
    public void triggerAction(HashMap<String, Klass> map) {
        Klass k =  map.get(targetClass);
        if(k != null)
            k.addKlassPart(new TargetDesign());
    }

    @Override
    public String getTargetClass() {
        return targetClass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TargetAction)) return false;

        TargetAction that = (TargetAction) o;

        return targetClass.equals(that.targetClass);

    }

    @Override
    public int hashCode() {
        return targetClass.hashCode();
    }
}