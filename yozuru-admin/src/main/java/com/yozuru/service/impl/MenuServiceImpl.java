package com.yozuru.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yozuru.domain.constants.SystemConstant;
import com.yozuru.domain.entity.Menu;
import com.yozuru.domain.vo.MenuVo;
import com.yozuru.mapper.MenuMapper;
import com.yozuru.service.MenuService;
import com.yozuru.utils.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 菜单权限表(Menu)表服务实现类
 *
 * @author Yozuru
 * @since 2022-11-29 17:45:18
 */

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Override
    public List<String> selectPermsByUserId(Long id) {
        //如果是超级管理员，返回所有权限关键字
        if(SecurityUtils.isAdmin()){
            LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.select(Menu::getPerms)
                    .in(Menu::getMenuType, SystemConstant.MENU, SystemConstant.BUTTON)
                    .eq(Menu::getStatus, SystemConstant.STATUS_NORMAL);
            List<Menu> list = list(queryWrapper);
            return list.stream()
                    .map(Menu::getPerms)
                    .collect(Collectors.toList());
        }
        //根据用户id查询权限关键字
        return baseMapper.selectPermsByUserId(id);
    }

    @Override
    public List<MenuVo> selectRouterMenuTreeByUserId(Long id) {
        List<MenuVo> menuVoS = null;
        //如果是超级管理员，返回所有菜单
        if(SecurityUtils.isAdmin()){
            menuVoS = baseMapper.selectAllRouterMenuTree();
        }else {
            menuVoS = baseMapper.selectRouterMenuTreeByUserId(id);
        }
        //构建菜单树，菜单树的根节点为0，最多只有两层。


        return buildMenuTree(menuVoS);
    }

    /**
     * 通过菜单节点列表，构建菜单树
     * @param menuVoList  菜单节点列表
     * @return 菜单树
     */
    private List<MenuVo> buildMenuTree(List<MenuVo> menuVoList) {
        return menuVoList.stream()
                //过滤出父菜单
                .filter(menuVo -> Objects.equals(menuVo.getParentId(), 0L))
                //把该结点作为父结点，查找该结点的子结点并赋值
                .peek(menuVo -> menuVo.setChildren(getChild(menuVo.getId(), menuVoList)))
                //返回结果
                .collect(Collectors.toList());
    }

    /**
     * 递归查找子菜单
     * @param id  父节点id
     * @param menuVoList  查询的菜单列表
     * @return  子菜单列表
     */
    private List<MenuVo> getChild(Long id, List<MenuVo> menuVoList) {
        return menuVoList.stream()
                //过滤出该父节点的子节点
                .filter(menuVo -> Objects.equals(menuVo.getParentId(), id))
                //把该子结点作为父结点，递归查找出该子节点的的子结点并赋值
                .peek(childrenMenuVo -> childrenMenuVo.setChildren(getChild(childrenMenuVo.getId(), menuVoList)))
                .collect(Collectors.toList());
    }
}

