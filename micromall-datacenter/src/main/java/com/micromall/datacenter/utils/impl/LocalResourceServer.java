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
 * Created by Administrator on 2015/5/21.
 */
@Profile("!prod")
@Component
public class LocalResourceServer implements ResourceServer {

    @Autowired
    private void setEnv(Environment env) {
        this.serverUri = env.getProperty("micromall.resouceUri", "http://localhost:8080/admin");
    }

    private String serverUri;

    public String saveResource(InputStream data, String savePath, String orignalFile, int customerId) throws IOException {
        FileOutputStream outputStream = null;
        String prefix = orignalFile.substring(orignalFile.lastIndexOf(".") + 1);
        Date now = new Date();
        String fileFolder = "/uploaded/image/" + customerId + "/" + StringUtil.DateFormat(now, "yyyyMMdd");
        String fileName = StringUtil.DateFormat(now, "yyyyMMddHHmmSS") + "." + prefix;
        try {
            File targetFile = new File(savePath + fileFolder, fileName);
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
        return serverUri + token;
    }
}
