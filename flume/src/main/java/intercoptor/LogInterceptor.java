package intercoptor;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.util.List;
import java.util.Map;

/**
 * 需求：将event按照是否包含“zjm”字母，给每个event添加对应的header
 * 自定义Interceptor需要实现Interceptor接口，并提供一个实现Builder接口的内部类，用于创建拦截器对象
 */
public class LogInterceptor implements Interceptor {
    @Override
    public void initialize() {

    }

    /**
     * 对单个event进行拦截处理
     * @param event
     * @return
     */
    @Override
    public Event intercept(Event event) {
        //获取event的body数据转换成字符串
        String body = String.valueOf(event.getBody());
        //获取event的headers
        Map<String, String> headers = event.getHeaders();
        //判断body中是否包含“zjm”
        if (body.contains("zjm")){
            headers.put("title", "bh");
        }else {
            headers.put("title", "bbh");
        }
        return event;
    }

    /**
     * 对多个event进行拦截处理
     * @param list
     * @return
     */
    @Override
    public List<Event> intercept(List<Event> list) {
        list.forEach(this::intercept);
        return list;
    }

    @Override
    public void close() {

    }
    public static class MyBuilder implements Builder{   //构造拦截器对象

        @Override
        public Interceptor build() {
            return new LogInterceptor();
        }

        @Override
        public void configure(Context context) {
            //读取配置文件
        }
    }
}
