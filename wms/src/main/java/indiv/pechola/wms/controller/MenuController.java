package indiv.pechola.wms.controller;


import indiv.pechola.wms.common.Result;
import indiv.pechola.wms.entity.Menu;
import indiv.pechola.wms.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author pechola
 * @since 2024-07-07
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/list")
    public Result list(@RequestParam String roleId) {
        List<Menu> list = menuService.lambdaQuery().like(Menu::getMenuright, roleId).list();
        return Result.success(list);
    }
}
