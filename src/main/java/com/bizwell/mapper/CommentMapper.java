package com.bizwell.mapper;

import com.bizwell.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by charles on 2017/10/25.
 */
@Mapper
public interface CommentMapper {

    List<Comment> getComments();

    void updateComment(Comment comment);

}
