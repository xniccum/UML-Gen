package asm.api;

/**
 * Created by Steven on 1/4/2016.
 *
 * Assumes that the Fields will be attached Before the methods
 */
public interface IKlassPart {
    String getBaseName();

    String printBefore();
    String printMiddle();
    String printEnd();
}
