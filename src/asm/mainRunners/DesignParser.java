package asm.mainRunners;

import asm.DataObjectVisitors.UmlOutputStream;
import asm.FilePaths;
import asm.StorageApi.IAction;
import asm.asmVisitor.DesignVisitors.SingletonClassVisitor;
import asm.asmVisitor.StandardVisitors.ClassDeclarationVisitor;
import asm.asmVisitor.StandardVisitors.ClassFieldVisitor;
import asm.asmVisitor.StandardVisitors.ClassMethodVisitor;
import asm.impl2.Klass;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

import java.io.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

public class DesignParser {
    public static final String OUTPUT_PATH = "inputOutput/output.dot";
    public static final String INPUT_FILE_PATH = FilePaths.PROJECTCLASSES;

    /**
     * Reads in a list of Java Classes and reverse engineers their design.
     *
     * @param args: the names of the classes, separated by spaces.
     *              For example: java DesignParser java.lang.String edu.rosehulman.csse374.ClassFieldVisitor java.lang.Math
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        HashMap<String, Klass> classes = new HashMap<>();
        HashSet<IAction> actions = new HashSet<>();

        BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_PATH));

        for (String className; (className = br.readLine()) != null; ) {
            Klass klass = new Klass();
            // ASM's ClassReader does the heavy lifting of parsing the compiled Java class
            try {
                ClassReader reader = new ClassReader(className);

                // make class declaration visitor to get superclass and interfaces
                ClassVisitor decVisitor = new ClassDeclarationVisitor(Opcodes.ASM5, klass);

                // DECORATE declaration visitor with field visitor
                ClassVisitor fieldVisitor = new ClassFieldVisitor(Opcodes.ASM5, decVisitor, klass);

                // DECORATE field visitor with method visitor
                ClassVisitor methodVisitor = new ClassMethodVisitor(Opcodes.ASM5, fieldVisitor, klass);

                // Decorate with
                ClassVisitor singletonClassVisitor = new SingletonClassVisitor(Opcodes.ASM5, methodVisitor, klass);

                // Tell the Reader to use our (heavily decorated) ClassVisitor to visit the class
                reader.accept(singletonClassVisitor, ClassReader.EXPAND_FRAMES);

                classes.put(klass.getName(), klass);
                klass.getActions().forEach(a -> actions.add(a));

            } catch (IOException e) {
                System.out.println("|----------------------------------------------------------| CLASS NOT FOUND!! : " + className);
            }
        }
        br.close();
        applyAfterActions(classes, actions);
        printToFile(classes);
    }

    private static void applyAfterActions(HashMap<String, Klass> classes, Collection<IAction> actions) {
        actions.forEach(a -> a.triggerAction(classes));
    }

    private static void printToFile(HashMap<String, Klass> classes) throws IOException {
        OutputStream dotOut = new FileOutputStream(OUTPUT_PATH);
        UmlOutputStream umlOut = new UmlOutputStream(dotOut);

        String s = "strict digraph G {\n" +
                "    fontname = \"Bitstream Vera Sans\"\n" +
                "    fontsize = 8\n" +
                "\n" +
                "    node [\n" +
                "    fontname = \"Bitstream Vera Sans\"\n" +
                "    fontsize = 8\n" +
                "    shape = \"record\"\n" +
                "    ]\n" +
                "\n" +
                "    edge [\n" +
                "    fontname = \"Bitstream Vera Sans\"\n" +
                "    fontsize = 8\n" +
                "    ]\n";
        umlOut.write(s.getBytes());

        classes.forEach((name, klass) -> {
            umlOut.setClassName(name);
            umlOut.write(klass);
        });

        umlOut.write("}".getBytes());
        umlOut.close();
    }
}
