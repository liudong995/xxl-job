/**
 * Project Name:bts-common-tools
 * File Name:HttpSendUtil.java
 * Package Name:cn.easysw.bts.common.tools.utils
 * Date:2018年5月25日下午12:53:19
 * Copyright (c) 2018, www.windo-soft.com All Rights Reserved.
 */

package util;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;


public class HttpSendUtil {

    /**
     * 发送post请求
     * 2020年08月07日18:24:25 zjf
     * @param uri
     * @param reqEntity
     * @return
     * @throws IOException
     */
    public static String postFormData(String uri, HttpEntity reqEntity) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String responseParam = "";
        try {
            HttpPost httppost = new HttpPost(uri);
            httppost.setEntity(reqEntity);
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    responseParam = EntityUtils.toString(resEntity);
                }
                EntityUtils.consume(resEntity);

            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
        return responseParam;
    }

}
