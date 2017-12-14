package cc.raven.menu.controller;

import cc.raven.menu.dto.MenuDTO;
import cc.raven.menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by fenghou on 2017/12/12.
 */
@Controller
@RequestMapping(value = "/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "getAll.lg", method = RequestMethod.GET)
    @ResponseBody
    public List<MenuDTO> getAll() {
        try {
            return menuService.selectAll();
        } catch (Exception e) {
            throw new RuntimeException("error", e);
        }
    }

}
