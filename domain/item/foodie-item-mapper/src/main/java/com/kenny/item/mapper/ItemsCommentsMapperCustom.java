package com.kenny.item.mapper;

import com.kenny.item.pojo.ItemsComments;
import com.kenny.item.pojo.vo.MyCommentVO;
import com.kenny.my.mapper.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItemsCommentsMapperCustom extends MyMapper<ItemsComments> {

    public void saveComments(Map<String, Object> map);

    public List<MyCommentVO> queryMyComments(@Param("paramsMap") Map<String, Object> map);

}