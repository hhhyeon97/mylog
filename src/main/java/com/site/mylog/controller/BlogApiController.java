package com.site.mylog.controller;

import com.site.mylog.domain.Article;
import com.site.mylog.dto.AddArticleRequest;
import com.site.mylog.dto.ArticleResponse;
import com.site.mylog.dto.UpdateArticleRequest;
import com.site.mylog.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RestController// HTTP Response Body에 객체 데이터를 JSON 형식으로 반환하는 컨트롤러
public class BlogApiController {

    private final BlogService blogService;

    // #1 글 작성
    // HTTP 메소드가 POST일 때 전달받은 URL과 동일하면 메소드로 매핑
    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request,
                                              Principal principal) {
        Article savedArticle = blogService.save(request, principal.getName());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle);
    }


    // #2 전체 글 조회
    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles(){
        List<ArticleResponse> articles = blogService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(articles);
    }

    // #3 특정 글 조회
    @GetMapping("/api/articles/{id}")
    // URL 경로에서 값 추출
    public ResponseEntity<ArticleResponse> findArticleById(@PathVariable Long id){
        Article article = blogService.findById(id);

        return ResponseEntity.ok().body(new ArticleResponse(article));
    }

    // #4 글 삭제
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticleById(@PathVariable Long id){
        blogService.delete(id);

        return ResponseEntity.ok().build();
    }

    // #5 글 수정
    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticleById(@PathVariable Long id,
                                                     @RequestBody UpdateArticleRequest request){
        Article updatedArticle = blogService.update(id, request);

        return ResponseEntity.ok().body(updatedArticle);

    }
}
