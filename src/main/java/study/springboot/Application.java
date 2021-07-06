package study.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing //JPA Auditing 활성화
/* @SpringBootApplication 으로 인해 스프링 부트의 자동 설정, 스프링 Bean 읽기와 생성을 모두 자동으로 설정
@SpringBootApplication 이 있는 위치부터 설정을 읽어가기 때문에 이 클래스는 항상 프로젝트의 최상단에 위치해야만 함 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        /* SpringApplication.run 으로 인해 내장 WAS 를 실행
        별도로 외부에 WAS 를 두지 않고 애플리케이션을 실행할 때 내부에서 WAS 를 실행
        언제 어디서나 같은 환경에서 스프링 부트를 배포할 수 있음 */
        SpringApplication.run(Application.class, args);
    }
}
