package study.springboot.service.posts;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.springboot.domain.posts.Posts;
import study.springboot.domain.posts.PostsRepository;
import study.springboot.web.dto.PostsListResponseDto;
import study.springboot.web.dto.PostsResponseDto;
import study.springboot.web.dto.PostsSaveRequestDto;
import study.springboot.web.dto.PostsUpdateRequestDto;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id = "+id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id = "+id));
        return new PostsResponseDto(entity);
    }

    /*
    readOnly = true를 주면 트랜잭션 범위는 유지하되, 조회 기능만 남겨두어 조회 속도 개선됨
    등록, 수정, 삭제 기능이 전혀 없는 서비스 메소드에서 사용하는 것 추천
    */
    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        /*
         .map(PostsListResponseDto::new)
            실제로 .map(posts->new PostsListResponseDto(posts))와 같음.
            postRepository 결과로 넘어온 Posts의 Stream을 map을 통해 PostListResponseDto 변환 -> List로 반환하는 메소드
         */
        return postsRepository.findAllDesc().stream().map(PostsListResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id){
        /*
        JpaRepository에서 이미 delete메소드를 지원하고 있음.
        엔티티를 파라미터로 삭제할 수도 있고, deleteById 메소드를 이용하면 id로 삭제 할 수도 잇음.
        존재하는 Posts인지 확인을 위해 엔티티 조회 후 그대로 삭제.
         */
        Posts posts = postsRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id = "+id));
        postsRepository.delete(posts);
    }
}
