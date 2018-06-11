package com.bizwell.service;

import com.bizwell.entity.Comment;
import com.bizwell.entity.Sentence;
import com.bizwell.entity.Word;
import com.bizwell.entity.WordLabel;
import com.bizwell.mapper.CommentMapper;
import com.bizwell.mapper.SentenceMapper;
import com.bizwell.mapper.WordMapper;
import com.bizwell.util.ParseUtil;
import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.DicAnalysis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by charles on 2017/11/20.
 */
@Service
public class EmotionParseService {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private SentenceMapper sentenceMapper;
    @Autowired
    private WordMapper wordMapper;

    public void parseComment(Comment comment) {

        String commentString = comment.getComments();
        String[] sentenceStrings = commentString.replace("!", "! ").replace("！", "！ ").split("\r|\n|,|，|。| |~|～|；|;");
        List<Sentence> sentences = new ArrayList<>();

        int flag = 0;  // 标记当前Sentence分词后 0:该sentence无意义 1：仅包含keyword 2：包含评价或者情感词
        String accumulatedSentence = "";

        for (int i = 0; i < sentenceStrings.length; i++) {
            if (sentenceStrings[i].trim().equals(""))
                continue;
            if (flag == 1) {
                if (accumulatedSentence.endsWith("!") || accumulatedSentence.endsWith("！")) {  // 遇到感叹句，不再往下追寻
                    flag = 0;
                    continue;
                } else
                    accumulatedSentence += sentenceStrings[i];
            } else
                accumulatedSentence = sentenceStrings[i];

            // 解析word
            Result result = DicAnalysis.parse(accumulatedSentence);
            List<Term> terms = result.getTerms();
            flag = ParseUtil.checkTerms(terms);
            if (flag == 2) {
                List<Word> concernedWords = getConcernedWords(terms);
                // 设置sentence相关信息
                String expression = ParseUtil.getExpression(concernedWords);
                Sentence sentence = new Sentence();
                sentence.setCommentId(comment.getId());
                sentence.setContent(accumulatedSentence);
                sentence.setExpression(expression);
                sentence.setScore(ParseUtil.getSentenceScore(expression));
                sentenceMapper.save(sentence); // 持久化sentence，同时获得sentenceId

                for (Word word : concernedWords)
                    word.setSentenceId(sentence.getId());    // 设置word对应的sentenceId

                if (concernedWords.size() > 0) {
                    sentences.add(sentence);
                    wordMapper.batchSave(concernedWords);    // 保存word
                }
            }
        }
        // 计算Comment score
        if (sentences.size() > 0) {
            double commentScore = ParseUtil.getCommentScore(sentences);
            comment.setScore(commentScore);
            commentMapper.updateComment(comment);
        }
    }

    /**
     * @param commmentString
     * @return
     */
    public List<Sentence> parseCommentString(String commmentString) {
        String[] sentenceStrings = standardizedCommentString(commmentString);
        List<Sentence> sentences = doParse(sentenceStrings);
        return sentences;
    }

    /**
     * 逐句解析评价句子，仅关注有效的句子，丢弃无效的句子。
     * 一个有效的句子，必须包含评价词或者情感词。
     *
     * @param sentenceStrings
     * @return
     */
    private List<Sentence> doParse(String[] sentenceStrings) {
        List<Sentence> sentences = new ArrayList<>();
        int maxCount = 20;
        String accumulatedSentence = "";
        // 标记当前Sentence分词后 0:该sentence无意义 1：仅包含keyword 2：包含评价或者情感词
        for (int i = 0; i < sentenceStrings.length; i++) {
            String currentSentence = sentenceStrings[i].trim();
            if (currentSentence.equals("")) continue;

            accumulatedSentence += currentSentence;
            int sentenceLength = accumulatedSentence.length();
            // 解析word
            List<Term> terms = DicAnalysis.parse(accumulatedSentence).getTerms();
            int flag = ParseUtil.checkTerms(terms);

            switch (flag) {
                case 0:
                    accumulatedSentence = "";
                    break;
                case 1:
                    if (sentenceLength > maxCount || accumulatedSentence.endsWith("!") || accumulatedSentence.endsWith("！") || accumulatedSentence.endsWith("。"))
                        accumulatedSentence = "";
                    break;
                case 2:
                    List<Word> concernedWords = getConcernedWords(terms);
                    // 设置sentence相关信息
                    String expression = ParseUtil.getExpression(concernedWords);
                    Sentence sentence = new Sentence();
                    sentence.setContent(accumulatedSentence);
                    sentence.setExpression(expression);
                    sentence.setScore(ParseUtil.getSentenceScore(expression));
                    if (concernedWords.size() > 0) {
                        sentence.setConcernedWordList(concernedWords);
                        sentences.add(sentence);
                    }
                    accumulatedSentence = "";
                    break;
                default:
                    break;
            }
        }
        return sentences;
    }

    private String[] standardizedCommentString(String commmentString) {
        String[] result = commmentString.replace("!", "! ")
                .replace("！", "！ ")
                .split("\n|,|，|。| |~|～|；|;");
        return result;
    }


    private List<Word> getConcernedWords(List<Term> terms) {
        List<Word> concernedWords = new ArrayList<>();
        for (Term term : terms) {
            String nature = term.getNatureStr();
            if (WordLabel.labelSet.contains(nature)) {
                Word word = new Word();
                word.setContent(term.getName());
                word.setNature(term.getNatureStr());
                word.setFactor(WordLabel.labelFactor.get(term.getNatureStr()));
                concernedWords.add(word);
            }
        }
        return concernedWords;
    }

}
