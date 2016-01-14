package asm.StorageApi;

import asm.StorageApi.MethodStorage.IMethodPart;
import asm.impl.Argument;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by Steven on 1/4/2016.
 */
public interface IMethod extends IKlassPart{
    public String getAccessLevel();
    public String getMethodName();
    public String getReturnType();
    public Argument[] getArguments();
    public String[] getExceptions();
    public void addMethodPart(IMethodPart part);
    public ArrayList<IMethodPart> getMethodParts();
    public void addSubMethod(IMethod method);
    public ArrayList<IMethod> getSubMethods();
    public String getClassName();
    public void setClassName(String className);
}
