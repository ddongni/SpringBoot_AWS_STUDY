package study.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// JpaRepository<Entity 클래스, PK타입>을 상속하면 기본적인 CRUD 메소드가 자동 생성됨
public interface PostsRepository extends JpaRepository<Posts, Long> {

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}
