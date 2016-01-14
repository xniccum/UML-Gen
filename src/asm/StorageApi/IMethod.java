package asm.StorageApi;

import asm.impl.Argument;

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
    public HashSet<String> getUsedClasses();

}
