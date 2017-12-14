package cc.raven.filemanagement.controller;

import cc.raven.filemanagement.model.FileMessage;
import cc.raven.filemanagement.service.FileManagementService;
import cc.raven.util.Result;
import cc.raven.util.StaticInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by fenghou on 2017/11/30.
 */
@RequestMapping("/file")
@Controller
public class FileManagementController {

    private final String locationPath = "/usr/raven/private/store";
    @Autowired
    private FileManagementService fileManagementService;

    @RequestMapping(value = "/getMyFile", method = RequestMethod.GET)
    @ResponseBody
    public Result getMyFile() {
        try {
            String userName = StaticInfo.userName;
            List<FileMessage> result = new ArrayList<FileMessage>();
            if ("".equals(userName)) {
                return new Result(201, "验证身份失败", result);
            }
            result = fileManagementService.selectByUserName(userName);
            return new Result(200, "获取文件列表成功", result);
        } catch (Throwable e) {
            throw new RuntimeException("load file error", e);
        }
    }

    @RequestMapping(value = "/delete/{id}.lg", method = RequestMethod.GET)
    @ResponseBody
    public Result delete(@PathVariable("id") String id) {
        try {
            fileManagementService.delete(id);
            return new Result(200, "删除成功", "");
        } catch (Throwable e) {
            throw new RuntimeException("delete file error", e);
        }
    }

    @RequestMapping(value = "/upload.lg", method = RequestMethod.POST)
    @ResponseBody
    public Result upload(HttpServletRequest request) {
        InputStream is = null;
        OutputStream out = null;
        try {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            String realName = request.getParameter("fileName");
            Map<String, MultipartFile> map = multipartHttpServletRequest.getFileMap();
            for (String name : map.keySet()) {
                MultipartFile multipartFile = map.get(name);
                String fileName = multipartFile.getOriginalFilename();
                long size = multipartFile.getSize();
                is = multipartFile.getInputStream();
                UUID uuid = UUID.randomUUID();
                String uuidStr = uuid.toString();
                String savePath = locationPath + "/" + uuidStr + ".temp";
                File file = new File(savePath);
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                if (!file.exists()) {
                    file.createNewFile();
                }
                out = new FileOutputStream(file);
                byte[] b = new byte[1024];
                int n;
                while ((n = is.read(b)) != -1) {
                    out.write(b, 0, n);
                }
                FileMessage fileMessage = new FileMessage();
                fileMessage.setRealName(realName);
                fileMessage.setFileName(fileName);
                fileMessage.setSavePath(savePath);
                fileMessage.setSize(new Long(size / 1024).toString() + "K");
                fileMessage.setUploader(StaticInfo.userName);
                fileManagementService.uploadRecord(fileMessage);
            }
            return new Result(200, "上传成功", "");
        } catch (IOException e) {
            throw new RuntimeException("file load error", e);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null)
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    @RequestMapping(value = "/download/{id}.lg", method = RequestMethod.GET)
    public void download(@PathVariable("id") int id, HttpServletRequest request, HttpServletResponse response) {
        InputStream inputStream = null;
        try {
            FileMessage fileMessage = fileManagementService.selectByUserId(id);
            File file = new File(fileMessage.getSavePath());
            if (!file.exists()) {
                response.getWriter().write("未找到下载资源！");
                return;
            }
            String fileName = URLEncoder.encode(fileMessage.getFileName(), "UTF-8");
            response.reset();
            response.setContentType("application/octet-stream; charset=utf-8");
            response.setHeader("Content-Disposition", "attachment; fileName=" + fileName);
            inputStream = new FileInputStream(file);
            int n;
            byte[] b = new byte[1024];
            while ((n = inputStream.read(b)) != -1) {
                response.getOutputStream().write(b, 0, n);
            }
        } catch (Throwable e) {
            throw new RuntimeException("download file error", e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
