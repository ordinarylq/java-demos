import org.junit.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @author qili
 * @create 2022-08-28-11:12
 */
public class TimeTests {
    @Test
    public void testZonedTime() {
        ZonedDateTime now = ZonedDateTime.now();
        System.out.println(now);
        // 2022-08-28T11:14:01.079+08:00[GMT+08:00]
        ZonedDateTime now1 = ZonedDateTime.now(ZoneId.of("Asia/Shanghai"));
        System.out.println(now1);
        // 2022-08-28T11:15:02.065+08:00[Asia/Shanghai]
    }
}
