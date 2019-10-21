package com.zlp.bootlearn.controller;

import com.zlp.bootlearn.model.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String sayHello(){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            builder.append(i+" * "+i+"</br>");
        }
        return builder.toString();
    }

//    @RequestMapping("/word")
//    public Article getName(){
//        Article article = new Article(12L,"张三");
//        log.info("这只是个测试！");
//        return article;
//    }
}
