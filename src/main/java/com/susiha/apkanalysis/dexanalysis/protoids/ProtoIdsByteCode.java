package com.susiha.apkanalysis.dexanalysis.protoids;

import com.susiha.apkanalysis.dexanalysis.Utils;
import com.susiha.apkanalysis.dexanalysis.stringids.StringIdsItem;
import com.susiha.apkanalysis.dexanalysis.typeIds.TypeIdsItem;
import okio.BufferedSource;

import java.io.IOException;
import java.util.ArrayList;

public class ProtoIdsByteCode {


    /**
     * 解析Proto
     * @param stringIdsItems 需要原型对应的字符串描述
     * @param typeIdsItems 需要类型对应的字符串描述包括参数类型 返回类型
     * @param protoIdsSize 原型的个数
     * @param protoIdsOff 原型的偏移量
     * @return
     * @throws IOException
     */
    public static ArrayList<ProtoIdsItem> decodeProtoIds
           (ArrayList<StringIdsItem> stringIdsItems,
           ArrayList<TypeIdsItem> typeIdsItems,
            int protoIdsSize,
           int protoIdsOff) throws IOException{
        BufferedSource bufferedSource = Utils.getDexBufferedSource();
        bufferedSource.skip(protoIdsOff);
        ArrayList<ProtoIdsItem> protoIdsItems = new ArrayList<>();

        for(int i = 0;i<protoIdsSize;i++){
            ProtoIdsItem item = new ProtoIdsItem();
            //读取原型描述索引字节
            byte[] shortlyIds = bufferedSource.readByteArray(4);
            item.setShortlyIds(shortlyIds);
            //获得原型描述在Stringids中的索引值
            int shortlyIdsIndex = Utils.reverseInt(shortlyIds);
            String shortlyIdsData = stringIdsItems.get(shortlyIdsIndex).getStringItemData().getRealData();
            item.setShortlyIdsData(shortlyIdsData);
            //读取返回类型索引字节
            byte[] returnTypeIds = bufferedSource.readByteArray(4);
            item.setReturnTypeIds(returnTypeIds);
            int returnTypeIdsIndex = Utils.reverseInt(returnTypeIds);
            //获取返回类型描述
            String returnTypeIdsData = typeIdsItems.get(returnTypeIdsIndex).getTypeItemData();
            item.setReturnTypeIdsData(returnTypeIdsData);
            //读取参数类型偏移量
            byte[] parameterOff = bufferedSource.readByteArray(4);
            int parameterOffValue = Utils.reverseInt(parameterOff);
            if(parameterOffValue!=0){ //表示有参数 需要接着读取
                //读取参数个数
                BufferedSource bufferedSourceParameter = Utils.getDexBufferedSource();
                bufferedSourceParameter.skip(parameterOffValue);
                byte[] parameterSize = bufferedSourceParameter.readByteArray(4);
                int parameterSizeValue = Utils.reverseInt(parameterSize);
                //这个判断有点多余
                if(parameterSizeValue!=0){
                    ProtoParameterItem parameterItem = new ProtoParameterItem();
                    parameterItem.setParameterSize(parameterSize);
                    ArrayList<ParameterIds> ParameterIds = new ArrayList<>();
                    Utils.Logger("Test parameterSizeValue "+i,parameterSizeValue);

                    for(int m = 0;m<parameterSizeValue;m++){
                        ParameterIds parameter = new ParameterIds();
                        //读取参数索引
                        byte[] ParameterIdsIndex = bufferedSourceParameter.readByteArray(2);
                        Utils.Logger("Test ParemeterIndex "+i,Utils.byteArrayToHex(ParameterIdsIndex));
                        int ParameterIdsValue =  Utils.reverseInt(ParameterIdsIndex);
                        Utils.Logger("Test ParemeterValue "+i,ParameterIdsValue);

                        parameter.setParmeterIds(ParameterIdsIndex);
                        //读取真实的参数的类型描述字符串
                        String ParameterTypeData = typeIdsItems.get(ParameterIdsValue).getTypeItemData();
                        parameter.setParmeterIdsData(ParameterTypeData);
                        ParameterIds.add(parameter);
                    }
                    parameterItem.setParameterIds(ParameterIds);
                    item.setParameterItem(parameterItem);
                }
            }
            protoIdsItems.add(item);
        }


        return protoIdsItems;
    }

}
