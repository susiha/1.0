package com.susiha.apkanalysis.dexanalysis.protoids;

import com.susiha.apkanalysis.dexanalysis.Utils;

/**
 * 参数索引Item
 */
public class ParameterIds {


    public byte[] getParmeterIds() {
        return parmeterIds;
    }

    public void setParmeterIds(byte[] parmeterIds) {
        this.parmeterIds = parmeterIds;
    }

    public String getParmeterIdsData() {
        return parmeterIdsData;
    }

    public void setParmeterIdsData(String parmeterIdsData) {
        this.parmeterIdsData = parmeterIdsData;
    }

    //表示的是参数的索引,占两个字节 指向的是TypeIds
    private byte[] parmeterIds;
    //表示parmeterIds对应的字符串
    private String parmeterIdsData;

    public int getRealParmeterIds(){
        return Utils.reverseInt(parmeterIds);
    }

}
