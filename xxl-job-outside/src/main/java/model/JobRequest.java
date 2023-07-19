package model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author liudong
 * @Date: 2020/9/11 18:41
 * @DESC
 * @since JDK 1.8
 */
@Getter
@Setter
public class JobRequest {
    /**
     * 签名数据
     */
    private String sign;
    /**
     * 关联用户,数票运营商ID
     */
    private String author;
    /**
     * 对应的任务名称
     */
    private String jobHandler;
    /**
     * json参数
     */
    private String jsonStr;
}
