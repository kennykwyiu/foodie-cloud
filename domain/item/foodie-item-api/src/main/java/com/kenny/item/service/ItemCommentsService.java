package com.kenny.item.service;

import com.kenny.pojo.PagedGridResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("item-comments-api")
public interface ItemCommentsService {
    /**
     * Query my comments with pagination
     * @param userId
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("myComments")
    public PagedGridResult queryMyComments(@RequestParam("userId") String userId, 
                                         @RequestParam(value = "page", required = false) Integer page, 
                                         @RequestParam(value = "pageSize", required = false) Integer pageSize);
    @PostMapping("saveComments")
    public void saveComments(@RequestBody Map<String,Object> map);
}
