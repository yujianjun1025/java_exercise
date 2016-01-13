package imageTest;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: jianjun.yu
 * Date: 14-11-24
 * Time: 下午3:50
 * To change this template use File | Settings | File Templates.
 */
public class BufferedImageTest {


    public static BufferedImage scaleImage(BufferedImage image, int scaleTimes,
                                           Object hint, boolean isMultiple) {
        // type = (image.getTransparency() == Transparency.OPAQUE) ?
        // BufferedImage.TYPE_INT_RGB
        // : BufferedImage.TYPE_INT_ARGB;
        int type = image.getType();

        int w = image.getWidth();
        int h = image.getHeight();
        int targetWidth = w / scaleTimes;
        int targetHight = h / scaleTimes;

        if (isMultiple) {
            int count = 0;
            do {
                w /= 2;
                if (w > targetWidth) {
                    w = targetWidth;
                }

                h /= 2;
                if (h > targetHight) {
                    h = targetHight;
                }

                BufferedImage tmp = new BufferedImage(w, h, type);
                Graphics2D graphics2D = tmp.createGraphics();
                graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                        hint);
                graphics2D.drawImage(image, 0, 0, w, h, null);
                image = tmp;
                count++;
            } while (w != targetWidth || h != targetHight);

            System.out.println("分步骤次数: " + count);
            return image;
        } else {
            BufferedImage tmp = new BufferedImage(targetWidth, targetHight,
                    type);
            Graphics2D graphics2D = tmp.createGraphics();
            graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    hint);
            graphics2D.drawImage(image, 0, 0, targetWidth, targetHight, null);
            return tmp;
        }
    }

    public static void productImage(String inputPath, String outputPath, int
            scaleTimes, Object hint,
                                    boolean isMultiple, String fileSuffix) throws Exception {
        File file = new File(inputPath);
        BufferedImage image = ImageIO.read(file);
        BufferedImage destImage = scaleImage(image, scaleTimes, hint,
                isMultiple);
        ImageIO.write(destImage, "jpg", new File(outputPath + fileSuffix +
                file.getName()));
    }

    public static void scaleImageByGetScaleImageInstance(String inputPath,
                                                         String outputPath, int scaleTimes, int type,
                                                         String fileSuffix) throws Exception {
        File file = new File(inputPath);
        BufferedImage bufferedImage = ImageIO.read(file);
        int targetWidth = bufferedImage.getWidth() / scaleTimes;
        int targetHight = bufferedImage.getHeight() / scaleTimes;
        Image image = bufferedImage.getScaledInstance(targetWidth,
                targetHight, type);
        ImageIO.write(convert2BufferedImage(image, bufferedImage.getType()),
                "jpg", new File(outputPath + fileSuffix
                + file.getName()));
    }

    private static  String intputPath = "/root/picture/1.jpg";
    private static  String outputPath = "/root/picture/2.jpg";

    public static void main(String[] args) throws Exception {

        scaleImageByGetScaleImageInstance(intputPath, outputPath, 6,
                Image.SCALE_SMOOTH, "only_image_hint_smooth_");
        scaleImageByGetScaleImageInstance(intputPath, outputPath, 6,
                Image.SCALE_AREA_AVERAGING,
                "only_image_hint_area_averaging_");
        scaleImageByGetScaleImageInstance(intputPath, outputPath, 6,
                Image.SCALE_FAST, "only_image_hint_fast_");

        productImage(intputPath, outputPath, 6,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR, false,
                "only_renderinghint_bilinear_");

        productImage(intputPath, outputPath, 6,
                RenderingHints.VALUE_INTERPOLATION_BICUBIC, false,
                "only_renderinghint_bicubic_");

        productImage(intputPath, outputPath, 6,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR, true,
                "multiple_renderinghint_bilinear_");
        productImage(intputPath, outputPath, 6,
                RenderingHints.VALUE_INTERPOLATION_BICUBIC, true,
                "multiple_renderinghint_bicubic_");
        //
        // File file = new File(intputPath);
        // BufferedImage sourceBufferedImage = ImageIO.read(file);
        // BufferedImage destBufferedImage =
        // scaleImageForBest(sourceBufferedImage, 6,
        // RenderingHints.VALUE_INTERPOLATION_BILINEAR, false);
        // ImageIO.write(destBufferedImage, "jpg", new File(outputPath +
        // "_for_best_" + file.getName()));
        //
        // test_SCALE_SMOOTH_multistep(intputPath, outputPath, true,
        // "best_test_1",
        // RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR, 6);
        // test_SCALE_SMOOTH_multistep(intputPath, outputPath, true,
        // "best_test_2",
        // RenderingHints.VALUE_INTERPOLATION_BILINEAR, 6);
        // test_SCALE_SMOOTH_multistep(intputPath, outputPath, true,
        // "best_test_3",
        // RenderingHints.VALUE_INTERPOLATION_BICUBIC, 6);
        // for (Map.Entry<String, String> entry : scaleCodeDesc.entrySet()) {
        // testImage(intputPath, outputPath, Integer.valueOf(entry.getKey()),
        // 6);
        // }
        // testGraphics2D(intputPath, outputPath,
        // RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED,
        // "1",
        // 6);
        // testGraphics2D(intputPath, outputPath,
        // RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY,
        // "2",
        // 6);
        // testGraphics2D(intputPath, outputPath,
        // RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_DEFAULT,
        // "3",
        // 6);
        //
        // testGraphics2D(intputPath, outputPath,
        // RenderingHints.KEY_INTERPOLATION,
        // RenderingHints.VALUE_INTERPOLATION_BILINEAR, "4", 6);
        // testGraphics2D(intputPath, outputPath,
        // RenderingHints.KEY_INTERPOLATION,
        // RenderingHints.VALUE_INTERPOLATION_BICUBIC, "5", 6);
        // testGraphics2D(intputPath, outputPath,
        // RenderingHints.KEY_INTERPOLATION,
        // RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR, "6", 6);

    }

    public static BufferedImage convert2BufferedImage(Image image, int type) {
        if (image instanceof BufferedImage) {
            return (BufferedImage) image;
        }

        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null),
                image.getHeight(null), type);
        Graphics graphics = bufferedImage.getGraphics();
        graphics.drawImage(image, 0, 0, null);
        graphics.dispose();
        return bufferedImage;
    }
}
