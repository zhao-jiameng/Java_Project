package etl;

import java.util.stream.IntStream;

/**
 * etl工具类
 */
public class EtlUtils {
    /**
     * 清洗古丽影音项目中cideo数据
     * 清洗规则：
     * 1.数据长度
     * 2.去掉视频类别中的空格
     * 3.将多个关联数据的id通过&连接
     */
    public static String etlGulivideo(String srcData) {
        StringBuffer sbs = new StringBuffer();
        //1.判断数据长度是否合法
        String[] videoInfo = srcData.split("\t");
        if (videoInfo.length < 9) {
            return null;
        }
        //2.去掉视频类别中的空格
        videoInfo[3] = videoInfo[3].replaceAll(" ", "");
        //3.将多个关联数据的id通过&连接
        IntStream.range(0, videoInfo.length).forEach(i -> {
            if (i < 9)
                if (i == videoInfo.length - 1) sbs.append(videoInfo[i]);//没有关联视频
                else sbs.append(videoInfo[i]).append("\t");             //有关联视频
            else
                if (i == videoInfo.length - 1) sbs.append(videoInfo[i]);//向后添加一个&符号
                else sbs.append(videoInfo[i]).append("&");              //最后一个直接添加
        });
        return sbs.toString();
    }
}

