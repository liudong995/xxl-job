package core;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import model.JobCreateRequest;
import model.JobRequest;
import model.JobUpdateRequest;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import util.HttpSendUtil;

import java.util.Map;

/**
 * @author liudong
 * @Date: 2020/9/11 18:42
 * @DESC
 * @since JDK 1.8
 */
@Slf4j
public class JobSend {

    public static final String URL = "/outside/trigger";
    public static final String update_cron = "/outside/updateCron";
    public static final String create_cron = "/outside/createCron";

    public static String send(String urlFix,JobRequest jobRequest){
        try {
            String requestUrl = urlFix + URL;
            //加签
            jobRequest.setSign(getSign(jobRequest.getAuthor(),jobRequest.getSign()));
            HttpEntity requestFormData = createParam(jobRequest);
            log.info("Job系统调用：地址为:{},请求参数:{}", requestUrl,requestFormData);
            String response = HttpSendUtil.postFormData(requestUrl, requestFormData);
            log.info("Job系统调用：response:{}", response);
            return response;
        } catch (Exception e) {
            log.info("Job系统调用：异常");
            log.error(e.getMessage(), e);
            return "";
        }
    }

    public static String updateCron(String urlFix, JobUpdateRequest jobRequest){
        try {
            String requestUrl = urlFix + update_cron;
            //加签
            jobRequest.setSign(getSign(jobRequest.getAuthor(),jobRequest.getSign()));
            HttpEntity requestFormData = createParam(jobRequest);
            log.info("Job系统调用：地址为:{},请求参数:{}", requestUrl,requestFormData);
            String response = HttpSendUtil.postFormData(requestUrl, requestFormData);
            log.info("Job系统调用：response:{}", response);
            return response;
        } catch (Exception e) {
            log.info("Job系统调用：异常");
            log.error(e.getMessage(), e);
            return "";
        }
    }
    public static String initCron(String urlFix, JobCreateRequest jobRequest){
        if (StringUtils.isEmpty(urlFix)){
            return "";
        }
        try {
            String requestUrl = urlFix + create_cron;
            //加签
            jobRequest.setSign(getSign(jobRequest.getAuthor(),jobRequest.getSign()));
            HttpEntity requestFormData = createParam(jobRequest);
            log.info("Job系统调用：地址为:{},请求参数:{}", requestUrl,requestFormData);
            String response = HttpSendUtil.postFormData(requestUrl, requestFormData);
            log.info("Job系统调用：response:{}", response);
            return response;
        } catch (Exception e) {
            log.info("Job系统调用：异常");
            log.error(e.getMessage(), e);
            return "";
        }
    }
    private static String getSign(String merchantNo, String message) {
        String base = merchantNo + message;
        return DigestUtils.md5DigestAsHex(base.getBytes());
    }

    /**
     创建HttpEntity对象用于请求
     * @param jobRequest
     * @return
     */
    public static HttpEntity createParam(JobRequest jobRequest){
        String req = JSON.toJSONString(jobRequest);
        Map map = JSONObject.parseObject(req, Map.class);

        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        for (Object key : map.keySet()) {
            builder.addPart(key.toString(), new StringBody(map.get(key.toString()).toString(), ContentType.TEXT_PLAIN));
        }
        return  builder.build() ;
    }
}
