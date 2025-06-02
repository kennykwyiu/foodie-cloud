package com.kenny.item.controller;


import com.kenny.controller.BaseController;
import com.kenny.item.pojo.Items;
import com.kenny.item.pojo.ItemsImg;
import com.kenny.item.pojo.ItemsParam;
import com.kenny.item.pojo.ItemsSpec;
import com.kenny.item.pojo.vo.CommentLevelCountsVO;
import com.kenny.item.pojo.vo.ItemInfoVO;
import com.kenny.item.pojo.vo.ShopcartVO;
import com.kenny.item.service.ItemService;
import com.kenny.pojo.IMOOCJSONResult;
import com.kenny.pojo.PagedGridResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Item API", tags = {"Item information display related interfaces"})
@RestController
@RequestMapping("items")
public class ItemsController extends BaseController {

    @Autowired
    private ItemService itemService;

    @ApiOperation(value = "Query item details", notes = "Query item details", httpMethod = "GET")
    @GetMapping("/info/{itemId}")
    public IMOOCJSONResult info(
            @ApiParam(name = "itemId", value = "Item ID", required = true)
            @PathVariable String itemId) {

        if (StringUtils.isBlank(itemId)) {
            return IMOOCJSONResult.errorMsg(null);
        }

        Items item = itemService.queryItemById(itemId);
        List<ItemsImg> itemImgList = itemService.queryItemImgList(itemId);
        List<ItemsSpec> itemsSpecList = itemService.queryItemSpecList(itemId);
        ItemsParam itemsParam = itemService.queryItemParam(itemId);

        ItemInfoVO itemInfoVO = new ItemInfoVO();
        itemInfoVO.setItem(item);
        itemInfoVO.setItemImgList(itemImgList);
        itemInfoVO.setItemSpecList(itemsSpecList);
        itemInfoVO.setItemParams(itemsParam);

        return IMOOCJSONResult.ok(itemInfoVO);
    }

    @ApiOperation(value = "Query item comment levels", notes = "Query item comment levels", httpMethod = "GET")
    @GetMapping("/commentLevel")
    public IMOOCJSONResult commentLevel(
            @ApiParam(name = "itemId", value = "Item ID", required = true)
            @RequestParam String itemId) {

        if (StringUtils.isBlank(itemId)) {
            return IMOOCJSONResult.errorMsg(null);
        }

        CommentLevelCountsVO countsVO = itemService.queryCommentCounts(itemId);

        return IMOOCJSONResult.ok(countsVO);
    }

    @ApiOperation(value = "Query item comments", notes = "Query item comments", httpMethod = "GET")
    @GetMapping("/comments")
    public IMOOCJSONResult comments(
            @ApiParam(name = "itemId", value = "Item ID", required = true)
            @RequestParam String itemId,
            @ApiParam(name = "level", value = "Comment level", required = false)
            @RequestParam Integer level,
            @ApiParam(name = "page", value = "Page number for next page", required = false)
            @RequestParam Integer page,
            @ApiParam(name = "pageSize", value = "Number of items per page", required = false)
            @RequestParam Integer pageSize) {

        if (StringUtils.isBlank(itemId)) {
            return IMOOCJSONResult.errorMsg(null);
        }

        if (page == null) {
            page = 1;
        }

        if (pageSize == null) {
            pageSize = COMMON_PAGE_SIZE;
        }

        PagedGridResult grid = itemService.queryPagedComments(itemId,
                                                                level,
                                                                page,
                                                                pageSize);

        return IMOOCJSONResult.ok(grid);
    }

    // Used to refresh shopping cart data (mainly item prices) for users who haven't logged in for a long time, similar to JD and Taobao
    @ApiOperation(value = "Find latest item data by specification IDs", notes = "Find latest item data by specification IDs", httpMethod = "GET")
    @GetMapping("/refresh")
    public IMOOCJSONResult refresh(
            @ApiParam(name = "itemSpecIds", value = "Concatenated specification IDs", required = true, example = "1001,1003,1005")
            @RequestParam String itemSpecIds) {

        if (StringUtils.isBlank(itemSpecIds)) {
            return IMOOCJSONResult.ok();
        }

        List<ShopcartVO> list = itemService.queryItemsBySpecIds(itemSpecIds);

        return IMOOCJSONResult.ok(list);
    }
}
