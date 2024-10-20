package com.site.mylog.controller;

import com.site.mylog.domain.Article;
import com.site.mylog.dto.ArticleListViewResponse;
import com.site.mylog.dto.ArticleViewResponse;
import com.site.mylog.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BlogViewController {

    private final BlogService blogService;

    @GetMapping("/articles")
    public String getArticles(Model model){
        /*
        1. findAll() 메서드를 통해 모든 Article 리스트를 가져온다.
        2. 이 리스트를 스트림으로 변환한 후, 각 Article 객체를 ArticleListViewResponse로 변환한다.
        3. 변환된 ArticleListViewResponse 객체들을 다시 리스트로 모아서 모델에 저장하고, 이를 뷰에 전달한다.
        ㄴ> 즉, 블로그 글을 DTO로 변환한 후 리스트로 만들어 뷰에 전달하는 흐름

        스트림과 map()은 복잡한 반복문 대신 간결하고 직관적인 방식으로 데이터 변환 작업을 할 수 있게 도와준다.
         */
        List<ArticleListViewResponse> articles = blogService.findAll().stream()
                .map(ArticleListViewResponse::new)
                .toList();
        model.addAttribute("articles", articles); // 블로그 글 리스트 저장

        return "articleList"; // articleList.html 뷰 조회
    }

    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable Long id, Model model){
        Article article = blogService.findById(id);
        model.addAttribute("article", new ArticleViewResponse(article));
        return "article";
    }

    // 수정/생성 뷰 컨트롤러 메서드 함께 작성
    @GetMapping("/new-article")
    // id 키를 가진 쿼리 파라미터의 값을 id 변수에 매핑 (id는 없을 수도 있음)
    public String newArticle(@RequestParam(required =false) Long id, Model model){
        if(id == null){ // id가 없으면 기본 생성자를 이용해 새 객체 생성
            model.addAttribute("article", new ArticleViewResponse());
        }else { // id가 있으면 수정
            Article article = blogService.findById(id);
            model.addAttribute("article", new ArticleViewResponse(article));
        }
        return "newArticle";
    }

}
