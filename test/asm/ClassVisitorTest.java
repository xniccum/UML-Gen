package asm;

import org.junit.Before;
import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

/**
 * Created by Austin on 1/5/2016.
 */
public class ClassVisitorTest
{
    private KlassStorage storage;
    private ClassReader reader;

    @Before
    public void setUp() throws Exception
    {
        storage = new KlassStorage();
        reader=new ClassReader("asm.mainRunners.FirstASM");
    }

    @Test
    public void VisitTest()
    {
        // make class declaration visitor to get superclass and interfaces
      //  ClassVisitor decVisitor = new ClassDeclarationVisitor(Opcodes.ASM5, storage);

        // DECORATE declaration visitor with field visitor
       // ClassVisitor fieldVisitor = new ClassFieldVisitor(Opcodes.ASM5, decVisitor, storage);

        // DECORATE field visitor with method visitor
      //  ClassVisitor methodVisitor = new ClassMethodVisitor(Opcodes.ASM5, fieldVisitor, storage);

        // Tell the Reader to use our (heavily decorated) ClassVisitor to visit the class
      //  reader.accept(methodVisitor, ClassReader.EXPAND_FRAMES);
    }
}
