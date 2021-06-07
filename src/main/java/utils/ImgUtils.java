package utils;

import org.apache.commons.lang3.StringUtils;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Administrator
 */
public class ImgUtils {

    /**
     * 通过图片路径获取其 Base64 码
     *
     * @param imgPath @Not-Null
     * @return img_Base64code
     */
    public static String getImgBase64Code(String imgPath) throws IOException {

        if (StringUtils.isEmpty(imgPath)) {
            return null;
        }

        File file = new File(imgPath);

        FileInputStream fis = null;
        byte[] data = null;
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (fis != null) {
            data = new byte[fis.available()];
            fis.read(data);
            fis.close();
        }

        BASE64Encoder encoder = new BASE64Encoder();
        if (data != null) {
            return encoder.encode(data);
        }

        return null;


    }


}
