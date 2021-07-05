package study.springboot.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

    @Test
    public void returnHelloDto() throws Exception{
        String name = "hello";
        int amount = 1000;

        /* param
            API 테스트 할 때 사용될 요청 파라미터 설정
            단, 값은 String만 허용됨, 따라서 숫자/날짜 등의 데이터도 등록할 때는 문자열로 변경해야만 가능
           jsonPath
            JSON 응답값을 필드별로 검증할 수 있는 메소드, $를 기준으로 필드명을 명시
         */
        mvc.perform(get("/hello/dto")
                .param("name", name)
                .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}
