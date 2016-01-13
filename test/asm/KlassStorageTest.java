package asm;

import asm.impl2.Klass;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Austin on 1/5/2016.
 */
public class KlassStorageTest {
    private KlassStorage klassStorage;
    @Before
    public void setUp() throws Exception
    {
        klassStorage = new KlassStorage();
    }
    @Test
    public void ToStringTest()
    {
        klassStorage.setCurrentPart(new Klass("HelloClass",2,0));
        assertEquals(klassStorage.toString(),
                "HelloClass [ \n" + " label = \" { HelloClass|| \n" + " } \" \n" + " ]");

        klassStorage.setCurrentPart(new Klass("GoodbyeClass",0,1));
        assertEquals(klassStorage.toString(),
                "GoodbyeClass [ \n" + " label = \" { GoodbyeClass|| \n" + " } \" \n" + " ]");
    }
}