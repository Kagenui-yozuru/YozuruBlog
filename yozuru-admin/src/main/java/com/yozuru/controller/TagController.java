package com.yozuru.controller;

import com.yozuru.domain.ResponseResult;
import com.yozuru.domain.dto.PageDto;
import com.yozuru.domain.dto.backstage.TagDto;
import com.yozuru.domain.vo.PageVo;
import com.yozuru.domain.vo.backstage.TagVo;
import com.yozuru.service.backstage.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Yozuru
 */

@RestController
@RequestMapping("/content/tag")
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping("/list")
    public ResponseResult<PageVo<TagVo>> getPageList(TagDto tagDto,PageDto pageDto) {
        return tagService.getTagPageList(tagDto, pageDto);
    }

    @GetMapping("/{id}")
    public ResponseResult<TagVo> getTagById(@PathVariable Long id) {
        return tagService.getVoById(id);
    }

    @GetMapping("/listAllTag")
    public ResponseResult<List<TagVo>> listAllTag() {
        return tagService.getAllTag();
    }

    @PostMapping
    public ResponseResult<Object> addTag(@RequestBody TagDto tagDto) {
        return tagService.addTag(tagDto);
    }

    @DeleteMapping("/{id}")
    public ResponseResult<Object> deleteTag(@PathVariable("id") Long id) {
        return tagService.deleteTag(id);
    }

    @PutMapping
    public ResponseResult<Object> update(@RequestBody TagDto tagDto) {
        return tagService.updateTag(tagDto);
    }
}
