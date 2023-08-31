package com.example.dailyTarget.util.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: bigDragon
 * @create: 2022/1/12
 * @Description:
 *      系统原本的Excel注解无法满足合并单元格功能
 *      标注Excel注解的实体类属性先后顺序既为excel导出字段顺序
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Excel {

    //导入时，对应excel的字段，主要是用户区分每个字段，不能有annocation重名
    //导出是的列明，导出排序跟定义了annotation的字段有关
    String exportName ();

    //导出时再excel中每个列的宽，单位为字符，一个汉字两个字符
    //如 以列名内容中较适合的长度，例如姓名为列6【姓名一般为3个字】 性别列4【男女占1，但列名占两个字】
    //限制1-255
    int exportFieldWidth () default 15;

    //导入数据是否需要转化 及 对已有的excel，是否需要将字段转为对应的数据
    //若是sign为1，则需要再pojo中加入 void set字段名Convert（String text）
    int importConvertSign () default 0;

    /**
     * 合并单元格标志
     * <p>
     * 若是sign为1,开启合并单元格，将相同的行合并为同一个单元格。
     * 当上一个字段也需合并单元格时，认为上个单元格为此单元格父类，会根据父单元格进行合并
     * <p>
     * 若是sign为0，不开启合并单元格功能
     * 值为2时，为扩展格，代表不仅需要合并单元格还和之前一格同时合并
     */
    int mergeCellSign () default 0;

    /**
     * 自定义格式
     */
    String selfFormat () default "";


}
