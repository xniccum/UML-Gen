package asm.api;

/**
 * Created by Steven on 1/4/2016.
 */
public interface IKlass extends IKlassPart {
    String getName();
    int getVersion();
    int getAccess();
}
