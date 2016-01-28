package asm.impl2.DesignParts;

import asm.StorageApi.DesignType;
import asm.impl2.KlassDecorator;

/**
 * Created by Steven on 1/27/2016.
 */
public class TargetDesign extends KlassDecorator implements DesignType {
    private final String designName ="target";

    @Override
    public String getDesignName() {
        return designName;
    }
}