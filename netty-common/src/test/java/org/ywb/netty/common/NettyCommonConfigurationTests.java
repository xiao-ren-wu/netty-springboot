package org.ywb.netty.common;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//@SpringBootTest
class NettyCommonConfigurationTests {

    private final String BASE_PACKAGE = "com.ywb.netty.common.packet";
    private final String RESOURCE_PATTERN = "/**/*.class";

    @Test
    void contextLoads() {
        Map<String, Class> handlerMap = new HashMap<String, Class>();
        //spring工具类，可以获取指定路径下的全部类
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        try {
            String pattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
                    ClassUtils.convertClassNameToResourcePath(BASE_PACKAGE) + RESOURCE_PATTERN;
            Resource[] resources = resourcePatternResolver.getResources(pattern);
            //MetadataReader 的工厂类
            MetadataReaderFactory readerfactory = new CachingMetadataReaderFactory(resourcePatternResolver);
            for (Resource resource : resources) {
                //用于读取类信息
                MetadataReader reader = readerfactory.getMetadataReader(resource);
                //扫描到的class
                String classname = reader.getClassMetadata().getClassName();
                Class<?> clazz = Class.forName(classname);
                System.out.println(classname);
                //判断是否有指定主解
//                MyAnno anno = clazz.getAnnotation(MyAnno.class);
//                if (anno != null) {
//                    //将注解中的类型值作为key，对应的类作为 value
//                    handlerMap.put(classname, clazz);
//                }
            }
        } catch (IOException | ClassNotFoundException e) {
        }
        System.out.println(handlerMap.toString());
    }

}
