package asm.asmVisitor.DesignVisitors;

import asm.StorageApi.IKlass;
import asm.impl2.Actions.ComponentAction;
import asm.impl2.Actions.TargetAction;
import asm.impl2.DesignParts.DecoratorDesign;
import asm.impl2.DesignParts.SingletonDesign;
import asm.impl2.KlassDecorator;
import jdk.internal.org.objectweb.asm.Opcodes;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Steven on 1/19/2016.
 */
public class DecoratorClassVisitor extends ClassVisitor {
    private IKlass klass;
    private ArrayList<String> superClassNames;
    private String decoratedClassName;
    private boolean flagContainsSuperclass = false;

    private boolean designAdded = false;

    public DecoratorClassVisitor(int i, IKlass klass) {
        super(i);
        this.klass = klass;
    }

    public DecoratorClassVisitor(int i, ClassVisitor classVisitor, IKlass klass) {
        super(i, classVisitor);
        this.klass = klass;
    }

    private boolean conditionsMet(){
        return flagContainsSuperclass;
    }

    private void UpdateKlass(){
        if(conditionsMet()  && !designAdded){
            designAdded = true;
            klass.addKlassPart(new DecoratorDesign());
            klass.addAction(new ComponentAction(decoratedClassName));
        }
        else if(!conditionsMet() && designAdded){
            //remove klassPart
        }
    }

    /**
     * Used for Class Declaration Visitor
     *
     * @param version    version
     * @param access     access
     * @param name       name
     * @param signature  signature
     * @param superName  superName
     * @param interfaces interfaces
     */
    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);

        try {
            this.superClassNames = visitAndListSuperclasses(superName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Used to check fields for self
     *
     * @param access    access
     * @param name      name
     * @param desc      desc
     * @param signature signature
     * @param value     value
     * @return
     */
    @Override
    public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
//        String varType = KlassDecorator.fullStripClean(desc);
        ArrayList<String> typeSuperNames = new ArrayList<>();
        String cleanVarClass = KlassDecorator.stripTypeClassPath(desc);
        try {
            System.out.println("XXX type name: "+cleanVarClass);
            if(KlassDecorator.isDesirableObject(cleanVarClass)) {
                typeSuperNames = visitAndListSuperclasses(cleanVarClass);
            } else {
                typeSuperNames.add(cleanVarClass);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        String strippedSuperName, strippedFieldType;
        for(String superName : superClassNames) {
            strippedSuperName = KlassDecorator.fullStripClean(superName);

            for(String typeSuperName : typeSuperNames) {
                strippedFieldType = KlassDecorator.fullStripClean(typeSuperName);

                System.out.println("COMPARE: "+strippedFieldType + ", "+strippedSuperName);

                if(strippedSuperName.equals(strippedFieldType)) {
                    flagContainsSuperclass = true;
                    decoratedClassName = superName;
                    System.out.println("FLAG SET: flagContainsSuperclass = " + flagContainsSuperclass);
                    break;
                }

                if(flagContainsSuperclass) {
                    break;
                }
            }
        }

        // TODO private static field of ClassType
        UpdateKlass();
        return super.visitField(access, name, desc, signature, value);
    }

    private ArrayList<String> visitAndListSuperclasses(String className) throws IOException {
        ArrayList<String> classNames = new ArrayList();
        ClassReader reader = new ClassReader(className);
        String string = reader.getClassName();

        while(KlassDecorator.isDesirableObject(string)) {
            System.out.println("VISITSUPERCLASS"+string);
            classNames.add(string);
            reader = new ClassReader(string);
            string = reader.getSuperName();
        }
        return classNames;
    }

}
