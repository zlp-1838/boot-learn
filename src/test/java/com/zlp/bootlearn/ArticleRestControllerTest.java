package com.zlp.bootlearn;

import com.zlp.bootlearn.controller.ArticleRestController;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//保证使用单元测试时进行事务回滚（把事务交给spring，基于内存的事务回滚，并不是基于数据库），单元测试结束后删除数据库中产生的数据（删表，并不是删除数据）
//@Transactional
@Slf4j
//用来创建Spring的ApplicationContext，保证测试在上下文环境中运行（mock模拟从请求到响应，整个过程需要有个环境来实现，此注解提供）
@SpringBootTest
public class ArticleRestControllerTest {

    /**
     * Mockito是github上使用最广泛的Mock框架，它与JUnit结合使用。Mockito可以创建并配置Mock对象
     */
    private MockMvc mockMvc;

    /**
     * 在使用Mockmvc测试之前先初始化，传入的参数是controller类
     */
    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new ArticleRestController()).build();
    }

    /**
     * 1.开始测试
     * 2.crticle参数是模拟客户端向服务端发送的请求参数
     * 3.以post的形式向/rest/article中发送请求参数，希望得到的状态码是200，希望返回的数据中$.data.author的值是zimug。。。
     * 4.andDo打印结果到控制台，andResult最后返回相应的MvcResult(Controller最后相应的结果)
     * @throws Exception
     */
    @Test
    public void saveArticle() throws Exception {

        String article = "{\n" +
                "    \"id\": 1,\n" +
                "    \"author\": \"zimug\",\n" +
                "    \"title\": \"手摸手教你开发spring boot\",\n" +
                "    \"content\": \"c\",\n" +
                "    \"createTime\": \"2017-07-16 05:23:34\",\n" +
                "    \"reader\":[{\"name\":\"zimug\",\"age\":18},{\"name\":\"kobe\",\"age\":37}]\n" +
                "}";
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.request(HttpMethod.POST, "/rest/article")
                        .contentType("application/json").content(article))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.author").value("zimug"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.reader[0].age").value(18))
                .andDo(print())
                .andReturn();

        log.info(result.getResponse().getContentAsString());

    }
}
