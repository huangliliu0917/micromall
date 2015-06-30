import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.micromall.datacenter.service.delivery.impl.BarCodeServiceImpl;
import com.micromall.datacenter.utils.BarCodeUnit;
import com.micromall.datacenter.utils.ResourceServer;
import com.micromall.datacenter.utils.impl.LocalResourceServer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by Administrator on 2015/6/26.
 */
public class MainTest {
    public static void main(String[] args) {
//        BarCodeServiceImpl service = new BarCodeServiceImpl();
//        List<String> mainList = service.createMainCode(100);
//        for (String mainCode : mainList) {
//            System.out.println("mainCode:" + mainCode);
//            List<String> subList = service.createSubCode(mainCode, 20);
//            for (String subCode : subList) {
//                //System.out.println("subCode:" + subCode);
//            }
//        }
//        try {
//            Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
//            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
//
//            BitMatrix bitMatrix = new MultiFormatWriter().encode("157591750767738", BarcodeFormat.CODE_128, 200, 50, hints);
//
//            BufferedImage bufferedImage = BarCodeUnit.toBufferedImage(bitMatrix);
//            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
//            ImageIO.write(bufferedImage, "png", buffer);
//            LocalResourceServer resourceServer = new LocalResourceServer();
//            resourceServer.saveResource(new ByteArrayInputStream(buffer.toByteArray()), "img.png", 5, ResourceServer.BarCodeImage);
//        } catch (WriterException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(9090);
            System.out.println("1232");
            serverSocket.accept();
            System.out.println("12111");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
