package com.susiha.apkanalysis.dexanalysis.common;

import com.susiha.apkanalysis.dexanalysis.Utils;
import java.util.ArrayList;

/**
 * 表示类型的的列表
 * 基本结构
 *  struct DexTypeList {
 *    u4  size;
 *    DexTypeItem list[1];
 *   };
 */
public class DexTypeList {
    public static final int BYTESIZE = 4;
    //表示Type的个数 4个字节
    private byte[] size;
    private ArrayList<DexTypeItem> dexTypeLists;

    public byte[] getSize() {
        return size;
    }

    public void setSize(byte[] size) {
        this.size = size;
    }

    public ArrayList<DexTypeItem> getDexTypeLists() {
        return dexTypeLists;
    }

    public void setDexTypeLists(ArrayList<DexTypeItem> dexTypeLists) {
        this.dexTypeLists = dexTypeLists;
    }
    public int getRealSize(){
        return Utils.reverseInt(size);
    }
}
