package com.susiha.apkanalysis.dexanalysis.classdef.classdata;

public class TryItem {
    private int startAddr;//try覆盖的代码块的起始地址，这个地址是第一个所涵盖指令开头部分的16位代码单元的计数
    private short insnCount; //覆盖的16位代码单元的数量，最后一个代码单元是start_add+insn_count-1
    private short handlerOff; //

    public int getStartAddr() {
        return startAddr;
    }

    public void setStartAddr(int startAddr) {
        this.startAddr = startAddr;
    }

    public short getInsnCount() {
        return insnCount;
    }

    public void setInsnCount(short insnCount) {
        this.insnCount = insnCount;
    }

    public short getHandlerOff() {
        return handlerOff;
    }

    public void setHandlerOff(short handlerOff) {
        this.handlerOff = handlerOff;
    }


}
