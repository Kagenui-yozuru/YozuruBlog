package com.yozuru.service.backstage;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yozuru.domain.ResponseResult;
import com.yozuru.domain.dto.PageDto;
import com.yozuru.domain.dto.backstage.TagDto;
import com.yozuru.domain.entity.Tag;
import com.yozuru.domain.vo.PageVo;
import com.yozuru.domain.vo.backstage.TagVo;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * 标签(Tag)表服务接口
 *
 * @author Yozuru
 * @since 2022-12-02 17:24:29
 */

public interface TagService extends IService<Tag> {
    /**
     * 分页查询标签列表
     * @param tagDto 查询条件，pageDto 分页条件
     * @return 标签信息
     */
    ResponseResult<PageVo<TagVo>> getTagPageList(TagDto tagDto, PageDto pageDto);

    /**
     * 根据标签ID查询标签信息
     * @return 标签信息
     */
    ResponseResult<TagVo> getVoById(Long id);

    /**
     * 添加标签的方法
     * @param tagDto 标签信息
     * @return 添加结果
     */

    ResponseResult<Object> addTag(TagDto tagDto);

    /**
     * 根据标签id删除标签的方法
     * @param id 标签id
     * @return 删除结果
     */
    ResponseResult<Object> deleteTag(Long id);


    /**
     * 根据标签id修改标签的方法
     * @param tagDto 标签信息
     * @return 修改结果
     */

    ResponseResult<Object> updateTag(TagDto tagDto);

    /**
     * 获取所有标签的方法
     * @return 全部标签的列表
     */
    ResponseResult<List<TagVo>> getAllTag();
}

