package cc.raven.menu.service;

import cc.raven.menu.dto.MenuDTO;

import java.util.List;

/**
 * Created by fenghou on 2017/12/12.
 */
public interface MenuService {

    List<MenuDTO> selectAll() throws Exception;

}
