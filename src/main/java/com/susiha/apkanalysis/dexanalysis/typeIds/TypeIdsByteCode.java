package com.susiha.apkanalysis.dexanalysis.typeIds;

import com.susiha.apkanalysis.dexanalysis.Utils;
import com.susiha.apkanalysis.dexanalysis.stringids.StringIdsItem;
import okio.BufferedSource;

import java.io.IOException;
import java.util.ArrayList;

public class TypeIdsByteCode {
    /**
     * 获取TypeIds
     * @param typeIdsSize 类型数量
     * @param typeIdsOff 偏移量
     * @param stringIdsItems 字符串索引区
     * @return
     * @throws IOException
     */
    public static ArrayList<TypeIdsItem> decodeTypeIds(ArrayList<StringIdsItem> stringIdsItems, int typeIdsSize, int typeIdsOff) throws IOException{
        BufferedSource bufferedSource = Utils.getDexBufferedSource();
        ArrayList<TypeIdsItem> typeIdsItems =  new ArrayList<>();
        bufferedSource.skip(typeIdsOff);

        for(int i =0;i<typeIdsSize;i++){

            TypeIdsItem idsItem = new TypeIdsItem();
            //读取四字节 是该Type的索引
            byte[] bytes = bufferedSource.readByteArray(4);
            idsItem.setTypeIds(bytes);
            //byteArray转换为索引值
            int byteArrayToIndex =Utils.reverseInt(bytes);
            //获取String对应的byteArray
            ArrayList<Byte>  byteArrayStringData =stringIdsItems.get(byteArrayToIndex).getStringItemData().getStringData();
            String stringData = Utils.byteArrayToString(Utils.byteListToByteArray(byteArrayStringData));
            idsItem.setTypeItemData(stringData);
            typeIdsItems.add(idsItem);
            idsItem.toString();

        }
        return typeIdsItems;
    }

}
