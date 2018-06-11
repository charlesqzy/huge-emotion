package com.bizwell.entity;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by charles on 2017/11/20.
 */
public class WordLabel {
    public static final HashSet<String> labelSet = new HashSet<String>() {{
        add("level_most");
        add("level_very");
        add("level_more");
        add("level_slight");
        add("negative_modifier");
        add("negative_comment");
        add("negative_emotion");
        add("positive_comment");
        add("positive_emotion");
        add("keyword_service");
        add("keyword_attitude");
        add("keyword_environment");
        add("exclamation");
        add("question");
        add("keyword_professional");
        add("keyword_menu_item");
        add("hypothesis");
    }};

    public static final HashMap<String, Double> labelFactor = new HashMap<String, Double>() {
        {
            put("level_most", 2.8);
            put("level_very", 2.2);
            put("level_more", 1.6);
            put("level_slight", 1.2);
            put("negative_modifier", -1.0);
            put("negative_comment", -1.0);
            put("negative_emotion", -1.0);
            put("positive_comment", 1.0);
            put("positive_emotion", 1.0);
            put("keyword_service", 0.0);
            put("keyword_attitude", 0.0);
            put("keyword_environment", 0.0);
            put("exclamation", 1.22);
            put("question", 0.0);
            put("keyword_professional", 0.0);
            put("keyword_menu_item", 0.0);
        }
    };

    /**
     * 评分需要“翻转”的关键词（分数*-1）
     * 例如，性价比很高，由于词库中"高"是基于“价格”设定的，做为 negative_comment，
     * 但是与 性价比组合后，高变成了positive_comment，为了保证评分正确，需乘以-1
     */
    public static final HashSet<String> reverseWords = new HashSet<String>(){{
        add("性价比");
        add("档次");
    }};
}
