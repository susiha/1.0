package com.susiha.apkanalysis.dexanalysis.classdef.classdata;

import java.util.ArrayList;

/**
 * struct DexCode {
 *     u2  registersSize;
 *     u2  insSize;
 *     u2  outsSize;
 *     u2  triesSize;
 *     u4  debugInfoOff;       /* file offset to debug info stream
 *     u4 insnsSize;          /* size of the insns array, in u2 units
 *     u2 insns[1];
 *       // followed by optional u2 padding
 *       // followed by try_item[triesSize]
 *       // followed by catch_handler_item[handlersSize]
 *};
 *4
 */
public class CodeItem {
    private short registersSize; //此代码使用寄存器的数量
    private short insSize; // 传入参数的个数
    private short outsSize; //出参个数
    private short triesSize;//tryItem数量
    private int debugInfoOff; //调试信息(行号+局部变量信息)的偏移量 如果有指向debug_info_item
    private int insnsSize; //指令列表的大小
    private ArrayList<Short> insns;//指令列表
    private short padding;//字节填充，只有triesSize为非0且insnsSize是奇数时，这个元素才会存在
    private ArrayList<TryItem> tries;
    private EncodedCatchHandlerList handlers;
    private DebugInfoItem debugInfoItem;

    public short getRegistersSize() {
        return registersSize;
    }

    public void setRegistersSize(short registersSize) {
        this.registersSize = registersSize;
    }

    public short getInsSize() {
        return insSize;
    }

    public void setInsSize(short insSize) {
        this.insSize = insSize;
    }

    public short getOutsSize() {
        return outsSize;
    }

    public void setOutsSize(short outsSize) {
        this.outsSize = outsSize;
    }

    public short getTriesSize() {
        return triesSize;
    }

    public void setTriesSize(short triesSize) {
        this.triesSize = triesSize;
    }

    public int getDebugInfoOff() {
        return debugInfoOff;
    }

    public void setDebugInfoOff(int debugInfoOff) {
        this.debugInfoOff = debugInfoOff;
    }

    public int getInsnsSize() {
        return insnsSize;
    }

    public void setInsnsSize(int insnsSize) {
        this.insnsSize = insnsSize;
    }

    public ArrayList<Short> getInsns() {
        return insns;
    }

    public void setInsns(ArrayList<Short> insns) {
        this.insns = insns;
    }

    public short getPadding() {
        return padding;
    }

    public void setPadding(short padding) {
        this.padding = padding;
    }

    public ArrayList<TryItem> getTries() {
        return tries;
    }

    public void setTries(ArrayList<TryItem> tries) {
        this.tries = tries;
    }

    public EncodedCatchHandlerList getHandlers() {
        return handlers;
    }

    public void setHandlers(EncodedCatchHandlerList handlers) {
        this.handlers = handlers;
    }

    public DebugInfoItem getDebugInfoItem() {
        return debugInfoItem;
    }

    public void setDebugInfoItem(DebugInfoItem debugInfoItem) {
        this.debugInfoItem = debugInfoItem;
    }
}
