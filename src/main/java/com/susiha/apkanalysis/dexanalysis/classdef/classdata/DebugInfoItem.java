package com.susiha.apkanalysis.dexanalysis.classdef.classdata;

import java.util.ArrayList;

/**
 * 每一个debugInfoItem都会定义一个字节码状态机
 * 除了本身的结构之外 后面跟着状态机字节码，最后都是以DBG_END_SEQUENCE字节结尾
 * 基本结构 debug_info_item{
 *     uleb128 line_start; 状态机的line寄存器的初始值 不表示实际的位置条目
 *     uleb128 parameters_size; 已编码的参数名称的数量 每个方法参数都应该有一个名称，但是不包括实例方法的this
 *     uleb128p1[parameters_size] parameter_names;//方法参数名称的字符串索引
 * }
 */
public class DebugInfoItem {
    private int lineStart; //起始位置
    private int parameterSize; //参数个数
    private ArrayList<Integer> parameterNames; //参数名称列表
    private ArrayList<Byte> DebugOpCodes;

    public int getLineStart() {
        return lineStart;
    }

    public void setLineStart(int lineStart) {
        this.lineStart = lineStart;
    }

    public int getParameterSize() {
        return parameterSize;
    }

    public void setParameterSize(int parameterSize) {
        this.parameterSize = parameterSize;
    }

    public ArrayList<Integer> getParameterNames() {
        return parameterNames;
    }

    public void setParameterNames(ArrayList<Integer> parameterNames) {
        this.parameterNames = parameterNames;
    }

    public ArrayList<Byte> getDebugOpCodes() {
        return DebugOpCodes;
    }

    public void setDebugOpCodes(ArrayList<Byte> debugOpCodes) {
        DebugOpCodes = debugOpCodes;
    }
}
