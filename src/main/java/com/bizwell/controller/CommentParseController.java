package com.bizwell.controller;

import com.alibaba.fastjson.JSONObject;
import com.bizwell.entity.RequestJson;
import com.bizwell.entity.ResponseJson;
import com.bizwell.service.EmotionParseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by charles on 2017/11/24.
 */
@RestController
public class CommentParseController {

    @Autowired
    private EmotionParseService emotionParseService;

    @RequestMapping(path = "/emotion", method = RequestMethod.POST)
    public ResponseJson postxx(@RequestBody RequestJson requestJson) {
        ResponseJson responseJson = null;
        String requestJsonString = requestJson.getJsonData();
        JSONObject jsonObject = JSONObject.parseObject(requestJsonString);
        if (!jsonObject.containsKey("comment"))
            responseJson = new ResponseJson(4004L, "Error", "缺少参数：comment");
        else {
            String comment = jsonObject.getString("comment").trim();
//            responseJson = emotionParseService.parseCommentString(comment);
        }

        return responseJson;
    }
}
