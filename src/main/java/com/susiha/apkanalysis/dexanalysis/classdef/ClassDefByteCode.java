package com.susiha.apkanalysis.dexanalysis.classdef;

import com.susiha.apkanalysis.dexanalysis.Utils;
import com.susiha.apkanalysis.dexanalysis.common.DexTypeItem;
import com.susiha.apkanalysis.dexanalysis.common.DexTypeList;
import com.susiha.apkanalysis.dexanalysis.fieldids.FieldIdsItem;
import com.susiha.apkanalysis.dexanalysis.methodids.MethodIdsItem;
import com.susiha.apkanalysis.dexanalysis.stringids.StringIdsItem;
import com.susiha.apkanalysis.dexanalysis.typeIds.TypeIdsItem;
import okio.BufferedSource;

import java.io.IOException;
import java.util.ArrayList;

public class ClassDefByteCode extends BaseByteCode {


    /**
     * 解析Dex中的数据区域
     * @param stringIdsItems
     * @param typeIdsItems
     * @param methodIdsItems
     * @param fieldIdsItems
     * @param classDefSize
     * @param calssDefOff
     * @return
     * @throws IOException
     */
    public static ArrayList<ClassDefItem> decodeClassDef(ArrayList<StringIdsItem> stringIdsItems,
                                                         ArrayList<TypeIdsItem> typeIdsItems,
                                                         ArrayList<MethodIdsItem> methodIdsItems,
                                                         ArrayList<FieldIdsItem> fieldIdsItems,
                                                         int classDefSize,int calssDefOff)
            throws IOException{

        ArrayList<ClassDefItem> classDefItems = new ArrayList<>();
        BufferedSource bufferedSource = Utils.getDexBufferedSource();
        bufferedSource.skip(calssDefOff);
        for(int i = 0;i<classDefSize;i++){
            Utils.Logger("Test Index ",i);
            ClassDefItem item = new ClassDefItem();
            item.setTypeIdsItems(typeIdsItems);
            item.setStringIdsItems(stringIdsItems);
            item.setMethodIdsItems(methodIdsItems);
            item.setFieldIdsItems(fieldIdsItems);
            int classIdx = getIntLet(bufferedSource);
            item.setClassIdx(classIdx);
            int accessFlags = getIntLet(bufferedSource);
            item.setAccessFlags(accessFlags);
            int superClassIdx = getIntLet(bufferedSource);
            item.setSuperclassIdx(superClassIdx);
            int interfaceOff = getIntLet(bufferedSource);
            item.setInterfacesOff(interfaceOff);
            int sourceFileIdx = getIntLet(bufferedSource);
            item.setSourceFileIdx(sourceFileIdx);
            int annotationOff = getIntLet(bufferedSource);
            item.setAnnotationsOff(annotationOff);
            int classDataOff = getIntLet(bufferedSource);
            item.setClassDataOff(classDataOff);
            int staticValuesOff = getIntLet(bufferedSource);
            item.setStaticValuesOff(staticValuesOff);
            //读取实现的接口
            if(interfaceOff!=0){
                item.setInterfaceList(getInerfaceList(interfaceOff));
            }
            //读取所有的注解，包括类注解,变量注解和方法注解，参数注解 等等
            if(annotationOff!=0){
                item.setAnnotationDirectoryItem(AnnotationByteCode.getAnnotationDirectItem(annotationOff));
            }
            if(classDataOff!=0){
                item.setClassDataItem(ClassDataByteCode.getClassDataItem(classDataOff));
            }
            classDefItems.add(item);
        }
        return classDefItems;
    }



    /**
     * 获取所有接口列表
     * @param interfaceOff 偏移量
     * @return
     * @throws IOException
     */
    public static DexTypeList getInerfaceList(int interfaceOff) throws IOException{
        BufferedSource bufferedSource = Utils.getDexBufferedSource();
        bufferedSource.skip(interfaceOff);
        DexTypeList typeList = new DexTypeList();
        int size = getIntLet(bufferedSource);
        typeList.setSize(size);
        ArrayList<DexTypeItem> dexTypeItems = new ArrayList<>();
        for(int i = 0;i<size;i++){
            DexTypeItem  item = new DexTypeItem();
            short index = getShortLet(bufferedSource);
            item.setTypeIdx(index);
            dexTypeItems.add(item);
        }
        typeList.setDexTypeLists(dexTypeItems);
        return typeList;
    }





















}
