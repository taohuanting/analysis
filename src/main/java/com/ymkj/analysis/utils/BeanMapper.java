package com.ymkj.analysis.utils;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.github.dozermapper.core.loader.api.BeanMappingBuilder;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 简单封装Dozer
 * @author system
 */
public class BeanMapper {

    private static Mapper MAPPER = DozerBeanMapperBuilder.buildDefault();

    /**
     * 通过指定目标Bean的classs来进行Bean拷贝
     * @param source
     * @param destinationClass
     * @param <T>
     * @return
     */
    public static <T> T map(Object source, Class<T> destinationClass) {
        if (source == null) {
            return null;
        }
        return MAPPER.map(source, destinationClass);
    }
    /**
     * 通过指定目标Bean的classs来进行Bean拷贝,忽略空字符串
     * @param source
     * @param destinationClass
     * @param <T>
     * @return
     */
    public static <T> T mapIgnoreEmpty(Object source, Class<T> destinationClass) {
        if (source == null) {
            return null;
        }
        WeakReference weakReference = new WeakReference(DozerBeanMapperBuilder.create()
            .withMappingBuilder(new BeanMappingBuilder() {
                @Override
                protected void configure() {
                    mapping(type(source.getClass()), type(destinationClass).mapNull(false).mapEmptyString(false));
                }
            }).build());
        try {
            Mapper mapper = (Mapper) weakReference.get();
            return mapper.map(source, destinationClass);
        }finally {
            weakReference.clear();
        }
    }

    /**
     * 实现Bean的拷贝
     * @param source
     * @param destination
     */
    public static void map(Object source, Object destination) {
        MAPPER.map(source, destination);
    }

    /**
     * 实现Bean的拷贝,忽略null
     * @param source
     * @param destination
     */
    public static void mapIgnoreNull(Object source, Object destination) {
        WeakReference weakReference = new WeakReference(DozerBeanMapperBuilder.create()
            .withMappingBuilder(new BeanMappingBuilder() {
                @Override
                protected void configure() {
                    mapping(type(source.getClass()), type(destination.getClass()).mapNull(false));
                }
            }).build());
        try {
            Mapper mapper = (Mapper) weakReference.get();
            mapper.map(source, destination);
        }finally {
            weakReference.clear();
        }
    }

    /**
     * 实现Bean的拷贝,忽略null和空字符串
     * @param source
     * @param destination
     */
    public static void mapIgnoreEmpty(Object source, Object destination) {
        WeakReference weakReference = new WeakReference(DozerBeanMapperBuilder.create()
            .withMappingBuilder(new BeanMappingBuilder() {
                @Override
                protected void configure() {
                    mapping(type(source.getClass()), type(destination.getClass()).mapNull(false).mapEmptyString(false));
                }
            }).build());
        try {
            Mapper mapper = (Mapper) weakReference.get();
            mapper.map(source, destination);
        }finally {
            weakReference.clear();
        }
    }

    /**
     * 实现集合拷贝
     * @param sourceList
     * @param destinationClass
     * @param <T>
     * @return
     */
    public static <T> List<T> mapList(Collection sourceList, Class<T> destinationClass) {
        if(null == sourceList || sourceList.isEmpty()){
            return new ArrayList<>();
        }
        List<T> destinationList = new ArrayList<>();
        for (Object sourceObject : sourceList) {
            destinationList.add(map(sourceObject,destinationClass));
        }
        return destinationList;
    }

    /**
     * 实现集合拷贝,忽略空字符串
     * @param sourceList
     * @param destinationClass
     * @param <T>
     * @return
     */
    public static <T> List<T> mapListIgnoreEmpty(Collection sourceList, Class<T> destinationClass) {
        if(null == sourceList || sourceList.isEmpty()){
            return new ArrayList<>();
        }
        List<T> destinationList = new ArrayList<>();
        for (Object sourceObject : sourceList) {
            destinationList.add(mapIgnoreEmpty(sourceObject,destinationClass));
        }
        return destinationList;
    }

    /**
     * 实现集合拷贝,忽略空字符串
     *
     * @param sourceList
     * @param destinationClass
     * @param <T>
     * @return
     */
    public static <T> List<T> mapListIgnoreEmpty(Collection sourceList, Class<?> sourceClass, Class<T> destinationClass) {
        if(null == sourceList || sourceList.isEmpty()){
            return new ArrayList<>();
        }
        WeakReference weakReference = new WeakReference(DozerBeanMapperBuilder.create()
            .withMappingBuilder(new BeanMappingBuilder() {
                @Override
                protected void configure() {
                    mapping(type(sourceClass), type(destinationClass).mapNull(false).mapEmptyString(false));
                }
            }).build());
        try {
            Mapper mapper = (Mapper) weakReference.get();
            List<T> destinationList = new ArrayList<>();
            for (Object sourceObject : sourceList) {
                if(null == sourceObject){
                    destinationList.add(null);
                }else{
                    destinationList.add(mapper.map(sourceObject,destinationClass));
                }
            }
            return destinationList;
        }finally {
            weakReference.clear();
        }

    }
}
