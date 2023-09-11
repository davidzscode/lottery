package com.qihang.common.util.code;

import cn.hutool.extra.qrcode.QrConfig;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * 二维码工具类型
 *
 * @author
 * @since 2021-11-15
 */
public class QrCodeUtil {

    private static final String FORMAT_NAME = "png";

    public static InputStream generate(String uid) {
        InputStream is = null;
        try {
            //生成名片二维码并上传到s3
            QrConfig config = new QrConfig();
            // 设置边距，既二维码和背景之间的边距
            config.setMargin(3);
            // 设置前景色，既二维码颜色（青色）
            //config.setForeColor(Color.CYAN);
            // 设置背景色（灰色）
            //config.setBackColor(Color.GRAY);
            // 高纠错级别
            config.setErrorCorrection(ErrorCorrectionLevel.H);
            BufferedImage image = cn.hutool.extra.qrcode.QrCodeUtil.generate(String.valueOf(uid), config);
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(image, FORMAT_NAME, os);
            is = new ByteArrayInputStream(os.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return is;
    }
}
