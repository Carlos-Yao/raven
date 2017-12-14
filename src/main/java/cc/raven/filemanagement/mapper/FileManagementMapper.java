package cc.raven.filemanagement.mapper;

import cc.raven.filemanagement.model.FileMessage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by fenghou on 2017/12/1.
 */
public interface FileManagementMapper {

    void uploadRecord(FileMessage fileMessage);

    List<FileMessage> selectByUserName(@Param("userName") String userName);

    FileMessage selectByUserId(@Param("id") int id);

    void delete(@Param("id") String id, @Param("userName") String userName);

}
