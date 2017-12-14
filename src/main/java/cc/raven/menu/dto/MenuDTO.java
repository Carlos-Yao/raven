package cc.raven.menu.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fenghou on 2017/12/12.
 */
public class MenuDTO {
    private int id;
    private String name;
    private int parent;
    private int page;
    private int index;
    private String description;
    private List<MenuDTO> children;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<MenuDTO> getChildren() {
        if (this.children == null) {
            this.children = new ArrayList<MenuDTO>();
        }
        return children;
    }

    public void addChildren(MenuDTO menuDTO) {
        if (this.children == null) {
            this.children = new ArrayList<MenuDTO>();
        }
        this.children.add(menuDTO);
    }
}
