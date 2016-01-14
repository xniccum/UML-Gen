package asm.impl2.Methodimp;

import asm.StorageApi.MethodStorage.IMethodPart;
import asm.visitorApi.ITraverser;
import asm.visitorApi.IVisitor;

/**
 * Created by Steven on 1/13/2016.
 */
public abstract class MethodDecorator implements IMethodPart, ITraverser {
    @Override
    public void accept(IVisitor v) {
        v.preVisit(this);
        v.nameVisit(this);
        v.fieldVisit(this);
        v.methodVisit(this);
        v.postVisit(this);
    }
}
