package com.susiha.apkanalysis.dexanalysis.classdef;

import com.susiha.apkanalysis.dexanalysis.Utils;
import com.susiha.apkanalysis.dexanalysis.classdef.classdata.*;
import okio.BufferedSource;

import java.io.IOException;
import java.util.ArrayList;

public class ClassDataByteCode extends BaseByteCode {

    /**
     * 获取ClassData数据区域 包括方法和字段
     *
     * @param offSet
     * @return
     * @throws IOException
     */
    public static ClassDataItem getClassDataItem(int offSet) throws IOException {
        BufferedSource bufferedSource = Utils.getDexBufferedSource();
        bufferedSource.skip(offSet);
        ClassDataItem classDataItem = new ClassDataItem();
        //静态变量的个数
        int staticFieldsSize = getULebanese128(bufferedSource);
        //实例变量的个数
        int instanceFieldsSize = getULebanese128(bufferedSource);
        //直接方法的个数
        int directMethodsSize = getULebanese128(bufferedSource);
        //虚方法的个数
        int virtualMethodsSize = getULebanese128(bufferedSource);

        classDataItem.setStaticFieldsSize(staticFieldsSize);
        classDataItem.setInstanceFieldsSize(instanceFieldsSize);
        classDataItem.setDirectMethodsSize(directMethodsSize);
        classDataItem.setVirtualMethodsSize(virtualMethodsSize);

        if (staticFieldsSize > 0) {
            classDataItem.setStaticFields(getEncodedFieldList(staticFieldsSize, bufferedSource));
        }
        if (instanceFieldsSize > 0) {
            classDataItem.setInstanceFields(getEncodedFieldList(instanceFieldsSize, bufferedSource));
        }
        if (directMethodsSize > 0) {
            classDataItem.setDirectMethods(getEncodedMthodList(directMethodsSize, bufferedSource));
        }
        if (virtualMethodsSize > 0) {
            classDataItem.setVirtualMethods(getEncodedMthodList(virtualMethodsSize, bufferedSource));
        }
        return classDataItem;
    }

    /**
     * 获取字段列表
     *
     * @param size
     * @param bufferedSource
     * @return
     * @throws IOException
     */
    public static ArrayList<EncodedField> getEncodedFieldList(int size, BufferedSource bufferedSource) throws IOException {
        ArrayList<EncodedField> encodedFields = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            EncodedField encodedField = new EncodedField();
            int fieldIdx = getULebanese128(bufferedSource);
            int accessFlag = getULebanese128(bufferedSource);
            encodedField.setFieldIdxDiff(fieldIdx);
            encodedField.setAccessFlags(accessFlag);
            encodedFields.add(encodedField);
        }
        return encodedFields;
    }

    /**
     * 获取方法列表
     *
     * @param size
     * @param bufferedSource
     * @return
     * @throws IOException
     */
    public static ArrayList<EncodedMethod> getEncodedMthodList(int size, BufferedSource bufferedSource) throws IOException {

        ArrayList<EncodedMethod> encodedMethods = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Utils.Logger("   method index ",i);
            EncodedMethod encodedMethod = new EncodedMethod();
            int methodIdx = getULebanese128(bufferedSource);
            int accessFlag = getULebanese128(bufferedSource);
            int codeOff = getULebanese128(bufferedSource);
            encodedMethod.setMethodIdx(methodIdx);
            encodedMethod.setAccessFlags(accessFlag);
            encodedMethod.setCodeOff(codeOff);
            if (codeOff != 0) {
                encodedMethod.setCodeItem(getCodeItem(codeOff));
            }
            encodedMethods.add(encodedMethod);
        }

        return encodedMethods;
    }


    /**
     * 获取方法中的指令相关
     *
     * @param offSet
     * @return
     * @throws IOException
     */
    public static CodeItem getCodeItem(int offSet) throws IOException {
        BufferedSource bufferedSource = Utils.getDexBufferedSource();
        bufferedSource.skip(offSet);
        CodeItem codeItem = new CodeItem();
        short registersSize = getShortLet(bufferedSource);
        short insSize = getShortLet(bufferedSource);
        short outsSize = getShortLet(bufferedSource);
        short triesSize = getShortLet(bufferedSource);
        int debugInfoOff = getIntLet(bufferedSource);
        int insnsSize = getIntLet(bufferedSource);
        codeItem.setRegistersSize(registersSize);
        codeItem.setInsSize(insSize);
        codeItem.setOutsSize(outsSize);
        codeItem.setTriesSize(triesSize);
        codeItem.setDebugInfoOff(debugInfoOff);

        if (insnsSize > 0) {
            ArrayList<Short> insns = new ArrayList<>();
            for (int i = 0; i < insnsSize; i++) {
                insns.add(getShortLet(bufferedSource));
            }
            codeItem.setInsns(insns);
        }

        if (triesSize != 0 && insnsSize % 2 != 0) {
            short padding = getShortLet(bufferedSource);
            codeItem.setPadding(padding);
        }

        if (triesSize != 0) {
            codeItem.setTries(getTryItemList(triesSize, bufferedSource));
            //只有triesSize为非0时才有handler
            codeItem.setHandlers(getHandlerList(bufferedSource));
        }

        if (debugInfoOff != 0) {
            codeItem.setDebugInfoItem(getDebugInfoItem(debugInfoOff));
        }
        return codeItem;
    }


    /**
     * 获取TryItemList
     * @param size
     * @param bufferedSource
     * @return
     * @throws IOException
     */
    public static ArrayList<TryItem> getTryItemList(int size, BufferedSource bufferedSource) throws IOException {
        ArrayList<TryItem> tryItems = new ArrayList<>();
        for(int i  = 0;i<size;i++){
            TryItem  tryItem = new TryItem();
            int startAddr = getIntLet(bufferedSource);
            short insnCount = getShortLet(bufferedSource);
            short handlerOff = getShortLet(bufferedSource);
            tryItem.setHandlerOff(handlerOff);
            tryItem.setInsnCount(insnCount);
            tryItem.setStartAddr(startAddr);
            tryItems.add(tryItem);
        }
        return tryItems;
    }


    public static EncodedCatchHandlerList getHandlerList(BufferedSource bufferedSource) throws IOException {
        EncodedCatchHandlerList handlerList = new EncodedCatchHandlerList();
        int size = getULebanese128(bufferedSource);
        handlerList.setSize(size);
        if(size>0){
            ArrayList<EncodedCatchHandler> encodedCatchHandlers = new ArrayList<>();
            for(int i= 0;i<size;i++){
                EncodedCatchHandler encodedCatchHandler = new EncodedCatchHandler();
                int catchSize = getSLebanese128(bufferedSource);
                encodedCatchHandler.setSize(catchSize);
                if(Math.abs(catchSize)>0){
                    ArrayList<EncodedTypeAddrPair> encodedTypeAddrPairs = new ArrayList<>();
                    for(int j = 0;j<Math.abs(catchSize);j++){
                        EncodedTypeAddrPair encodedTypeAddrPair = new EncodedTypeAddrPair();
                        int typeIdx = getULebanese128(bufferedSource);
                        int addr = getULebanese128(bufferedSource);
                        encodedTypeAddrPair.setTypeIdx(typeIdx);
                        encodedTypeAddrPair.setAddr(addr);
                        encodedTypeAddrPairs.add(encodedTypeAddrPair);
                    }
                    encodedCatchHandler.setHandlers(encodedTypeAddrPairs);
                }

                if(catchSize<=0){
                    int catchAllAddr = getULebanese128(bufferedSource);
                    encodedCatchHandler.setCatch_all_addr(catchAllAddr);
                }
                encodedCatchHandlers.add(encodedCatchHandler);
            }
            handlerList.setEncodedCatchHandlerList(encodedCatchHandlers);
        }
        return handlerList;
    }


    public static DebugInfoItem getDebugInfoItem(int offSet) throws IOException {
        DebugInfoItem debugInfoItem = new DebugInfoItem();
        BufferedSource bufferedSource = Utils.getDexBufferedSource();
        bufferedSource.skip(offSet);
        int lineStart = getULebanese128(bufferedSource);
        int parameterSize = getULebanese128(bufferedSource);
        debugInfoItem.setLineStart(lineStart);
        debugInfoItem.setParameterSize(parameterSize);
        if(parameterSize>0){
            ArrayList<Integer> parameterNames = new ArrayList<>();
            for(int i = 0;i<parameterSize;i++){
                parameterNames.add(getLebanese128P1(bufferedSource));
            }
            debugInfoItem.setParameterNames(parameterNames);
        }
        debugInfoItem.setDebugOpCodes(DebugOpCode.getDebugOpCodeArray(bufferedSource));
        return debugInfoItem;
    }


}
