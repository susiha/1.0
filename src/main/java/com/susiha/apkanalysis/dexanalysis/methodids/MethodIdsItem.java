package com.susiha.apkanalysis.dexanalysis.methodids;

import com.susiha.apkanalysis.dexanalysis.Utils;
import com.susiha.apkanalysis.dexanalysis.protoids.ParameterIds;
import com.susiha.apkanalysis.dexanalysis.protoids.ProtoIdsItem;

import java.util.ArrayList;

/**
 * 基本结构
 * MethodIds{
 *    ushort class_idx,
 *    ushort proto_idx,
 *    iint name_idx
 * }
 */
public class MethodIdsItem {
    public static final int CLASSIDSSIZE = 2;
    public static final int PROTOIDSSIZE = 2;
    public static final int NAMEIDSSIZE = 4;
    //Method所在类的索引 指向TypeIds 2 个字节
    private byte[] classIds;
    //Method的方法原型索引 指向 ProtoIds 2个字节
    private byte[] protoIds;
    //Method的Name索引 指向StringIds 4个字节
    private byte[] nameIds;

    public byte[] getClassIds() {
        return classIds;
    }

    public void setClassIds(byte[] classIds) {
        this.classIds = classIds;
    }

    public byte[] getProtoIds() {
        return protoIds;
    }

    public void setProtoIds(byte[] protoIds) {
        this.protoIds = protoIds;
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

    public String getNameIdsData() {
        return nameIdsData;
    }

    public void setNameIdsData(String nameIdsData) {
        this.nameIdsData = nameIdsData;
    }

    public ProtoIdsItem getProtoIdsItem() {
        return protoIdsItem;
    }

    public void setProtoIdsItem(ProtoIdsItem protoIdsItem) {
        this.protoIdsItem = protoIdsItem;
    }

    //所在类的字符串描述
    private String classIdsData;
    //Name本身的字符串
    private String nameIdsData;
    //ProtoIdsItem
    private ProtoIdsItem protoIdsItem;

    //根据返回值类型 参数类型和Method名字构造method
    public String getMethod(){
        StringBuilder sb = new StringBuilder();
        sb.append(Utils.basicTypeConversion(protoIdsItem.getReturnTypeIdsData()));
        sb.append(" ");
        sb.append(nameIdsData);
        sb.append("(");
        if(protoIdsItem.getParameterItem()!=null&&protoIdsItem.getParameterItem().getRealParameterSize()>0){
           ArrayList<ParameterIds> list = protoIdsItem.getParameterItem().getParameterIds();
            for(int i = 0;i<list.size();i++){
                sb.append(Utils.basicTypeConversion(list.get(i).getParmeterIdsData()));
                sb.append(" ");
                sb.append("arg"+i);
                if(i!=list.size()-1){
                    sb.append(",");
                }
            }
        }
        sb.append(")");
        return sb.toString();
    }

    @Override
    public String toString() {
        return "[class: "+Utils.basicTypeConversion(classIdsData)+",method: "+getMethod()+"]";
    }
}
