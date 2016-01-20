package asm.impl2.DesignParts;

import asm.StorageApi.DesignType;
import asm.impl2.KlassDecorator;
import asm.visitorApi.ITraverser;
import asm.visitorApi.IVisitor;

/**
 * Created by Steven on 1/19/2016.
 */
public class SingletonDesign extends KlassDecorator implements DesignType {
    private String designName = "singleton";

    @Override
    public String getDesignName() {
        return this.designName;
    }

}
