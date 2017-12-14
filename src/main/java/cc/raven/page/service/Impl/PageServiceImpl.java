package cc.raven.page.service.Impl;

import cc.raven.page.mapper.PageMapper;
import cc.raven.page.model.Page;
import cc.raven.page.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by fenghou on 2017/12/12.
 */
@Service("pageService")
public class PageServiceImpl implements PageService {

    @Autowired
    private PageMapper pageMapper;


    public Page selectById(int id) {
        return pageMapper.selectById(id);
    }
}
