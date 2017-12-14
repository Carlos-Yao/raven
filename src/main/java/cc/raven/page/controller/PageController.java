package cc.raven.page.controller;

import cc.raven.page.model.Page;
import cc.raven.page.service.PageService;
import cc.raven.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by fenghou on 2017/12/12.
 */
@RequestMapping("/page")
@Controller
public class PageController {

    @Autowired
    private PageService pageService;

    @RequestMapping(value = "/getPage/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Result getById(@PathVariable("id") int id) {
        try {
            Page page = pageService.selectById(id);
            return new Result(200, "获取数据成功", page);
        } catch (Throwable e) {
            throw new RuntimeException("error", e);
        }
    }
}
