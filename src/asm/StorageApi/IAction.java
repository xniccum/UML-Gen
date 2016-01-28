package asm.StorageApi;

import asm.impl2.Klass;

import java.util.HashMap;

/**
 * Created by Steven on 1/27/2016.
 */
public interface IAction {
     void setTargetClass(String className);
     String getTargetClass();
     void triggerAction(HashMap<String, Klass> map);
}
