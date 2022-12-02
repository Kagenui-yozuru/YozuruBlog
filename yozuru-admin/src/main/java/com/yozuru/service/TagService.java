package com.yozuru.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yozuru.domain.ResponseResult;
import com.yozuru.domain.dto.PageDto;
import com.yozuru.domain.dto.TagDto;
import com.yozuru.domain.entity.Tag;
import com.yozuru.domain.vo.PageVo;
import com.yozuru.domain.vo.TagVo;
import org.springframework.validation.annotation.Validated;

/**
 * 标签(Tag)表服务接口
 *
 * @author Yozuru
 * @since 2022-12-02 17:24:29
 */
@Validated
public interface TagService extends IService<Tag> {

    ResponseResult<PageVo<TagVo>> selectTagList(TagDto tagDto, PageDto pageDto);

    ResponseResult<TagVo> getVoById(Long id);

    @Validated(TagDto.Add.class)
    ResponseResult<Object> addTag(TagDto tagDto);

    ResponseResult<Object> deleteTag(Long id);

    @Validated(TagDto.Edit.class)
    ResponseResult<Object> updateTag(TagDto tagDto);
}

