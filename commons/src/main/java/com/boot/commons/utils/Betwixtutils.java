package com.boot.commons.utils;

import org.apache.commons.betwixt.io.BeanWriter;

import java.io.StringWriter;

public class Betwixtutils {

    /**
     * bean转xml文件
     * @param args
     * @throws Exception
     */

    public static final void main(String [] args) throws Exception {
        // 先创建一个StringWriter，我们将把它写入为一个字符串
        StringWriter outputWriter = new StringWriter();
        // Betwixt在这里仅仅是将Bean写入为一个片断
        // 所以如果要想完整的XML内容，我们应该写入头格式
        outputWriter.write("<?xml version='1.0' encoding='UTF-8' ?>\n");
        // 创建一个BeanWriter，其将写入到我们预备的stream中
        BeanWriter beanWriter = new BeanWriter(outputWriter);
        // 配置betwixt
        // 更多详情请参考java docs 或最新的文档
        beanWriter.getXMLIntrospector().getConfiguration().setAttributesForPrimitives(false);
        beanWriter.getBindingConfiguration().setMapIDs(false);
        beanWriter.enablePrettyPrint();
        // 如果这个地方不传入XML的根节点名，Betwixt将自己猜测是什么
        // 但是让我们将例子Bean名作为根节点吧
        beanWriter.write("person", new Person("John Smith", 21));
        //输出结果
        System.out.println(outputWriter.toString());
        // Betwixt写的是片断而不是一个文档，所以不要自动的关闭掉writers或者streams，
        //但这里仅仅是一个例子，不会做更多事情，所以可以关掉
        outputWriter.close();
    }
}
