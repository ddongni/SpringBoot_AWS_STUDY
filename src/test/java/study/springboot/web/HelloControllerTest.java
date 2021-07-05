package study.springboot.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/* 테스트를 진행할 때 JUnit에 내장된 실행자 외에 다른 실행자를 실행
여기서는 SpringExtension 실행자 사용
즉, 스프링 부트 테스트와 JUnit 사이에 연결자 역할 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {

    /* 웹 API 테스트 시 사용
    스프링 MVC 테스트의 시작점
    MockMvc를 통해 HTTP GET, POST 등에 대한 API 테스트 가능 */
    @Autowired
    private MockMvc mvc;

    //테스트 메소드 선언
    @Test
    public void returnHello() throws Exception{
        String hello = "hello";

        /* get("/hello")
            MocMvc를 통해 /hello 주소로 HTTP GET 요청
           status().isOk()
            mvc.perform의 결과를 검증, HTTP Header의 Status 검증, isOk()는 200인지 아닌지를 검증
           content().string(hello)
            mvc.peform의 결과를 검증, 응답 본문의 내용 검증 */
        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }
}
