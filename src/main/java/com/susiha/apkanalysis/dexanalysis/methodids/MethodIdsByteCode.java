package com.susiha.apkanalysis.dexanalysis.methodids;

import com.susiha.apkanalysis.dexanalysis.Utils;
import com.susiha.apkanalysis.dexanalysis.protoids.ProtoIdsItem;
import com.susiha.apkanalysis.dexanalysis.stringids.StringIdsItem;
import com.susiha.apkanalysis.dexanalysis.typeIds.TypeIdsItem;
import okio.BufferedSource;

import java.io.IOException;
import java.util.ArrayList;

public class MethodIdsByteCode {


    /**
     * 解析Method区域字节码
     * @param stringIdsItems
     * @param typeIdsItems
     * @param protoIdsItems
     * @param methodIdsSize
     * @param methodIdsOff
     * @return
     * @throws IOException
     */
    public static ArrayList<MethodIdsItem> decodeMethodIdsItem
            (ArrayList<StringIdsItem> stringIdsItems,
             ArrayList<TypeIdsItem> typeIdsItems,
             ArrayList<ProtoIdsItem> protoIdsItems,
             int methodIdsSize,int methodIdsOff)throws IOException{
        ArrayList<MethodIdsItem> methodIdsItems = new ArrayList<>();
        BufferedSource bufferedSource = Utils.getDexBufferedSource();
        bufferedSource.skip(methodIdsOff);

        for(int i = 0;i<methodIdsSize;i++){
            MethodIdsItem item = new MethodIdsItem();
            //读取所在类的索引并找到对应的字符串
            byte[] classIds = bufferedSource.readByteArray(MethodIdsItem.CLASSIDSSIZE);
            int classIdsIndex = Utils.reverseInt(classIds);
            String classIdsData = typeIdsItems.get(classIdsIndex).getTypeItemData();
            item.setClassIds(classIds);
            item.setClassIdsData(classIdsData);
            //读取Method原型并找到对应的ProtoIdsItem
            byte[] protoIds = bufferedSource.readByteArray(MethodIdsItem.PROTOIDSSIZE);
            int protoIdsIndex = Utils.reverseInt(protoIds);
            item.setProtoIds(protoIds);
            item.setProtoIdsItem(protoIdsItems.get(protoIdsIndex));
            //读取MethodName所在的索引并找到对应的字符串
            byte[] nameIds = bufferedSource.readByteArray(MethodIdsItem.NAMEIDSSIZE);
            int nameIdsIndex = Utils.reverseInt(nameIds);
            String nameIdsData = stringIdsItems.get(nameIdsIndex).getStringItemData().getRealData();
            item.setNameIds(nameIds);
            item.setNameIdsData(nameIdsData);
            methodIdsItems.add(item);
        }
        return methodIdsItems;
    }
}
