# freemarker-demo

#### 介绍
Java结合Freemarker模板技术生成office word 文档。并提供两种功能：
1. 根据模板导出word到本地
2. 根据模板响应到服务端，提供word下载
#### 软件架构
Java、Freemarker、Fastjson

#### 开发工具及依赖
IDEA-2021.1 <br>

freemarker-2.3.28.jar <br>
fastjson-1.2.75.jar <br>
commons-lang3-3.11.jar <br>
junit-4.13.1.jar

#### Freemarker模板简介
API: [api](http://freemarker.foofun.cn/pgui_quickstart_merge.html)

**数据模型+模板=输出**

首先，你应该创建一个 `freemarker.template.Configuration` 实例， 然后调整它的设置。`Configuration` 实例是存储 FreeMarker 应用级设置的核心部分。同时，它也处理创建和 *缓存* 预解析模板(比如 `Template` 对象)的工作。

不需要重复创建 `Configuration` 实例； 它的代价很高，尤其是会丢失缓存。`Configuration`实例就是应用级别的单例。因此Configuration需要设计成全应用单例模式。

**创建数据模型**

`Map<String, Object> root `  或者符合JavaBean规范具有getxxx()方法的对象

**获取模板**

从Configuration实例中获取Template实例`Template temp = cfg.getTemplate("test.ftl");`

`Configuration` 缓存 `Template` 实例，当再次获得 `test.ftl` 的时候，它可能再读取和解析模板文件了， 而只是返回第一次的 `Template`实例。

**合并模板和数据模型**

合并是由模板实例Template的process()完成的。

`template.process(dataMap,out)`



#####  制作Freemarker模板（.ftl）

1. 将word模板格式另存为.xml格式文档（注意：不是直接改文件后缀名，而是转为word .xml格式文档！）

2. 使用在线xml格式工具，格式化word .xml中的xml内容后，直接修改后缀名为 **.ftl**

3. 修改模板变量名 ${name}

   ```
   <w:r wsp:rsidRPr="00B50F88">
             <w:rPr>
               <w:rFonts w:ascii="宋体" w:fareast="宋体" w:h-ansi="宋体" w:hint="fareast"/>
               <wx:font wx:val="宋体"/>
             </w:rPr>
             <w:t>${name}</w:t>
           </w:r>
           <w:r wsp:rsidR="001016F6" wsp:rsidRPr="00B50F88">
             <w:rPr>
               <w:rFonts w:ascii="宋体" w:fareast="宋体" w:h-ansi="宋体" w:hint="fareast"/>
               <wx:font wx:val="宋体"/>
             </w:rPr>
             <w:t>早上好，今天是晴天！</w:t>
           </w:r>
   ```

##### 数据模型

```
@Test
    public void exportDoc() throws Exception {
        IDocument IDocument = new FreemarkerService();

        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("name", "yacongliu");

        String msg = IDocument.exportDoc(dataMap, "D:/test.doc");


        System.out.println(msg);

    }
```

##### 文档效果

见 images 文件夹内图片

#### 接口调用说明

设计`IDocument` 文档接口，定义文档操作方法：导出word到本地以及响应服务端提供下载。

```
package doc;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author Administrator
 */
public interface IDocument {

    /**
     * 导出word到本地
     *
     * @param dataMap  数据模型
     * @param filePath 文件路径
     * @return
     */
    String exportDoc(Map<?, ?> dataMap, String filePath);

    /**
     * 导出word响应到服务端Response
     *
     * @param docName  文档名称
     * @param dataMap  数据模型
     * @param response HttpServletResponse
     * @return
     */
    String exportDoc(String docName, Map<?, ?> dataMap, HttpServletResponse response);
}

```

接口实现类`doc.impl.FreemarkerService` 进行具体的文档操作编码。

工厂类`DocumentFactory` 提供接口实例 采用静态工厂方法模式

```
/**
 * Copyright (C), 2016-2019
 * FileName: DocumentFactory
 * Author:   yacnog_liu
 * Date:     2019/3/19 8:43
 * Description: DocumentFactory
 * <p>
 * 设计模式-静态工厂方法模式
 */
package cdoc;

import doc.impl.FreemarkerService;

public class DocumentFactory {

    public static IDocument produceFreemarker() {
        return new FreemarkerService();
    }
}

```

**接口调用示例**

```
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
```



#### 参与贡献

1. Fork 本仓库
2. 新建 Feat_xxx 分支
3. 提交代码
4. 新建 Pull Request
