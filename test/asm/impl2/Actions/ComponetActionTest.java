package asm.impl2.Actions;

import asm.StorageApi.IAction;
import asm.StorageApi.IKlassPart;
import asm.impl2.Klass;
//import asm.impl2.StandardDataObjects.Field;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

import static org.junit.Assert.*;

/**
 * Created by Steven on 1/28/2016.
 */
public class ComponetActionTest {
    HashMap<String, Klass> map;
    Field field;
    private final String METHODPATH = "asm.StorageApi.IMethod";
    private final String FIELDPATH = "asm.StorageApi.IField";
    private final String VISITORPATH = "asm.visitorApi.Visitor";
    @Before
    public void setUp() throws Exception {
        map = new HashMap<>();
        map.put("asm.StorageApi.IMethod", new Klass(METHODPATH, 0, -1));
        map.put("asm.StorageApi.IField", new Klass(FIELDPATH, 0, -1));
        field = Klass.class.getDeclaredField("klassParts");
        field.setAccessible(true);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testSetAndGetTargetClass() throws Exception {
        ComponetAction action = new ComponetAction("IField");
        assertEquals(action.getTargetClass(), "IField");
        action.setTargetClass("IMethod");
        assertEquals(action.getTargetClass(), "IMethod");
    }

    @Test
    public void testTriggerActionFound() throws Exception {
        Klass method = map.get(METHODPATH);
        Collection<IKlassPart> parts = (Collection<IKlassPart>) field.get(method);
        assertEquals(parts.size(), 0);

        IAction action = new ComponetAction(METHODPATH);
        action.triggerAction(map);
        assertEquals(parts.size(), 1);
    }

    @Test
    public void testTriggerActionNotFound() throws Exception {
        Klass method = map.get(METHODPATH);
        Collection<IKlassPart> parts = (Collection<IKlassPart>) field.get(method);
        assertEquals(parts.size(), 0);

        IAction action = new ComponetAction(VISITORPATH);
        action.triggerAction(map);
        assertEquals(parts.size(), 0);
    }
}