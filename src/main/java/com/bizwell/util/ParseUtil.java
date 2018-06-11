package com.bizwell.util;

import com.bizwell.entity.Sentence;
import com.bizwell.entity.Word;
import com.bizwell.entity.WordLabel;
import org.ansj.domain.Term;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.List;

/**
 * Created by charles on 2017/11/24.
 */
public class ParseUtil {

    /**
     * 0:该sentence无意义 1：仅包含keyword 2：包含评价或者情感词
     *
     * @param terms
     * @return
     */
    public static int checkTerms(List<Term> terms) {
        int flag = 0;
        boolean hasNegativeModifier = false;
        boolean hasHypothesis = false;

        if (terms != null && terms.size() > 0) {
            for (Term term : terms) {
                String nature = term.getNatureStr();
                if (!WordLabel.labelSet.contains(nature)) continue;

                if (nature.equals("hypothesis")) hasHypothesis = true;
                if (nature.equals("negative_modifier")) hasNegativeModifier = true;

                if (nature.startsWith("keyword_")) {
                    flag = 1;
                    continue;
                }
                if (nature.endsWith("_comment") || nature.endsWith("_emotion")) {
                    flag = 2;
                    break;
                }
            }
        }
        // 为了限定否定词的影响范围，如果该句子中含有否定词语，则必须同时包含评价词或者情感词，否则该句子废弃
        if (hasNegativeModifier && flag == 1) flag = 0;
        if (hasHypothesis && flag == 2) flag = 0;
        return flag;
    }

    public static double getCommentScore(List<Sentence> sentences) {
        double result = 0.0;
        if (sentences != null && sentences.size() > 0) {
            for (Sentence sentence : sentences) {
                result += sentence.getScore();
            }
        }
        return result;
    }

    public static String getExpression(List<Word> concernedWords) {
        if (concernedWords == null || concernedWords.isEmpty())
            return "0.0";
        String expression = "1.0";
        boolean isExclamatory = false; //是否感叹句
        boolean hasCommentOrEmotion = false; // 标识该句子中是否包含评论或者情感词汇，不包含则不应计算在分数之内
        boolean hasKeyword = false; //是否含有关键词
        boolean reverseKeyword = false; //当期keyword是否是reverse keyword

        if (concernedWords != null && concernedWords.size() > 0) {
            int flag = 0;
            for (Word word : concernedWords) {
                String content = word.getContent();
                String nature = word.getNature();
                double factor = word.getFactor();

                if (nature.equalsIgnoreCase("exclamation"))
                    isExclamatory = true;
                if (nature.endsWith("_comment") || nature.endsWith("_emotion"))
                    hasCommentOrEmotion = true;
                if (nature.startsWith("keyword_")) {
                    hasKeyword = true;
                    if (WordLabel.reverseWords.contains(content)) reverseKeyword = true;
                }


                if (flag == 0) {
                    if (!nature.startsWith("keyword_") && !nature.equals("exclamation"))
                        expression = expression + "*" + factor;
                    if (nature.endsWith("_comment") || nature.endsWith("_emotion")) {
                        flag = 1;
                        if (reverseKeyword && (content.equals("高") || content.equals("低"))) {
                            expression = expression + "*-1.0";
                            reverseKeyword = false;
                        }
                    }
                } else if (flag == 1) {
                    if (!nature.startsWith("keyword_") && !nature.equals("exclamation"))
                        expression = expression + "+" + factor;
                    flag = 0;
                }
            }
        }

        if (isExclamatory && (hasKeyword || hasCommentOrEmotion))
            expression = "(" + expression + ")*" + WordLabel.labelFactor.get("exclamation");
        if (!hasCommentOrEmotion)
            expression = "0.0";
        return expression;
    }

    public static double getSentenceScore(String expression) {
        double f = -9999;
        ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName("js");
        try {
            f = (double) scriptEngine.eval(expression);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        return f;
    }


}
