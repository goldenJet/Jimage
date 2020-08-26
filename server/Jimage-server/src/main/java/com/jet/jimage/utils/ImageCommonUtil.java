package com.jet.jimage.utils;

/**
 * @ClassName: ImageCommonUtil
 * @Description: 图片处理公共方法类
 * @Author: Jet.Chen
 * @Date: 2020/8/26 13:40
 * @Version: 1.0
 **/
public class ImageCommonUtil {

    /** 
    * @Description: 获取指定像素的 RGB 的值
    * ============================================================
    * 每个像素点其实都是由三元素调配出来的，即每个像素点都是有 RGB 值的
    * R:red  G:green  B:blue
    * ============================================================
    * 像素的表现形式有很多，其中有一种就是用 4个 byte 来展现的，即 4 个 8 位
    * 第一个 byte（最高位的 8 位）是 alpha 值（不一定有）
    * 第二个 byte 是 R 值
    * 第三个 byte 是 G 值
    * 第四个 byte 是 B 值
    * ============================================================
    * 0xff 是用来做滤码的
    * 0xff     代表 1111 1111
    * 0xff00   代表 1111 1111 0000 0000
    * 0xff0000 代表 1111 1111 0000 0000 0000 0000
    * ============================================================
    * 所以，
    * 为了方便理解，我们可以假设 pixel 为 AB CD EF（高位的 alpha 先不看）
    * pixel & 0xff              就是取 EF 的值，即 B 值
    * (pixel & 0xff00) >> 8     就是取 CD 的值，即 G 值
    * (pixel & 0xff0000) >> 16  就是取 AB 的值，即 R 值
    * ============================================================
    * @Param: [pixel] 包含这个像素的颜色信息的值， int型
    * @return: int[] 数组长度为4，分别为 alpha、R、G、B
    * @Author: Jet.Chen 
    * @Date: 2020/8/26 13:55
    */ 
    public static int[] getRGBList(int pixel) {
        int[] rgb = new int[4];
        rgb[0] = (pixel & 0xff000000) >> 24; //alpha
        rgb[1] = (pixel & 0xff0000) >> 16; //r
        rgb[2] = (pixel & 0xff00) >> 8; //g
        rgb[3] = (pixel & 0xff); //b
        return rgb;
    }

}
