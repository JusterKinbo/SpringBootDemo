package demo.juster.spboot.error;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;

@Component
public class CusErrAttributes extends DefaultErrorAttributes {

    //返回值的map就是页面和json能获取的所有字段
    @Override
    public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes, boolean includeStackTrace) {
        //DefaultErrorAttributes的错误数据
        Map<String, Object> map = super.getErrorAttributes(requestAttributes, includeStackTrace);
        map.put("company","SpringBoot");
        //我们的异常处理器携带的数据
//        Map<String,Object> ext = (Map<String, Object>) requestAttributes.getAttribute("ext", 0);
        Map<String,Object> ext = new HashMap<>();
        ext.put("user", "测试");
        map.put("ext",ext);
        return map;
    }
}
