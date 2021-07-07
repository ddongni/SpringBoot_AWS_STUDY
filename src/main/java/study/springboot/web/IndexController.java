package study.springboot.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import study.springboot.service.posts.PostsService;

/*
 머스테치 스타터 덕분에 컨트롤러에서 문자열을 반환할 때
 앞의 경로와 뒤의 파일 확장자는 자동으로 지정 됨.
 앞의 경로는 src/main/resources/templates로,
 뒤의 파일 확장자는 .mustache가 붙음
 */
@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    /*
     Model
        서버 템플릿 엔진에서 사용할 수 있는 객체 저장
        여기서는 postsService.findAllDesc()로 가져온 결과를 posts로 index.musatche에 전달
     */
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("posts",postsService.findAllDesc());
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }
}
