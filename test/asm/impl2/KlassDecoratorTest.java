package asm.impl2;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Austin on 1/14/2016.
 */
public class KlassDecoratorTest {

    @Test
    public void testStripFilePath() throws Exception {
        assertEquals("test",KlassDecorator.stripFilePath("../this/is/test"));
        assertEquals("test.aswell",KlassDecorator.stripFilePath("../this/is/test.aswell"));
        assertEquals("",KlassDecorator.stripFilePath("../this/may/cause/problems/"));
    }

    @Test
    public void testStripClassPath() throws Exception {
        assertEquals("test",KlassDecorator.stripClassPath("this.is.a.test"));
        assertEquals("aswell",KlassDecorator.stripClassPath("../this/is/test/aswell"));
        assertEquals("",KlassDecorator.stripClassPath("../this/may/cause/problems/"));
    }

    @Test
    public void testStripCollection() throws Exception {
        assertEquals("BILL",KlassDecorator.stripCollection("List<BILL>"));
        assertEquals("BILL,MAY",KlassDecorator.stripCollection("Map<BILL,MAY>"));
        assertEquals("String",KlassDecorator.stripCollection("String"));
    }
}