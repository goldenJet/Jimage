package com.jet.jimage.config;

import lombok.Data;

/**
 * @ClassName: ImageException
 * @Description: 图像处理异常
 * @Author: Jet.Chen
 * @Date: 2020/8/26 14:50
 * @Version: 1.0
 **/
@Data
public class ImageException extends RuntimeException {
    private String code;
    private String desc;

    public ImageException() {
        super();
    }

    public ImageException(String codo, String desc) {
        super();
        this.code = codo;
        this.desc = desc;
    }
}
