package com.yozuru.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yozuru.domain.entity.Menu;
import com.yozuru.domain.vo.backstage.MenuVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 菜单权限表(Menu)表数据库访问层
 *
 * @author Yozuru
 * @since 2022-11-29 17:45:18
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    List<String> selectPermsByUserId(Long id);

    List<MenuVo> selectAllRouterMenuTree();

    List<MenuVo> selectRouterMenuTreeByUserId(Long id);

    List<Menu> selectMenuListByRoleId(Long id);

    List<Long> selectMenuIdsByRoleId(Long id);
}

