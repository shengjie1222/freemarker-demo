package doc.impl;

import doc.DocumentFactory;
import doc.IDocument;
import org.junit.Test;
import utils.ImgUtils;

import java.text.SimpleDateFormat;
import java.util.*;

public class FreemarkerServiceTest{

    private final String ROOT_PATH = this.getClass().getClassLoader().getResource("images").getPath();

    @Test
    public void exportDoc() throws Exception {
        IDocument IDocument = new FreemarkerService("template_2");

        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("name", "yacongliu");

        String msg = IDocument.exportDoc(dataMap, ROOT_PATH+"\\demo2.doc");
        System.out.println(msg);

    }

    @Test
    public void exportDocTable() throws Exception {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        IDocument IDocument = new FreemarkerService("template_3");

        dataMap.put("code", "001");
        dataMap.put("name", "张三" + ";<w:br />" + "李四");
        dataMap.put("number", "tbkg001");
        dataMap.put("phone", "1888888888");
        List list = new ArrayList();
        Map<String, String> map1 = new HashMap<String, String>(3);
        map1.put("jl", "2019-03 天津大学");

        Map<String, String> map2 = new HashMap<String, String>(3);
        map2.put("jl", "2015-03 南开大学");

        list.add(map1);
        list.add(map2);

        dataMap.put("columns", list);

        String msg = IDocument.exportDoc(dataMap, ROOT_PATH+"\\demo3.docx");

        System.out.println(msg);

    }
    @Test
    public void export2DocTable() throws Exception {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        IDocument IDocument = new FreemarkerService("template_1");
        dataMap.put("title", "001");
        dataMap.put("create_date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        dataMap.put("content", "Ethereal");
        List<String> images = new ArrayList();
        images.add(ImgUtils.getImgBase64Code(ROOT_PATH+"\\001.jpeg"));
        images.add(ImgUtils.getImgBase64Code(ROOT_PATH+"\\002.svg"));
        images.add(ImgUtils.getImgBase64Code(ROOT_PATH+"\\003.jpeg"));
        dataMap.put("images", images);
        String msg = IDocument.exportDoc(dataMap, ROOT_PATH+"\\demo1.docx");
        System.out.println(msg);

    }

    @Test
    public void exportDocFactory() throws Exception {
        IDocument IDocument = DocumentFactory.produceFreemarker();
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("title", "001");
        dataMap.put("create_date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        dataMap.put("content", "Ethereal");
        List<String> images = new ArrayList();
        images.add(ImgUtils.getImgBase64Code(ROOT_PATH+"\\001.jpeg"));
        images.add(ImgUtils.getImgBase64Code(ROOT_PATH+"\\002.svg"));
        images.add(ImgUtils.getImgBase64Code(ROOT_PATH+"\\003.jpeg"));
        dataMap.put("images", images);
        String msg = IDocument.exportDoc(dataMap, ROOT_PATH+"\\demo_factory1.docx");
        System.out.println(msg);

    }
}