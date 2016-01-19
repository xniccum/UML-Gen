package asm;

import asm.StorageApi.IKlassPart;
import asm.StorageApi.IMethod;
import asm.StorageApi.MethodStorage.IMethodInternalCall;
import asm.StorageApi.MethodStorage.IMethodPart;
import asm.impl2.Klass;
import asm.impl2.KlassDecorator;
import asm.visitorApi.*;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;

/**
 * Created by Steven on 1/12/2016.
 */
public class SequenceOutputStream extends FilterOutputStream {
    private final IVisitor visitor;
    private LinkedHashSet<String> usedClassNames;
    private boolean classNamesOutputed = false;


    /**
     * Depends on Klass being the very base object
     *
     * @param out
     * @throws IOException
     */
    public SequenceOutputStream(OutputStream out) throws IOException {
        super(out);
        this.visitor = new Visitor();
        this.usedClassNames = new LinkedHashSet<>();
        setupMethodVisitMethods();
        setupNameVisitMethod();
        setupPreVisitMethod();

    }

    private void write(String m) {
        try {
            super.write(m.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void write(IMethod method) {
        ITraverser t = (ITraverser) method;
        t.accept(this.visitor);
    }




    private void setupPreVisitMethod(){
        this.visitor.addVisit(VisitType.PreVisit, IMethod.class, (ITraverser t) -> {
            IMethod m = (IMethod) t;

            //use hashset to remove duplicates
            usedClassNames.add(KlassDecorator.fullStripClean(m.getClassName()));


            //add used classes from submethods
            ArrayList<IMethodPart> subMethods = m.getMethodParts();
            for (IMethodPart subMethod : subMethods) {
                IMethodInternalCall call = (IMethodInternalCall) subMethod;
                usedClassNames.add(KlassDecorator.fullStripClean(call.getClassName()));
            }

           // if(m.isTopLevel()) {
            //    s = KlassDecorator.fullStripClean(m.getClassName())+":"+KlassDecorator.fullStripClean(m.getClassName())+"\n";
            //} else {//add to hashset
              //  s = String.format(KlassDecorator.fullStripClean(m.getClassName())+ ":" + KlassDecorator.fullStripClean(m.getClassName()) + "\n");
            //}


        });
    }

    private void setupNameVisitMethod() {
        this.visitor.addVisit(VisitType.NameVisit, IMethod.class, (ITraverser t) -> {
            //only print once
            if(this.classNamesOutputed)
                return;
            for(String s : this.usedClassNames){
                this.write(String.format("%s:%s\n", s, s));
            }
            this.write("\n");
            this.classNamesOutputed = true;

        });
    }

    private void setupMethodVisitMethods() {
        this.visitor.addVisit(VisitType.MethodVisit, IMethod.class, (ITraverser t) -> {
            IMethod m = (IMethod) t;
            /**ArrayList<IMethod> subMethods = m.getSubMethods();
            String s = "";

//            s = String.format(callingClass+ ":" + KlassDecorator.fullStripClean(m.getClassName()) + "\n\n");
            for (IMethod subMethod : subMethods) {
                s += String.format(KlassDecorator.fullStripClean(m.getClassName()) + ":" + KlassDecorator.fullStripClean(subMethod.getClassName())
                        + "." + subMethod.getMethodName() + "(...)\n");
            }
            this.write(s);**/


            ArrayList<IMethodPart> subMethods = m.getMethodParts();
            String s = "";

//            s = String.format(callingClass+ ":" + KlassDecorator.fullStripClean(m.getClassName()) + "\n\n");
            String callName;
            for (IMethodPart subMethod : subMethods) {
                IMethodInternalCall call = (IMethodInternalCall) subMethod;
                callName = call.getCallName();
                if(callName.equals("<init>")) {
                    callName = "new";
                }


                s += String.format(KlassDecorator.fullStripClean(m.getClassName()) + ":" + KlassDecorator.fullStripClean(call.getClassName())
                        + "." + callName + "(...)\n");
            }
            this.write(s);
        });
    }
}
