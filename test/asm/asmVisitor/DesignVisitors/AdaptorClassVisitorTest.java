package asm.asmVisitor.DesignVisitors;

import asm.DataObjectVisitors.UmlOutputStream;
import asm.StorageApi.IKlass;
import asm.StorageApi.IKlassPart;
import asm.asmVisitor.StandardVisitors.ClassDeclarationVisitor;
import asm.impl2.DesignParts.AdaptorDesign;
import asm.impl2.Klass;
import org.junit.Before;
import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

import java.lang.reflect.Field;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Created by Austin on 1/27/2016.
 */
public class AdaptorClassVisitorTest {

    private IKlass klass;
    private AdaptorClassVisitor visitor;
    private UmlOutputStream stream;

    @Before
    public void setUp() throws Exception {
        this.klass = new Klass();
    }

    @Test
    public void testVisit() throws Exception {
        boolean test = false;
        ClassReader reader = new ClassReader("");

        // make class declaration visitor to get superclass and interfaces
        AdaptorClassVisitor visitor = new AdaptorClassVisitor(Opcodes.ASM5, klass);
        reader.accept(visitor, ClassReader.EXPAND_FRAMES);


        Field field = Klass.class.getDeclaredField("klassParts");
        field.setAccessible(true);
        for(IKlassPart k: ((Collection<IKlassPart>)field.get(klass))) {
            if(k instanceof AdaptorDesign)
                test = true;
        }
        assertTrue(false);
    }
}