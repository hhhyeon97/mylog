package com.site.mylog.service;

import com.site.mylog.domain.Article;
import com.site.mylog.dto.AddArticleRequest;
import com.site.mylog.dto.UpdateArticleRequest;
import com.site.mylog.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor // final이 붙거나 @NotNull이 붙은 필드의 생성자 추가
@Service
public class BlogService {

    private final BlogRepository blogRepository;

    // 블로그 글 추가 메소드
    public Article save(AddArticleRequest request){
        return blogRepository.save(request.toEntity());
    }
    
    // db에 저장되어 있는 모든 글을 가져오는 메소드
    public List<Article> findAll(){
        return blogRepository.findAll();
    }

    // 특정 글 하나 조회 메서드
    public Article findById(Long id){
        return blogRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found : "+id));
    }

    // 글 삭제 메서드
    public void delete(Long id){
        blogRepository.deleteById(id);
    }

    // 글 수정 메소드
    @Transactional // - > 매칭한 메서드를 하나의 트랜잭션으로 묶는다.
    public Article update(long id, UpdateArticleRequest request) {
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));

        article.update(request.getTitle(), request.getContent());

        return article;
    }
}
