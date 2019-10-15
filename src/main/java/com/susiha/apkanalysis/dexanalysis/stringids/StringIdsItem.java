package com.susiha.apkanalysis.dexanalysis.stringids;

import com.susiha.apkanalysis.dexanalysis.Utils;

/**
 * StringIdsItem的结构包含一个索引和数据区，数据区是根据索引得到的
 */
public class StringIdsItem {
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    // String 所在的索引
    private int index;
    //四个字节
    private int stringDataOff;
    // string 结构 包括uleb128表示的大小 和 ArrayList<Byte> 表示的字符串
    private StringItemData stringItemData;

    public int getStringDataOff() {
        return stringDataOff;
    }

    public void setStringDataOff(int stringDataOff) {
        this.stringDataOff = stringDataOff;
    }

    public StringItemData getStringItemData() {
        return stringItemData;
    }

    public void setStringItemData(StringItemData stringItemData) {
        this.stringItemData = stringItemData;
    }

    @Override
    public String toString() {
        return "[ index: "+index+",offSet: "+stringDataOff+",stringDataSize: "+
                stringItemData.getSize()+",originalByteArray: "+Utils.byteArrayToHex(stringItemData.getOriginalByteArray())+"\n"
                +",StringDataHex: "+Utils.byteArrayToHex(stringItemData.getStringData())+"\n"
                +"StringData: "+Utils.byteArrayToString(Utils.byteListToByteArray(stringItemData.getStringData()))+"]"
                ;
    }






}
