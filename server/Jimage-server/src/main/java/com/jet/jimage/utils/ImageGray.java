package com.jet.jimage.utils;

import com.jet.jimage.config.ImageException;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @ClassName: ImageGray
 * @Description: 图像灰化处理
 * @Author: Jet.Chen
 * @Date: 2020/8/26 13:37
 * @Version: 1.0
 **/
@Data
//@NoArgsConstructor
public class ImageGray {

    public static void main(String[] args) {
        try {
            ImageGray imageGray = new ImageGray("D:\\temp\\RGBTest\\RGBTest03.jpg", "D:\\temp\\RGBTest\\RGBTest03-javaUtil.jpg");
//            imageGray.transferImage2GrayByR();
//            imageGray.transferImage2GrayByG();
//            imageGray.transferImage2GrayByB();
//            imageGray.transferImage2GrayByRGBAverage();
//            imageGray.transferImage2GrayByRGBMax();
//            imageGray.transferImage2GrayByRGBMin();
//            imageGray.transferImage2GrayByRGBWeighted();
            imageGray.transferImage2GrayByJavaUtil();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String inputFilePath;

    private String outputFilePath;

    private BufferedImage image;

    public ImageGray(String inputFilePath, String outputFilePath) throws IOException {
        this.inputFilePath = inputFilePath;
        this.outputFilePath = outputFilePath;
        this.image = ImageIO.read(new File(this.inputFilePath));
    }

    /**
    * @Description: 使用 R 值来设置 RGB
    * @Param: []
    * @return: void
    * @Author: Jet.Chen
    * @Date: 2020/8/26 15:41
    */
    public void transferImage2GrayByR() throws IOException {
        this.transferImage2GrayByRGB(1, new File(this.outputFilePath));
    }

    /**
    * @Description: 使用 G 值来设置 RGB
    * @Param: []
    * @return: void
    * @Author: Jet.Chen
    * @Date: 2020/8/26 15:41
    */
    public void transferImage2GrayByG() throws IOException {
        this.transferImage2GrayByRGB(2, new File(this.outputFilePath));
    }

    /**
    * @Description: 使用 B 值来设置 RGB
    * @Param: []
    * @return: void
    * @Author: Jet.Chen
    * @Date: 2020/8/26 15:41
    */
    public void transferImage2GrayByB() throws IOException {
        this.transferImage2GrayByRGB(3, new File(this.outputFilePath));
    }

    /**
    * @Description: 使用 R、G、B 的平均值来设置 RGB
    * @Param: []
    * @return: void
    * @Author: Jet.Chen
    * @Date: 2020/8/26 15:41
    */
    public void transferImage2GrayByRGBAverage() throws IOException {
        this.transferImage2GrayByRGB(4, new File(this.outputFilePath));
    }

    /**
    * @Description: 使用 R、G、B 的最大值来设置 RGB
    * @Param: []
    * @return: void
    * @Author: Jet.Chen
    * @Date: 2020/8/26 16:11
    */
    public void transferImage2GrayByRGBMax() throws IOException {
        this.transferImage2GrayByRGB(5, new File(this.outputFilePath));
    }

    /**
    * @Description: 使用 R、G、B 的最小值来设置 RGB
    * @Param: []
    * @return: void
    * @Author: Jet.Chen
    * @Date: 2020/8/26 16:12
    */
    public void transferImage2GrayByRGBMin() throws IOException {
        this.transferImage2GrayByRGB(6, new File(this.outputFilePath));
    }

    /**
    * @Description: 使用 R、G、B 的加权值来设置 RGB
    * @Param: []
    * @return: void
    * @Author: Jet.Chen
    * @Date: 2020/8/26 16:18
    */
    public void transferImage2GrayByRGBWeighted() throws IOException {
        this.transferImage2GrayByRGB(7, new File(this.outputFilePath));
    }

    /**
    * @Description: 采用 java 本身的灰化类型
    * @Param: []
    * @return: void
    * @Author: Jet.Chen
    * @Date: 2020/8/26 16:44
    */
    public void transferImage2GrayByJavaUtil() throws IOException {
        this.transferImage2GrayByJavaUtil(new File(this.outputFilePath));
    }


    /**
    * @Description: 采用 RGB 中的一种来进行灰化处理
    * @Param: [type, output]
    * ======================
    * type |  说明
    * ----------------------
    * 1    |  R 值
    * ----------------------
    * 2    |  G 值
    * ----------------------
    * 3    |  B 值
    * ----------------------
    * 4    |  R、G、B 的平均值
    * ----------------------
    * 5    |  R、G、B 的最大值
    * ----------------------
    * 6    |  R、G、B 的最小值
    * ----------------------
    * 7    |  R、G、B 的加权值
    * ======================
    * image：图片输出
    * output：图片输出，有三种形式，File、OutputStream、ImageOutputStream
    * @return: void
    * @Author: Jet.Chen
    * @Date: 2020/8/26 15:18
    */
    private void transferImage2GrayByRGB(int type, Object output) {
        try {
            if (this.image == null) throw new ImageException("1400", "Image error.");
            if (type < 1 || type > 7) throw new ImageException("1400", "Type error.");
            // 遍历各个像素，进行替换 R、G、B 的值
            for (int y = this.image.getMinY(); y < this.image.getHeight(); y++) {
                for (int x = this.image.getMinX(); x < this.image.getWidth(); x++) {
                    int pixel = this.image.getRGB(x, y);
                    int[] rgbList = ImageCommonUtil.getRGBList(pixel);
                    int temp;
                    if (4 == type) {
                        // 使用 R、G、B 的平均值来设置 RGB
                        temp = (rgbList[1] + rgbList[2] + rgbList[3]) / 3;
                    } else if (5 == type) {
                        // 使用 R、G、B 的最大值来设置 RGB
                        temp = Math.max(Math.max(rgbList[1], rgbList[2]), rgbList[3]);
                    } else if (6 == type) {
                        // 使用 R、G、B 的最小值来设置 RGB
                        temp = Math.min(Math.min(rgbList[1], rgbList[2]), rgbList[3]);
                    } else if (7 == type) {
                        // 使用 R、G、B 的加权值来设置 RGB
                        temp = (int)(rgbList[1]*0.299 + rgbList[2]*0.587 + rgbList[3]*0.114);
                    } else {
                        temp = rgbList[type];
                    }
                    if (1 != type) {
                        pixel = ((temp<<16) & 0x00ff0000) | (pixel & 0xff00ffff);// 设置r的值
                    }
                    if (2 != type) {
                        pixel = ((temp<<8) & 0x0000ff00) | (pixel & 0xffff00ff);// 设置g的值
                    }
                    if (3 != type) {
                        pixel = (temp & 0x000000ff) | (pixel & 0xffffff00); // 设置b的值
                    }
                    this.image.setRGB(x, y, pixel);
                }
            }
            // 图片输出
            imageOutPut(this.image, output);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ImageException("1500", "IO exception.");
        }
    }

    /**
    * @Description: 采用 java 本身的灰化类型
    * @Param: [output]
    * @return: void
    * @Author: Jet.Chen
    * @Date: 2020/8/26 16:43
    */
    private void transferImage2GrayByJavaUtil(Object output) {
        try {
            if (this.image == null) throw new ImageException("1400", "Image error.");
            int width = this.image.getWidth();
            int height = this.image.getHeight();
            BufferedImage grayImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
            // 遍历各个像素，进行替换 R、G、B 的值
            for (int y = this.image.getMinY(); y < height; y++) {
                for (int x = this.image.getMinX(); x < width; x++) {
                    int pixel = this.image.getRGB(x, y);
                    grayImage.setRGB(x, y, pixel);
                }
            }
            // 图片输出
            imageOutPut(grayImage, output);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ImageException("1500", "IO exception.");
        }
    }

    /**
    * @Description: 图片输出
    * @Param: [image, output]
    * @return: void
    * @Author: Jet.Chen
    * @Date: 2020/8/26 16:44
    */
    private void imageOutPut(BufferedImage image, Object output) throws IOException {
        if (output instanceof File){
            ImageIO.write(image, "jpg", (File)output);
        } else if (output instanceof OutputStream) {
            ImageIO.write(image, "jpg", (OutputStream)output);
        } else if (output instanceof ImageOutputStream) {
            ImageIO.write(image, "jpg", (ImageOutputStream)output);
        } else {
            throw new ImageException("1500", "Output type error.");
        }
    }

}
