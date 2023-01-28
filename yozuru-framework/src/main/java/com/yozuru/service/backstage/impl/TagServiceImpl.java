package com.yozuru.service.backstage.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yozuru.domain.ResponseResult;
import com.yozuru.domain.dto.PageDto;
import com.yozuru.domain.dto.backstage.TagDto;
import com.yozuru.domain.vo.PageVo;
import com.yozuru.domain.vo.backstage.TagVo;
import com.yozuru.mapper.TagMapper;
import com.yozuru.domain.entity.Tag;
import com.yozuru.service.backstage.TagService;
import com.yozuru.utils.BeanCopyUtil;
import io.jsonwebtoken.lang.Strings;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 标签(Tag)表服务实现类
 *
 * @author Yozuru
 * @since 2022-12-02 17:24:29
 */

@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Override
    public ResponseResult<PageVo<TagVo>> getTagPageList(TagDto tagDto, PageDto pageDto) {
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        // 规定返回的字段
        queryWrapper.select(Tag::getId, Tag::getName, Tag::getRemark,Tag::getUpdateTime)
                //查询条件
                .eq(Strings.hasText(tagDto.getName()),Tag::getName, tagDto.getName())
                .eq(Strings.hasText(tagDto.getRemark()),Tag::getRemark, tagDto.getRemark());
        // 分页查询
        Page<Tag> pageObj = new Page<>(pageDto.getPageNum(), pageDto.getPageSize());
        pageObj = page(pageObj, queryWrapper);
        // 封装返回结果
        List<Tag> tags = pageObj.getRecords();
        List<TagVo> tagVos = BeanCopyUtil.copyBeanList(tags, TagVo.class);
        PageVo<TagVo> pageVo = new PageVo<>(tagVos,pageObj.getTotal());
        // 返回结果
        return ResponseResult.success(pageVo);
    }

    @Override
    public ResponseResult<TagVo> getVoById(Long id) {
        Tag tag = getById(id);
        TagVo tagVo = BeanCopyUtil.copyBean(tag, TagVo.class);
        return ResponseResult.success(tagVo);
    }

    @Override
    public ResponseResult<List<TagVo>> getAllTag() {
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        // 规定返回的字段
        queryWrapper.select(Tag::getId, Tag::getName);
        List<Tag> list = list(queryWrapper);
        List<TagVo> tagVos = BeanCopyUtil.copyBeanList(list, TagVo.class);
        return ResponseResult.success(tagVos);
    }

    @Override
    public ResponseResult<Object> addTag(TagDto tagDto) {
        Tag tag = BeanCopyUtil.copyBean(tagDto, Tag.class);
        save(tag);
        return ResponseResult.success();
    }

    @Override
    public ResponseResult<Object> deleteTag(Long id) {
        removeById(id);
        return ResponseResult.success();
    }

    @Override
    public ResponseResult<Object> updateTag(TagDto tagDto) {
        updateById(BeanCopyUtil.copyBean(tagDto, Tag.class));
        return ResponseResult.success();
    }
}

