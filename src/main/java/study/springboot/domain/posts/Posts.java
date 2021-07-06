package study.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/*
 @Entity는 JPA 어노테이션, @Getter와 @NoArgsConstructor는 롬복의 어노테이션
 @NoArgsConstructor
    파라미터가 없는 기본 생성자 생성, public Posts(){}와 같은 효과
 @Entity
    테이블과 링크될 클래스임을 나타냄
    ex) SalesManager.java -> sales_manager table 매칭
*/
@Getter
@NoArgsConstructor
@Entity
public class Posts {

    /*
     @Id
        해당 테이블의 PK 필드
     @GeneratedValue
        PK의 생성 규칙, 스프링 부트 2.0에서는 GenerationType.IDENTITY 옵션을 추가해야만 auto_increment가 됨
        Long 타입은 MySQL 기준으로 bigint 타입이 됨
     @Column
        테이블의 칼럼을 나타내며 굳이 선언하지 않더라도 해당 클래스의 필드는
        모두 칼럼, 사용하는 이유는 기본값 외에 추가로 변경이 필요한 옵션이 있으면 사용
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    /*
     @Builder
        해당 클래스의 빌더 패턴 클래스를 생성, 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
     */
    @Builder
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
