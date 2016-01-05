package asm.api;

/**
 * Created by Steven on 1/4/2016.
 */
public interface IMethod extends IKlassPart{
    public String getAccessLevel();
    public String getMethodName();
    public String getReturnType();
    public Object[] getArguments();
    public String[] getExceptions();

}
