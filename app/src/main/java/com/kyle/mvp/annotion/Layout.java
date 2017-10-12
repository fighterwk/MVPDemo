package com.kyle.mvp.annotion;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2017/10/12
 */
// 注解可以为运行时
@Retention(RetentionPolicy.RUNTIME)
public @interface Layout {

    int layoutResId() default -1;
}
