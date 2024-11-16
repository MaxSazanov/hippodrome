import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

class MainTest {

    @Test
    @Timeout(22)
    @Disabled
    void whenMainInvoke_thenCompleteWithin22Seconds() throws Exception {
        Main.main(null);
    }
}