package com.micromall.datacenter.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;

/**
 * Created by Administrator on 2015/6/26.
 */
@Component
public class BarCodeUnit {
    @Autowired
    private ResourceServer resourceServer;
    private final String CODE = "utf-8";
    private final int BLACK = 0xff000000;
    private final int WHITE = 0xFFFFFFFF;

    public String getBarCode(String str, int width, int height, int customerId) throws WriterException, IOException {
        if (width < 200) {
            width = 200;
        }

        if (height < 50) {
            height = 50;
        }

        // ÎÄ×Ö±àÂë
        Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
        hints.put(EncodeHintType.CHARACTER_SET, CODE);

        BitMatrix bitMatrix = new MultiFormatWriter().encode(str, BarcodeFormat.CODE_128, width, height, hints);

        BufferedImage bufferedImage = toBufferedImage(bitMatrix);
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", buffer);

        return resourceServer.saveResource(new ByteArrayInputStream(buffer.toByteArray()), "img.png", customerId, ResourceServer.BarCodeImage);
    }

    /**
     * ×ª»»³ÉÍ¼Æ¬
     *
     * @param matrix
     * @return
     * @author wuhongbo
     */
    private BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_ARGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
            }
        }
        return image;
    }
}
