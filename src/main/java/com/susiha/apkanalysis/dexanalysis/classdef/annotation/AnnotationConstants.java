package com.susiha.apkanalysis.dexanalysis.classdef.annotation;

/**
 * 注解的常量解析
 */
public class AnnotationConstants {

    public static final int VisibilityBuild = 0x00;     /* annotation visibility */
    public static final int VisibilityRuntime = 0x01;
    public static final int VisibilitySystem = 0x02;


    /**
     * 获取注解的可见性 也就是运行在哪里
     * @param typeValue
     * @return
     */
    public static String getAnnotationVilibleByTypeValue(int typeValue){

        switch (typeValue){
            case VisibilityBuild:
                return "Visibility_Build";
            case VisibilityRuntime:
                return "Visibility_Runtime";
            case VisibilitySystem:
                return "Visibility_System";

        }
        return "Visibility_UnKnow";
    }









}
