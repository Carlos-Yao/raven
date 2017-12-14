package cc.raven.filemanagement.service.impl;

import cc.raven.filemanagement.mapper.FileManagementMapper;
import cc.raven.filemanagement.model.FileMessage;
import cc.raven.filemanagement.service.FileManagementService;
import cc.raven.util.StaticInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fenghou on 2017/12/1.
 */
@Service("fileManagementService")
public class FileManagementServiceImpl implements FileManagementService {
    @Autowired
    private FileManagementMapper fileManagementMapper;

    public void uploadRecord(FileMessage fileMessage) {
        fileManagementMapper.uploadRecord(fileMessage);
    }

    public List<FileMessage> selectByUserName(String userName) {
        return fileManagementMapper.selectByUserName(userName);
    }

    public FileMessage selectByUserId(int id) {
        return fileManagementMapper.selectByUserId(id);
    }

    public void delete(String id) {
        fileManagementMapper.delete(id, StaticInfo.userName);
    }
}
