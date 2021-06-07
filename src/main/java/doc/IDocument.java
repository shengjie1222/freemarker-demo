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
