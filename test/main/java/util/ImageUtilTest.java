package util;

import com.mashibing.tank.util.ImageUtil;
import com.mashibing.tank.util.ResourceMgr;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

class ImageUtilTest {

	@Test
	void testRotateImage() throws IOException {
        BufferedImage tankL = ImageUtil.rotateImage(ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankL.gif")), 90);
        Assertions.assertNotNull(tankL);
	}

}
