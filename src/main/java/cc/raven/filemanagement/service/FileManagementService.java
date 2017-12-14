package cc.raven.filemanagement.service;

import cc.raven.filemanagement.model.FileMessage;

import java.util.List;

/**
 * Created by fenghou on 2017/12/1.
 */
public interface FileManagementService {

    void uploadRecord(FileMessage fileMessage);

    List<FileMessage> selectByUserName(String userName);

    FileMessage selectByUserId(int id);

    void delete(String id);

}
