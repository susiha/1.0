package com.susiha.apkanalysis.dexanalysis.fieldids;


import com.susiha.apkanalysis.dexanalysis.Utils;
import com.susiha.apkanalysis.dexanalysis.stringids.StringIdsItem;
import com.susiha.apkanalysis.dexanalysis.typeIds.TypeIdsItem;
import okio.BufferedSource;

import java.io.IOException;
import java.util.ArrayList;

public class FieldIdsByteCode {


    /**
     * 解析field区域字节码
     * @param stringIdsItems
     * @param typeIdsItems
     * @param fieldIdsSize
     * @param fieldIdsOff
     * @return
     * @throws IOException
     */
    public static ArrayList<FieldIdsItem> decodeFieldIds
            (ArrayList<StringIdsItem> stringIdsItems, ArrayList<TypeIdsItem> typeIdsItems,
             int fieldIdsSize,int fieldIdsOff) throws IOException{
        ArrayList<FieldIdsItem> idsItems = new ArrayList<>();
        BufferedSource bufferedSource = Utils.getDexBufferedSource();
        bufferedSource.skip(fieldIdsOff);
        for(int i = 0;i< fieldIdsSize;i++){
            FieldIdsItem item = new FieldIdsItem();
            //读取Field所在类的索引
            byte[] classIds = bufferedSource.readByteArray(FieldIdsItem.CLASSIDSSIZE);
            item.setClassIds(classIds);
            int classIdsIndex = Utils.reverseInt(classIds);
            //通过索引找到对应的类的类型的字符串描述
            String classIdsData = typeIdsItems.get(classIdsIndex).getTypeItemData();
            item.setClassIdsData(classIdsData);
            //读取Field类型的索引
            byte[] typeIds = bufferedSource.readByteArray(FieldIdsItem.TYPEIDSSIZE);
            item.setTypeIds(typeIds);
            int typeIdsIndex = Utils.reverseInt(classIds);
            //通过索引找到对应的类型的字符串描述
            String typeIdsData = typeIdsItems.get(typeIdsIndex).getTypeItemData();
            item.setTypeIdsData(typeIdsData);
            //读取Field的名字的索引
            byte[] nameIds = bufferedSource.readByteArray(FieldIdsItem.NAMEIDSSIZE);
            item.setNameIds(nameIds);
            int nameIdsIndex = Utils.reverseInt(nameIds);
            //通过名字索引找到对应的字符串
            String nameIdsData = stringIdsItems.get(nameIdsIndex).getStringItemData().getRealData();
            item.setNameIdsData(nameIdsData);
            idsItems.add(item);
        }
        return idsItems;
    }
}
