package com.micromall.datacenter.utils.impl;

import com.micromall.datacenter.utils.StringUtil;
import com.micromall.datacenter.utils.ResourceServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 * Created by CJ on 5/26/15.
 * <p>
 * 资源投放到apache服务器
 *
 * @author CJ
 */
@Profile("prod")
@Component
public class ApacheResourceServer implements ResourceServer {

    private String serverUri;
    private String resourceHome;

    @Autowired
    private void setEnv(Environment env) {
        this.serverUri = env.getProperty("micromall.resourcesUri", (String) null);
        this.resourceHome = env.getProperty("micromall.resourcesHome", (String) null);
    }


    public String saveResource(InputStream data, String orginalName, int customerId) throws IOException {
        if (serverUri == null || resourceHome == null)
            throw new IllegalStateException("请设置micromall.resourcesUri和micromall.resourcesHome属性");
        FileOutputStream outputStream = null;
        String prefix = orginalName.substring(orginalName.lastIndexOf(".") + 1);
        Date now = new Date();
        String fileFolder = "/uploaded/image/" + customerId + "/" + StringUtil.DateFormat(now, "yyyyMMdd");
        String fileName = StringUtil.DateFormat(now, "yyyyMMddHHmmSS") + "." + prefix;
        try {
            File targetFile = new File(resourceHome + fileFolder, fileName);
            if (!targetFile.getParentFile().exists()) {
                targetFile.getParentFile().mkdirs();
            }
            outputStream = new FileOutputStream(targetFile);
            StreamUtils.copy(data, outputStream);
        } finally {
            try {
                data.close();
                outputStream.close();
            } catch (IOException e) {
            }
        }
        return fileFolder + "/" + fileName;
    }

    public String resourceUri(String token) {
        return this.serverUri + token;
    }
}
