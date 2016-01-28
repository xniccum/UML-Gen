package asm.asmVisitor.DesignVisitors;

import asm.StorageApi.IAction;
import asm.StorageApi.IKlass;
import asm.impl2.Actions.TargetAction;
import asm.impl2.DesignParts.AdaptorDesign;
import asm.impl2.DesignParts.SingletonDesign;
import asm.impl2.DesignParts.TargetDesign;
import asm.impl2.KlassDecorator;
import jdk.internal.org.objectweb.asm.Opcodes;
import jdk.internal.org.objectweb.asm.Type;
import jdk.nashorn.internal.runtime.regexp.joni.constants.OPCode;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;

/**
 * Created by Austin on 1/27/2016.
 */
public class AdaptorClassVisitor extends ClassVisitor {

    private IKlass klass;
    private String implementsInstance;
    private String privateFieldInstance;
    private String constructorArgumentInstance;
    private boolean designAdded = false;
    private int fieldCount = 0;

    public AdaptorClassVisitor(int i, IKlass klass) {
        super(i);
        this.klass = klass;
    }

    public AdaptorClassVisitor(int i, ClassVisitor classVisitor, IKlass klass) {
        super(i, classVisitor);
        this.klass = klass;
    }

    private boolean conditionsMet(){
        return (implementsInstance!=null) && (privateFieldInstance!=null) && !(implementsInstance.equals(privateFieldInstance)) &&
                (fieldCount == 1) && (privateFieldInstance.equals(constructorArgumentInstance));
    }

    private void UpdateKlass(){
        if(conditionsMet()  && !designAdded) {
            designAdded = true;
            klass.addKlassPart(new TargetDesign());
            klass.addAction(new TargetAction(implementsInstance));
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
        if(interfaces.length == 1)   {
            this.implementsInstance = interfaces[0];
        }

        super.visit(version, access, name, signature, superName, interfaces);
    }

    /**
     * Used to check fields for self
     * @param access    access
     * @param name      name
     * @param desc      desc
     * @param signature signature
     * @param value     value
     * @return
     */
    @Override
    public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
        String varType = KlassDecorator.fullStripClean(desc);
        this.fieldCount++;

        if(access == Opcodes.ACC_PRIVATE) {
            privateFieldInstance = varType;
        }

        UpdateKlass();
        return super.visitField(access, name, desc, signature, value);
    }

    /**
     * Used to check for method return
     * @param access     access
     * @param name       name
     * @param desc       desc
     * @param signature  signature
     * @param exceptions exceptions
     * @return
     */
    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        String sClassName = KlassDecorator.stripClassPath(klass.getName());
        String mName = KlassDecorator.stripClassPath(name);

        if(name.equals("<init>")) {
            Type[] argTypes = Type.getArgumentTypes(desc);
            if(argTypes.length == 1) {
                constructorArgumentInstance = argTypes[0].getClassName();
            }
        }

        UpdateKlass();
        return super.visitMethod(access, name, desc, signature, exceptions);
    }
}
