package asm.StorageApi.MethodStorage;

import asm.StorageApi.IMethod;

/**
 * Created by Steven on 1/13/2016.
 */
public interface IMethodInternalCall extends IMethodPart {
    public String getClassName();
    public String getCallName();
    public String getCallDescriptor();
    public boolean isInterface();

}
