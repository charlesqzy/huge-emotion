package com.bizwell;

import com.bizwell.entity.Comment;
import com.bizwell.entity.Sentence;
import com.bizwell.entity.Word;
import com.bizwell.mapper.CommentMapper;
import com.bizwell.service.EmotionParseService;
import org.ansj.domain.Result;
import org.ansj.library.DicLibrary;
import org.ansj.splitWord.analysis.DicAnalysis;
import org.ansj.util.MyStaticValue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nlpcn.commons.lang.tire.domain.Forest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HugeEmotionApplicationTests {

    @Autowired
    private EmotionParseService emotionParseService;

    @Autowired
    private CommentMapper commentMapper;

    @Test
    public void saveParsed() {
        List<Comment> comments = commentMapper.getComments();
        for (Comment comment : comments) {
            emotionParseService.parseComment(comment);
        }
    }

    @Test
    public void parseString() {

        String comment = "不错。就是吃一点就饱了。主要是牛羊肉占地，不好消化呀";

        Result result = DicAnalysis.parse(comment);
        System.out.println(result);

//        String comment = "如果是原价我的评价就会低一些了";
        List<Sentence> sentences = emotionParseService.parseCommentString(comment);
        for (Sentence sentence : sentences) {
            System.out.println(sentence.getContent());
            System.out.println(sentence.getExpression() + " : " + sentence.getScore());
            List<Word> words = sentence.getConcernedWordList();
            for (Word word : words) {
                System.out.println(word.getContent() + " : " + word.getFactor() + " : " + word.getNature());
            }
        }
    }


}

