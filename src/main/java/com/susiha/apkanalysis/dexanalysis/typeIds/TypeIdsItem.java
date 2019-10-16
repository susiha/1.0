package com.susiha.apkanalysis.dexanalysis.typeIds;

import com.susiha.apkanalysis.dexanalysis.Utils;

/**
 * TypeIdsItem 的结构
 */
public class TypeIdsItem {
    private String typeItemData;//类型的全限量字符串
    private byte[] typeIds; //在StringIds中的索引

    public String getTypeItemData() {
        return typeItemData;
    }

    public void setTypeItemData(String typeItemData) {
        this.typeItemData = typeItemData;
    }

    public byte[] getTypeIds() {
        return typeIds;
    }

    public void setTypeIds(byte[] typeIds) {
        this.typeIds = typeIds;
    }

    @Override
    public String toString() {
        return "[index: "+ Utils.byteArrayToHex(typeIds)+"Type: " +typeItemData+"]";
    }
}
