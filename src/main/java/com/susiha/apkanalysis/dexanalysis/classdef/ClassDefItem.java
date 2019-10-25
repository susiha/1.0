package com.susiha.apkanalysis.dexanalysis.classdef;

import com.susiha.apkanalysis.dexanalysis.Utils;
import com.susiha.apkanalysis.dexanalysis.classdef.annotation.*;
import com.susiha.apkanalysis.dexanalysis.classdef.classdata.ClassDataItem;
import com.susiha.apkanalysis.dexanalysis.classdef.classdata.EncodedField;
import com.susiha.apkanalysis.dexanalysis.common.DexTypeList;
import com.susiha.apkanalysis.dexanalysis.common.EncodedValue;
import com.susiha.apkanalysis.dexanalysis.fieldids.FieldIdsItem;
import com.susiha.apkanalysis.dexanalysis.methodids.MethodIdsItem;
import com.susiha.apkanalysis.dexanalysis.stringids.StringIdsItem;
import com.susiha.apkanalysis.dexanalysis.typeIds.TypeIdsItem;

import java.util.ArrayList;

/**
 * 基本结构
 * struct DexClassDef {
 *    u4 classIdx;        描述具体的class类型，指向TypeIds,不能是基本类型或者是数组类型
 *    u4 accessFlags;     访问类型
 *    u4 superclassIdx;   父类类型，约束同class_idx
 *    u4 interfacesOff;   接口的偏移量 如果为0  表示没有实现的接口
 *    u4 sourceFileIdx;   表示源代码文件的信息，指向StringIds,0xFFFFFFFF 表示没有找到索引
 *    u4 annotationsOff;  注解的偏移量
 *    u4 classDataOff;    classData 的偏移量
 *    u4 staticValuesOff; 静态数据的偏移量
 * };
 */
public class ClassDefItem {
    private int classIdx;
    private int accessFlags;
    private int superclassIdx;
    private int interfacesOff;
    private int sourceFileIdx;
    private int annotationsOff;
    private int classDataOff;
    private int staticValuesOff;
    private DexTypeList interfaceList;
    private AnnotationDirectoryItem annotationDirectoryItem;
    private ClassDataItem classDataItem;
    private ArrayList<TypeIdsItem> typeIdsItems;
    private ArrayList<StringIdsItem> stringIdsItems;
    private ArrayList<MethodIdsItem> methodIdsItems;
    private ArrayList<FieldIdsItem> fieldIdsItems;

    public ArrayList<FieldIdsItem> getFieldIdsItems() {
        return fieldIdsItems;
    }

    public void setFieldIdsItems(ArrayList<FieldIdsItem> fieldIdsItems) {
        this.fieldIdsItems = fieldIdsItems;
    }

    public ArrayList<MethodIdsItem> getMethodIdsItems() {
        return methodIdsItems;
    }

    public void setMethodIdsItems(ArrayList<MethodIdsItem> methodIdsItems) {
        this.methodIdsItems = methodIdsItems;
    }
    public ArrayList<TypeIdsItem> getTypeIdsItems() {
        return typeIdsItems;
    }

    public void setTypeIdsItems(ArrayList<TypeIdsItem> typeIdsItems) {
        this.typeIdsItems = typeIdsItems;
    }

    public ArrayList<StringIdsItem> getStringIdsItems() {
        return stringIdsItems;
    }

    public void setStringIdsItems(ArrayList<StringIdsItem> stringIdsItems) {
        this.stringIdsItems = stringIdsItems;
    }

    public int getClassIdx() {
        return classIdx;
    }

    public void setClassIdx(int classIdx) {
        this.classIdx = classIdx;
    }

    public int getAccessFlags() {
        return accessFlags;
    }

    public void setAccessFlags(int accessFlags) {
        this.accessFlags = accessFlags;
    }

    public int getSuperclassIdx() {
        return superclassIdx;
    }

    public void setSuperclassIdx(int superclassIdx) {
        this.superclassIdx = superclassIdx;
    }

    public int getInterfacesOff() {
        return interfacesOff;
    }

    public void setInterfacesOff(int interfacesOff) {
        this.interfacesOff = interfacesOff;
    }

    public int getSourceFileIdx() {
        return sourceFileIdx;
    }

    public void setSourceFileIdx(int sourceFileIdx) {
        this.sourceFileIdx = sourceFileIdx;
    }

    public int getAnnotationsOff() {
        return annotationsOff;
    }

    public void setAnnotationsOff(int annotationsOff) {
        this.annotationsOff = annotationsOff;
    }

    public int getClassDataOff() {
        return classDataOff;
    }

    public void setClassDataOff(int classDataOff) {
        this.classDataOff = classDataOff;
    }

    public int getStaticValuesOff() {
        return staticValuesOff;
    }

    public void setStaticValuesOff(int staticValuesOff) {
        this.staticValuesOff = staticValuesOff;
    }

    public DexTypeList getInterfaceList() {
        return interfaceList;
    }

    public void setInterfaceList(DexTypeList interfaceList) {
        this.interfaceList = interfaceList;
    }

    public AnnotationDirectoryItem getAnnotationDirectoryItem() {
        return annotationDirectoryItem;
    }

    public void setAnnotationDirectoryItem(AnnotationDirectoryItem annotationDirectoryItem) {
        this.annotationDirectoryItem = annotationDirectoryItem;
    }

    public ClassDataItem getClassDataItem() {
        return classDataItem;
    }

    public void setClassDataItem(ClassDataItem classDataItem) {
        this.classDataItem = classDataItem;
    }

    public String getClassAccess(int accessFlags){
        String name = AccessFlag.getClassAccessFlag(accessFlags);

        //如果是接口 注解 或者是枚举 后面不在添加class
        if(name.contains("interface")||name.contains("@interface")||name.contains("enum")){
            return name;
        }
        return name+"class ";
    }


    /**
     * 实现的接口列表
     * @return
     */
    public String getInterface(){
        if(interfacesOff ==0){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(" implements ");
        for(int i = 0;i<interfaceList.getSize();i++){
            String interfaceName =Utils.basicTypeConversion(typeIdsItems.get(interfaceList.getDexTypeLists().get(i).getTypeIdx()).getTypeItemData());
            sb.append(interfaceName);
            if(i!=interfaceList.getSize()-1){
                sb.append(",");
            }
        }
        return sb.toString();
    }

    public String getClassName(){

        String sourfileName =stringIdsItems.get(sourceFileIdx).getStringItemData().getRealData();
        if(sourfileName.contains(".")){
            return sourfileName.substring(0,sourfileName.lastIndexOf("."));
        }
        return sourfileName;
    }


    /**
     * 获取对应代码中的显示效果
     * @param source
     * @return
     */
    private String getClassName(String source){
        String data = Utils.basicTypeConversion(source);
        if(data.contains("/")){
            return data.replace("/",".");
        }
        return data;
    }



    public String getSuperClassName(){

        String superClass =typeIdsItems.get(superclassIdx).getTypeItemData();
        String result = Utils.basicTypeConversion(superClass);

        if(result.contains("/")){
            return result.replace("/",".");
        }
        return result;
    }



    @Override
    public String toString() {
        return generString();
    }


    private String generString(){
        int spaceNum = 2;
        StringBuilder sb = new StringBuilder();
        sb.append(generSpace(spaceNum)).append(getClassAccess(accessFlags)).append(getClassName()).append(" extends ").append(getSuperClassName()).append(getInterface()).append("{\n");
        sb.append(generAnnotationInfo(spaceNum)).append("\n");
        sb.append(getClassDataInfo(spaceNum));
        sb.append("\n}");
        return sb.toString();
    }


    /**
     * 注解的基本信息，缩进方便查看
     * @param spaceNum
     * @return
     */
    private String generAnnotationInfo(int spaceNum){

        spaceNum = spaceNum+4;
        //没有注解
        if(annotationsOff==0){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int classAnnotationOff =annotationDirectoryItem.getClassAnnotationsOff();
        sb.append(generSpace(spaceNum)).append("ClassAnnotationSize: ");
        if(classAnnotationOff ==0){
            sb.append(0);
        }else{
            sb.append(generAnnotationSetItemInfo(spaceNum,annotationDirectoryItem.getClassAnnotation()));
        }
        sb.append("\n");
        sb.append(generSpace(spaceNum)).append("FieldSize: ");
        if(annotationDirectoryItem.getFieldsSize()==0){
            sb.append(0);
        }else{
            sb.append(annotationDirectoryItem.getFieldsSize()).append(" , {");
            for(int i = 0;i<annotationDirectoryItem.getFieldAnnotationItems().size();i++){
               FieldAnnotationItem item = annotationDirectoryItem.getFieldAnnotationItems().get(i);
               sb.append("\n").append(generSpace(spaceNum+4)).append("{ index: ").append(i).append(" , ");
               sb.append(" Type: ").append(fieldIdsItems.get(item.getFieldIds()).getTypeIdsData()).append(" , ");
               sb.append(fieldIdsItems.get(item.getFieldIds()).getNameIdsData()).append(": ").append(generAnnotationSetItemInfo(spaceNum+8,item.getFileAnnotationsItem()));
               sb.append(" } ");
               if(i!=annotationDirectoryItem.getFieldAnnotationItems().size()-1){
                   sb.append(" , ");
               }
            }
            sb.append(" }");
        }

        sb.append("\n");
        sb.append(generSpace(spaceNum)).append("MethodSize: ");
        if(annotationDirectoryItem.getMethodsSize()==0){
            sb.append(0);
        }else{
            sb.append(annotationDirectoryItem.getMethodsSize()).append(" , {");
            for(int i = 0;i<annotationDirectoryItem.getMethodAnnotationItems().size();i++){
                MethodAnnotationItem item = annotationDirectoryItem.getMethodAnnotationItems().get(i);
                sb.append("\n").append(generSpace(spaceNum+4)).append("{ index: ").append(i).append(" , ");
                sb.append(methodIdsItems.get(item.getMethodIdx()).getNameIdsData()).append(": ").append(generAnnotationSetItemInfo(spaceNum+8,item.getMethodAnnotationsItem()));
                sb.append(" } ");
                if(i!=annotationDirectoryItem.getMethodAnnotationItems().size()-1){
                    sb.append(" , ");
                }
            }
            sb.append(" }");
        }

        sb.append("\n");
        sb.append(generSpace(spaceNum)).append("ParameterSize: ");
        if(annotationDirectoryItem.getParamtersSize() ==0){
            sb.append(0);
        }else{
            sb.append(annotationDirectoryItem.getParamtersSize()).append(" , {");
            for(int i=0;i<annotationDirectoryItem.getParamtersSize();i++){
                sb.append("\n").append(generSpace(spaceNum+4)).append("{ index ").append(i).append(" , ");
                ParameterAnnotation item = annotationDirectoryItem.getParameterAnnotationItems().get(i);
                sb.append("MethodName: ").append(methodIdsItems.get(item.getMethodIdx()).getNameIdsData()).append(" { ");
                sb.append("ParamerSize: ").append(item.getAnnotationSetRefList().getSize()).append(" , ");
                for(int j=0;j<item.getAnnotationSetRefList().getAnnotationSetRefItems().size();j++){
                    sb.append(generAnnotationSetItemInfo(spaceNum+8,item.getAnnotationSetRefList().getAnnotationSetRefItems().get(j).getParameterItem()));
                }
                sb.append(" } ");
                sb.append(" } ");
                if(i!=annotationDirectoryItem.getParamtersSize()-1){
                    sb.append(" , ");
                }

            }
            sb.append(" } ");
        }
        return sb.toString();
    }


    private String generAnnotationSetItemInfo(int spaceNum,AnnotationSetItem classAnnotation){
        StringBuilder sb = new StringBuilder();
        sb.append("{ size: ").append( classAnnotation.getSize()).append(" , ");
        for(int i = 0;i<classAnnotation.getSize();i++){

            sb.append("\n").append(generSpace(spaceNum+8)).append(" { index: ").append(i).append(" , ");
            EncodedAnnotation encodedAnnotation =classAnnotation.getItems().get(i).getAnnotationItem().getEncodedAnnotation();
            int typeIdx = encodedAnnotation.getTypeIdx();
            sb.append(Utils.basicTypeConversion(typeIdsItems.get(typeIdx).getTypeItemData())).append("（ ");
            for(int j=0;j< encodedAnnotation.getSize();j++){
                int nameIdx = encodedAnnotation.getElements().get(j).getNameIdx();
                sb.append(Utils.basicTypeConversion(stringIdsItems.get(nameIdx).getStringItemData().getRealData())).append(" = ");
                EncodedValue encodedValue =encodedAnnotation.getElements().get(j).getEncodeValue();
                String valueType =encodedValue.getTypeDescByValue(encodedValue.getRealValueType());
                sb.append(valueType);
                if(j!=encodedAnnotation.getElements().size()-1){
                    sb.append(" , ");
                }
            }
            sb.append(")");
            sb.append(" } ");
            if(i!=classAnnotation.getSize()-1){
                sb.append(" , ");
            }
        }

        sb.append("}");

        return sb.toString();
    }

    /**
     * 缩进
     * @param size
     * @return
     */
    private String generSpace(int size){

        if(size<=0){
            return "";
        }
        StringBuilder sb = new StringBuilder();

        for(int i = 0;i<size;i++){
            sb.append(" ");
        }
        return sb.toString();
    }


    private String getClassDataInfo(int spaceNum){
        spaceNum = spaceNum+4;
        StringBuilder sb = new StringBuilder();
        sb.append(generSpace(spaceNum)).append("staticfield size: ");
        if(classDataItem.getStaticFieldsSize()==0){
            sb.append(0);
        }else{
            sb.append(classDataItem.getStaticFieldsSize()).append(",{ ");
            int index =0;
            for(int i = 0;i<classDataItem.getStaticFieldsSize();i++){
                sb.append("\n").append(generSpace(spaceNum+4));
                EncodedField field =classDataItem.getStaticFields().get(i);
                index = field.getFieldIdxDiff()+index;
                sb.append(AccessFlag.getFieldAccessFlag(field.getAccessFlags()));
                sb.append(getClassName(fieldIdsItems.get(index).getTypeIdsData())).append("  ").append(fieldIdsItems.get(index).getNameIdsData());
                if(i!=classDataItem.getStaticFieldsSize()-1){
                    sb.append(" , ");
                }
            }
            sb.append(" } ");
        }
        sb.append("\n");
        sb.append(generSpace(spaceNum)).append("instanceField size: ");
        if(classDataItem.getInstanceFieldsSize()==0){
            sb.append(0);
        }else{
            sb.append(classDataItem.getInstanceFieldsSize()).append(" , { ");
            int index =0;
            for(int i = 0;i<classDataItem.getInstanceFieldsSize();i++){

                sb.append("\n").append(generSpace(spaceNum+4));
                EncodedField field =classDataItem.getInstanceFields().get(i);
                //加前一个索引值得到争取的索引值
                index = field.getFieldIdxDiff()+index;
                sb.append(AccessFlag.getFieldAccessFlag(field.getAccessFlags()));
                sb.append(getClassName(fieldIdsItems.get(index).getTypeIdsData())).append("  ").append(fieldIdsItems.get(index).getNameIdsData());
                if(i!=classDataItem.getStaticFieldsSize()-1){
                    sb.append(" , ");
                }
            }
            sb.append(" } ");
        }
        sb.append("\n");
        sb.append(generSpace(spaceNum)).append("directMethod size: ").append(classDataItem.getDirectMethodsSize());
        sb.append("\n");
        sb.append(generSpace(spaceNum)).append("virtualMethod size: ").append(classDataItem.getVirtualMethodsSize());
        return sb.toString();
    }







}
