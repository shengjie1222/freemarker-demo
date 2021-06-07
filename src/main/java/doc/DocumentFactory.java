package doc;


import doc.impl.FreemarkerService;

/**
 * 设计模式-静态工厂方法模式
 * @author Administrator
 */
public class DocumentFactory {

    /**
     * 获取FreemarkerService实例 template默认模板
     *
     * @return IDocument
     */
    public static IDocument produceFreemarker() {
        return new FreemarkerService();
    }

    /**
     * @param templatePackage 模板路径 eg:"/com/kingdee/shr/template"
     * @param templateName    模板名称 eg:"template.ftl" or "template"
     * @return IDocument
     */
    public static IDocument produceFreemarker(String templatePackage, String templateName) {
        return new FreemarkerService(templatePackage, templateName);
    }

    /**
     * @param templateName 模板名称 eg:"template.ftl" or "template"
     * @return
     */
    public static IDocument produceFreemarker(String templateName) {
        return new FreemarkerService(templateName);
    }
}
