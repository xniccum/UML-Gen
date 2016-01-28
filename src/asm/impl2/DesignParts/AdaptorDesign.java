package asm.impl2.DesignParts;

import asm.StorageApi.DesignType;
import asm.impl2.KlassDecorator;

/**
 * Created by Austin on 1/27/2016.
 */
public class AdaptorDesign extends KlassDecorator implements DesignType {
    private String designName = "adaptor";

    @Override
    public String getDesignName() {
        return this.designName;
    }
}
