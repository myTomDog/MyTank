package util;


import com.mashibing.tank.util.ResourceMgr;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ResourceMgrTest {

    @Test
    public void test() {
        assertNotNull(ResourceMgr.goodTankL);
    }

}
