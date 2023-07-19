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
public class JobCreateRequest extends JobRequest{

    /**
     * 执行表达式
     */
    private String jobCron;
    /**
     * 任务名称
     */
    String jobDesc;
    /**
     * 执行分组名称
     */
    String groupName;


}
