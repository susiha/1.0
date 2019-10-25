package com.susiha.apkanalysis.dexanalysis.classdef.classdata;

import com.susiha.apkanalysis.dexanalysis.Utils;
import okio.BufferedSource;

import java.io.IOException;
import java.util.ArrayList;

public class DebugOpCode {

    //无参数 表示终止code_item的调试信息序列
    public static final int DBG_END_SEQUENCE = 0x00;
    /**
     *  参数 uleb128 addr_diff 添加到地址寄存器的数量
     *  表示使地址寄存器指向下一个置底，而不发出位置条目
     */
    public static final int DBG_ADVANCE_PC = 0x01;
    /**
     * 参数 sleb128 line_diff 要更改的行寄存器数量
     * 表示使行寄存器指向下一个地址 而不发出位置条目
     */
    public static final int DBG_ADVANCE_LINE = 0x02;
    /**
     * 参数 uleb128 register_num 包含本地变量的寄存器
     *     uleb128p1 name_idx  名称的字符串索引
     *     uleb128p1 type_idx  类型索引
     *  表示在当前地址中引入一个本地变量 name_idx和type_idx都可能是NO_index,用于表示该值是未知的
     */
    public static final int DBG_START_LOCAL = 0x03;
    /**
     * 参数 uleb128 register_num 包含本地变量的寄存器
     *     uleb128p1 name_idx  名称的字符串索引
     *     uleb128p1 type_idx  类型索引
     *     uleb128p1 sig_idx  该类型签名的字符串索引
     *  表示在当前地址中引入一个带有类型签名的本地变量 name_idx和type_idx，sig_idx都可能是NO_index,用于表示该值是未知的
     *
     */
    public static final int DBG_START_LOCAL_EXTENDED = 0x04;
    /**
     *  参数： uleb128 register_num 包含本地变量的寄存器
     *  表示 在当前地址中将当前存在的本地变量标记为超出范围
     */
    public static final int DBG_END_LOCAL = 0x05;

    /**
     * 参数： uleb128 register_num 要重新启动的寄存器
     * 表示在当前地址中重新引入一个本地变量，名称和类型与指定寄存器中存在的最后一个变量相同
     */
    public static final int DBG_RESTART_LOCAL = 0x06;

    /**
     * 无参数
     * 设置prologue_end状态机寄存器，表示所添加的下一个位置条目赢被视为方法前序的结尾
     * prologue_end寄存器已被任何特殊的(>= 0x0a)操作码清除
     */
    public static final int DBG_SET_PROLOGUE_END = 0x07;

    /**
     * 无参数
     * 设置epilogue_begin状态机寄存器
     * 表示所添加的下一个位置条目应被视为方法结尾的开头
     * epilogue_begin 寄存器一杯任何特殊的（>=0x0a）操作码清除
     */
    public static final int DBG_SET_EPILOGUE_BEGIN = 0x08;

    /**
     * 参数 uleb128p1 name_idx 源文件名称的字符串索引 如果未知则是NO_INDEX
     * 表示所有后续行号条目均引用源文件名称
     */
    public static final int DBG_SET_FILE = 0x09;


    /**
     * 获取DebugInfoItem的状态码
     * @param bufferedSource
     * @return
     * @throws IOException
     */
    public static ArrayList<Byte> getDebugOpCodeArray(BufferedSource bufferedSource) throws IOException {


        ArrayList<Byte> bytes = new ArrayList<>();
        byte b;
        //如果调试信息没有结束，就继续读下去
        while((b = bufferedSource.readByte())!=DBG_END_SEQUENCE){
            //首先添加该类型
            bytes.add(b);
            //添加参数
            switch (b){
                case DBG_ADVANCE_PC: //添加参数addr_diff
                case DBG_ADVANCE_LINE://添加参数line_diff
                case DBG_SET_FILE:   //name_idx
                case DBG_RESTART_LOCAL:  //register_num
                case DBG_END_LOCAL:  //register_num
                    addOpCodParameters(bytes,1,bufferedSource);
                    break;
                case DBG_START_LOCAL:
                    //register_num,name_idx,type_idx
                    addOpCodParameters(bytes,3,bufferedSource);
                    break;
                case DBG_START_LOCAL_EXTENDED:
                    //register_num,name_idx,type_idx,sig_idx
                    addOpCodParameters(bytes,4,bufferedSource);
                    break;
                case DBG_SET_PROLOGUE_END:
                case DBG_SET_EPILOGUE_BEGIN:
                    addOpCodParameters(bytes,0,bufferedSource);
                    break;
            }
        }
        //把结尾处的DBG_END_SEQUENCE添加进入
        if(b == DBG_END_SEQUENCE){
            bytes.add(b);
        }
        return bytes;
    }


    /**
     * 根据DebugOpCode参数个数添加对应的字节数到target
     * @param target 目标Array
     * @param parameterSize 参数个数
     * @param bufferedSource 字节源
     */
    public static void addOpCodParameters(ArrayList<Byte> target,int parameterSize,
                                          BufferedSource bufferedSource)throws IOException {

        if(parameterSize>0) {
            for(int i=0;i<parameterSize;i++){
                //有几个参数就添加几次
                target.addAll(Utils.readLeb128(bufferedSource));
            }
        }
    }




}
