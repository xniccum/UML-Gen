package asm.impl2.Methodimp;

import asm.StorageApi.MethodStorage.IMethodInternalCall;

/**
 * Created by Steven on 1/13/2016.
 */
public class MethodInternalCall extends MethodDecorator implements IMethodInternalCall {
    private String owner;
    private String name;
    private String desc;
    private boolean itf;


    public MethodInternalCall(String owner, String name, String desc, boolean itf) {
        this.owner = owner;
        this.name = name;
        this.desc = desc;
        this.itf = itf;
    }

    @Override
    public String getClassName() {
        return owner;
    }

    @Override
    public String getCallName() {
        return name;
    }

    @Override
    public String getCallDescriptor() {
        return desc;
    }

    @Override
    public boolean isInterface() {
        return itf;
    }
}
