package cc.raven.menu.service.impl;

import cc.raven.menu.dto.MenuDTO;
import cc.raven.menu.mapper.MenuMapper;
import cc.raven.menu.model.Menu;
import cc.raven.menu.service.MenuService;
import cc.raven.util.TransfromUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fenghou on 2017/12/12.
 */
@Service("menuService")
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Test
    public List<MenuDTO> selectAll() throws Exception {
        List<Menu> menuList = menuMapper.selectAll();
        if (CollectionUtils.isEmpty(menuList)) {
            return new ArrayList<MenuDTO>();
        }
        List<MenuDTO> menuDTOList = new ArrayList<MenuDTO>();
        List<MenuDTO> result = new ArrayList<MenuDTO>();
        Map<Integer, MenuDTO> menuDTOMap = new HashMap<Integer, MenuDTO>();
        for (Menu menu : menuList) {
            MenuDTO menuDTO = TransfromUtil.transform(menu, MenuDTO.class);
            menuDTOMap.put(menuDTO.getId(), menuDTO);
            menuDTOList.add(menuDTO);
        }
        for (MenuDTO menuDTO : menuDTOList) {
            Integer parentId = menuDTO.getParent();
            if (parentId == 0) {
                result.add(menuDTO);
            } else {
                MenuDTO parent = menuDTOMap.get(parentId);
                parent.addChildren(menuDTO);
            }
        }
        return result;
    }
}
