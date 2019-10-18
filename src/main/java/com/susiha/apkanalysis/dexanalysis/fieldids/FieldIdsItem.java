package com.susiha.apkanalysis.dexanalysis.fieldids;

import com.susiha.apkanalysis.dexanalysis.Utils;

/**
 * 字段索引Item
 * 基本结构field_id_item{
 *     ushort class_idx,
 *     ushort type_idx,
 *     uint name_idx
 * }
 *
 */
public class FieldIdsItem {

    public static final int CLASSIDSSIZE = 2;
    public static final int TYPEIDSSIZE = 2;
    public static final int NAMEIDSSIZE = 4;


    //字段所属的类 2个字节 指向的是TypeIds中的索引
    private byte[] classIds;
    //字段的类型，2 个字节，指向的是TypeIds中的索引
    private byte[] typeIds;
    //字段的name 4 个字节，指向的是StringIds 中的索引
    private byte[] nameIds;
    //classIds对应的值
    private String classIdsData;

    public byte[] getClassIds() {
        return classIds;
    }

    public void setClassIds(byte[] classIds) {
        this.classIds = classIds;
    }

    public byte[] getTypeIds() {
        return typeIds;
    }

    public void setTypeIds(byte[] typeIds) {
        this.typeIds = typeIds;
    }

    public byte[] getNameIds() {
        return nameIds;
    }

    public void setNameIds(byte[] nameIds) {
        this.nameIds = nameIds;
    }

    public String getClassIdsData() {
        return classIdsData;
    }

    public void setClassIdsData(String classIdsData) {
        this.classIdsData = classIdsData;
    }

    public String getTypeIdsData() {
        return typeIdsData;
    }

    public void setTypeIdsData(String typeIdsData) {
        this.typeIdsData = typeIdsData;
    }

    public String getNameIdsData() {
        return nameIdsData;
    }

    public void setNameIdsData(String nameIdsData) {
        this.nameIdsData = nameIdsData;
    }

    //typeIds对应的额值
    private String typeIdsData;
    //nameIds对应的值
    private String nameIdsData;


    @Override
    public String toString() {
        return "[class: "+ Utils.basicTypeConversion(classIdsData)+
                ", type: "+Utils.basicTypeConversion(typeIdsData)+
                ", name: "+nameIdsData+"]";
    }
}
