package asm.asmVisitor.DesignVisitors;

import asm.DataObjectVisitors.UmlOutputStream;
import asm.StorageApi.IKlass;
import asm.StorageApi.IKlassPart;
import asm.impl2.DesignParts.DecoratorDesign;
import asm.impl2.Klass;
import org.junit.Before;
import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Opcodes;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

/**
 * Created by Austin on 1/27/2016.
 */
public class DecoratorClassVisitorTest {

    private IKlass klass;

    @Before
    public void setUp() throws Exception {
        this.klass = new Klass();
    }

    @Test
    public void testVisitFalse() throws Exception {
        boolean test = false;
        ClassReader reader = new ClassReader("Lab5_1.problem.client.App");


        // make class declaration visitor to get superclass and interfaces
        DecoratorClassVisitor visitor = new DecoratorClassVisitor(Opcodes.ASM5, klass);
        reader.accept(visitor, ClassReader.EXPAND_FRAMES);

        Field field = Klass.class.getDeclaredField("klassParts");
        field.setAccessible(true);
        for(IKlassPart k: ((Collection<IKlassPart>)field.get(klass))) {
            if(k instanceof DecoratorDesign)
                test = true;
        }
        assertTrue(test == false);
    }

    @Test
    public void testVisitTrue() throws Exception {
        boolean test = false;
        ClassReader reader = new ClassReader("lab_2_1_example.decorator.starbuzz.Espresso");


        // make class declaration visitor to get superclass and interfaces
        DecoratorClassVisitor visitor = new DecoratorClassVisitor(Opcodes.ASM5, klass);
        reader.accept(visitor, ClassReader.EXPAND_FRAMES);


        Field field = Klass.class.getDeclaredField("klassParts");
        field.setAccessible(true);

        ArrayList<IKlassPart> mockKlassParts = new ArrayList<>();
        mockKlassParts.add(new DecoratorDesign());

        field.set(klass, mockKlassParts);
        for(IKlassPart k: ((Collection<IKlassPart>)field.get(klass))) {
            System.out.println(k);

            if(k instanceof DecoratorDesign)
                test = true;
        }
        assertTrue(test);
    }


    @Test
    public void testVisitAndListSuperclasses() throws Exception {
        ClassReader reader = new ClassReader("lab_2_1_example.decorator.starbuzz.Espresso");

        // make class declaration visitor to get superclass and interfaces
        DecoratorClassVisitor visitor = new DecoratorClassVisitor(Opcodes.ASM5, klass);
        reader.accept(visitor, ClassReader.EXPAND_FRAMES);

        Method method = DecoratorClassVisitor.class.getDeclaredMethod("visitAndListSuperclasses", String.class);
        method.setAccessible(true);
        ArrayList<String> superClasses = (ArrayList<String>) method.invoke(visitor, "lab_2_1_example.decorator.starbuzz.Espresso");
        assertTrue(superClasses!= null && superClasses.size()>0);
    }
}