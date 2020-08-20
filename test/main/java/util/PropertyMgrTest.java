package util;

import com.mashibing.tank.util.PropertyMgr;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PropertyMgrTest {

    @Test
    public void testGetValue() {
        assertNotNull(PropertyMgr.getString("initTankCount"));
        assertNotNull(PropertyMgr.getInt("initTankCount"));
    }
}
