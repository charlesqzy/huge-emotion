package com.bizwell.Init;

import org.ansj.library.DicLibrary;
import org.ansj.util.MyStaticValue;
import org.nlpcn.commons.lang.tire.domain.Forest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


/**
 * Created by charles on 2017/10/24.
 * 初始化分词词典
 */
@Component
public class DicEmotionInit implements CommandLineRunner {
    @Override
    public void run(String... strings) throws Exception {
        Forest forest = DicLibrary.get("dic_mysql");
        MyStaticValue.putLibrary(DicLibrary.DEFAULT, "", forest);
    }

}
