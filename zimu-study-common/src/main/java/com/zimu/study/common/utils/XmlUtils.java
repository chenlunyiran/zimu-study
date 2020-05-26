package com.zimu.study.common.utils;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import javax.xml.bind.JAXB;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Slf4j
public class XmlUtils {

    public static <T> T parse(String content, Class<T> clazz) {
        return JAXB.unmarshal(new StringReader(content), clazz);
    }

    /**
     * @param content
     * @param typeReference eg: new TypeReference<List<NotifySmsYunZhiXunReplyRecordDTO>>(){})
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> T parseList(String content, TypeReference<T> typeReference) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.registerModule(new JaxbAnnotationModule());
//        xmlMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return xmlMapper.readValue(content, typeReference);
    }

    /**
     * 解析XML多节点内容为List集合
     *
     * @param tr2   将要解析为List集合的节点
     * @param s     XML格式的字符串
     * @param clazz 接收实体类
     * @return
     */
    public static <T> List<T> readXMLToList(String tr2, String s, Class<?> clazz) {
        //创建list集合
        List<Object> list = new ArrayList();
        try {
            // 1.创建一个SAXBuilder的对象
            SAXReader reader = new SAXReader();
            // 2.dom4j读取 指定编码格式为UTF-8,可根据具体情况修改
            org.dom4j.Document doc = reader.read(new InputSource(new ByteArrayInputStream(s.getBytes("UTF-8"))));
            // 4.通过document对象获取xml文件的根节点
            org.dom4j.Element root = doc.getRootElement();
            // 5. 获取根节点下的子节点items,即要解析XML多节点的父节点，可根据具体情况修改
//            org.dom4j.Element body = root.element("items");
            // 6. 获取根节点下的二级节点
            org.dom4j.Element foo;
            //遍历t.getClass().getSimpleName()节点
            for (Iterator i = root.elementIterator(tr2); i.hasNext(); ) {
                //下一个二级节点
                foo = (org.dom4j.Element) i.next();
                //实例化Object对象
                Object obj = clazz.newInstance();
                //获得实例的属性
                Field[] properties = obj.getClass().getDeclaredFields();
                //实例的get方法
                Method getmeth;
                //实例的set方法
                Method setmeth;
                //遍历实体类的属性
                for (int j = 0; j < properties.length; j++) {
                    //实例的set方法
                    if (properties[j].getName().equals("serialVersionUID")) {
                        continue;
                    } else {
                        setmeth = obj.getClass().getMethod(
                                "set"
                                        + properties[j].getName().substring(0, 1).toUpperCase()
                                        + properties[j].getName().substring(1), properties[j].getType());
                        Object setStr = foo.elementText(properties[j].getName());

                        if (foo.elementText(properties[j].getName()) != null && !"".equals(foo.elementText(properties[j].getName()))) {
                            if (properties[j].getType() == java.util.Date.class) {
                                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                Date sj = df.parse(foo.elementText(properties[j].getName()));
                                setStr = sj;
                            } else if (properties[j].getType() == java.lang.Integer.class) {
                                setStr = Integer.parseInt(foo.elementText(properties[j].getName()));
                            } else if (properties[j].getType() == java.lang.Character.class) {
                                setStr = foo.elementText(properties[j].getName()).charAt(0);
                            } else if (properties[j].getType() == java.lang.Double.class) {
                                setStr = Double.parseDouble(foo.elementText(properties[j].getName()));
                            }
                        } else {
                            setStr = null;
                        }
                        //将对应节点的值存入
                        setmeth.invoke(obj, setStr);
                    }
                }
                list.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return (List<T>) JSON.parseArray(JSON.toJSONString(list), clazz);
    }

    public static void main(String[] args) throws IOException {
        String msg = "<?xml version=\"1.0\" encoding=\"utf-8\" ?><returnsms>" +
                "<callbox><mobile>13605801525</mobile>" +
                "<taskid>1212</taskid>" +
                "<content>你好，我不需要</content>" +
                "<receivetime>2011-12-02 22:12:11</receivetime>" +
                "</callbox>" +
                "<callbox>" +
                "<mobile>13605801525</mobile>" +
                "<taskid>1212</taskid>" +
                "<content></content>" +
                "<receivetime>2011-12-02 22:12:11</receivetime>" +
                "<extno><![CDATA[10690123]]></extno>" +
                "</callbox>" +
                "</returnsms>";

//        List<AbelSmsYunZhiXunReplyRecordDTO> objectList = readXMLToList("callbox", msg, AbelSmsYunZhiXunReplyRecordDTO.class);
//        System.out.println(parseList(msg, new TypeReference<List<NotifySmsYunZhiXunReplyRecordDTO>>() {}));
    }
}
