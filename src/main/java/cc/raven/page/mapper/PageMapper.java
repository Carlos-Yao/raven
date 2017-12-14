package cc.raven.page.mapper;

import cc.raven.page.model.Page;
import org.apache.ibatis.annotations.Param;

/**
 * Created by fenghou on 2017/12/12.
 */
public interface PageMapper {

    Page selectById(@Param("id") int id);

}
