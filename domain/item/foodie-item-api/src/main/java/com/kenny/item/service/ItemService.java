package com.kenny.item.service;


import com.kenny.item.pojo.Items;
import com.kenny.item.pojo.ItemsImg;
import com.kenny.item.pojo.ItemsParam;
import com.kenny.item.pojo.ItemsSpec;
import com.kenny.item.pojo.vo.CommentLevelCountsVO;
import com.kenny.item.pojo.vo.ShopcartVO;
import com.kenny.pojo.PagedGridResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("item-api")
public interface ItemService {

    /**
     * Query item details by item ID
     * @param itemId
     * @return
     */
    @GetMapping("item")
    public Items queryItemById(@RequestParam("itemId") String itemId);

    /**
     * Query item image list by item ID
     * @param itemId
     * @return
     */
    @GetMapping("itemsImages")
    public List<ItemsImg> queryItemImgList(@RequestParam("itemId") String itemId);

    /**
     * Query item specifications by item ID
     * @param itemId
     * @return
     */
    @GetMapping("itemsSpecs")
    public List<ItemsSpec> queryItemSpecList(@RequestParam("itemId") String itemId);

    /**
     * Query item parameters by item ID
     * @param itemId
     * @return
     */
    @GetMapping("itemsParam")
    public ItemsParam queryItemParam(@RequestParam("itemId") String itemId);

    /**
     * Query comment level counts for an item by item ID
     * @param itemId
     */
    @GetMapping("countComment")
    public CommentLevelCountsVO queryCommentCounts(@RequestParam("itemId") String itemId);

    /**
     * Query item comments with pagination
     * @param itemId
     * @param level
     * @return
     */
    @GetMapping("pagedComments")
    public PagedGridResult queryPagedComments(@RequestParam("itemId") String itemId, 
                                            @RequestParam("level") Integer level,
                                            @RequestParam("page") Integer page, 
                                            @RequestParam("pageSize") Integer pageSize);

    /**
     * Search items by keywords
     * @param keywords
     * @param sort
     * @param page
     * @param pageSize
     * @return
     */
//    @GetMapping("search")
//    public PagedGridResult searhItems(String keywords, String sort,
//                                              Integer page, Integer pageSize);

    /**
     * Search items by category ID
     * @param catId
     * @param sort
     * @param page
     * @param pageSize
     * @return
     */
//    @GetMapping("catItems")
//    public PagedGridResult searhItems(Integer catId, String sort,
//                                      Integer page, Integer pageSize);

    /**
     * Query latest shopping cart item data by specification IDs (for refreshing shopping cart item data)
     * @param specIds
     * @return
     */
    @GetMapping("getCartBySpecIds")
    public List<ShopcartVO> queryItemsBySpecIds(@RequestParam("specIds") String specIds);

    /**
     * Get detailed information of specification object by specification ID
     * @param specId
     * @return
     */
    @GetMapping("singleItemSpec")
    public ItemsSpec queryItemSpecById(@RequestParam("specId") String specId);

    /**
     * Get main image URL of item by item ID
     * @param itemId
     * @return
     */
    @GetMapping("primaryImage")
    public String queryItemMainImgById(@RequestParam("itemId") String itemId);

    /**
     * Decrease item stock
     * @param specId
     * @param buyCounts
     */
    @PostMapping("decreaseStock")
    public void decreaseItemSpecStock(@RequestParam("specId") String specId, @RequestParam("buyCounts") int buyCounts);
}
