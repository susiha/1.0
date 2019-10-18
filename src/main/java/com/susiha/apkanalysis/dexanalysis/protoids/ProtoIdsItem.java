package com.susiha.apkanalysis.dexanalysis.protoids;

import com.susiha.apkanalysis.dexanalysis.Utils;

/**
 * Proto是方法原型描述
 */
public class ProtoIdsItem {
    //表示指向Stringids中的索引值 获取的是对应的字符串
    //对方法原型的简单描述 4个字节
    private byte[] shortlyIds;

    public byte[] getShortlyIds() {
        return shortlyIds;
    }

    public void setShortlyIds(byte[] shortlyIds) {
        this.shortlyIds = shortlyIds;
    }

    public byte[] getReturnTypeIds() {
        return returnTypeIds;
    }

    public void setReturnTypeIds(byte[] returnTypeIds) {
        this.returnTypeIds = returnTypeIds;
    }

    public byte[] getParameterOff() {
        return parameterOff;
    }

    public void setParameterOff(byte[] parameterOff) {
        this.parameterOff = parameterOff;
    }

    public ProtoParameterItem getParameterItem() {
        return parameterItem;
    }

    public void setParameterItem(ProtoParameterItem parameterItem) {
        this.parameterItem = parameterItem;
    }

    public String getShortlyIdsData() {
        return shortlyIdsData;
    }

    public void setShortlyIdsData(String shortlyIdsData) {
        this.shortlyIdsData = shortlyIdsData;
    }

    public String getReturnTypeIdsData() {
        return returnTypeIdsData;
    }

    public void setReturnTypeIdsData(String returnTypeIdsData) {
        this.returnTypeIdsData = returnTypeIdsData;
    }

    //表示指向TypeIds中的索引,对应的字符串
    //表示的是返回类型的全限量字符串描述 4个字节
    private byte[] returnTypeIds;
    //表示的是获取参数的偏移量 如果是为0 表示没有参数 4个字节
    private byte[] parameterOff;
    //表示是对参数的描述
    private ProtoParameterItem parameterItem;

    //shortlyIds对应的字符串
    private String shortlyIdsData;
    //returnTypeIds对应的字符串
    private String returnTypeIdsData;


    public int getRealShortlyIds() {
        return Utils.reverseInt(shortlyIds);
    }

    public int getRealReturnTypeIds(){
        return Utils.reverseInt(returnTypeIds);
    }

    public int getRealParameterOff(){
        return Utils.reverseInt(parameterOff);
    }



    @Override
    public String toString() {

        StringBuilder method = new StringBuilder();

        method.append(Utils.basicTypeConversion(returnTypeIdsData));
        method.append("(");
        if(parameterItem!=null&&parameterItem.getRealParameterSize()!=0){
            for(int i= 0;i<parameterItem.getParameterIds().size();i++){
                method.append(Utils.basicTypeConversion(parameterItem.getParameterIds().get(i).getParmeterIdsData()));
                if(i!=parameterItem.getParameterIds().size()-1){
                    method.append(",");
                }

            }

        }
        method.append(")");
        return "["+"methodProto: "+shortlyIdsData+", method: "+method.toString()+"]";
    }
}
