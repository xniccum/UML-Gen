package asm;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

import static org.junit.Assert.*;

/**
 * Created by Austin on 1/5/2016.
 */
public class ClassFieldVisitorTest
{
    @org.junit.Before
    public void setUp() throws Exception {
        ClassReader reader=new ClassReader("asm.impl.FirstASM");

        // make class declaration visitor to get superclass and interfaces
        ClassVisitor decVisitor = new ClassDeclarationVisitor(Opcodes.ASM5);
    }

    @org.junit.Test
    public void FieldVisitorTest()
    {

    }

    @org.junit.After
    public void tearDown() throws Exception {

    }

}