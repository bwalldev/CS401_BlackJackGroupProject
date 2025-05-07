import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)

@SuiteClasses({
	DealerTest.class,
	HandTest.class,
	MessageTest.class,
	LobbyTest.class,
	ShoeTest.class,
	TableTest.class,
	PlayerTest.class,
})

public class AllTests {
	
}
