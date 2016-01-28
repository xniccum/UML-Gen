import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import IteratorToEnumerationAdapterTest;
import LinearTransformerTest;

@RunWith(Suite.class)
@SuiteClasses({
	IteratorToEnumerationAdapterTest.class,
	LinearTransformerTest.class
})
public class AllTests {

}
