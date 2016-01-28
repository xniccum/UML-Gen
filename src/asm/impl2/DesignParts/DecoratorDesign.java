package asm.impl2.DesignParts;

import asm.StorageApi.DesignType;
import asm.impl2.KlassDecorator;

/**
 * Created by Steven on 1/19/2016.
 */
public class DecoratorDesign extends KlassDecorator implements DesignType {
    private String designName = "decorator";

    @Override
    public String getDesignName() {
        return this.designName;
    }

}
