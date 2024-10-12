package com.site.mylog.service;

import com.site.mylog.domain.Article;
import com.site.mylog.dto.AddArticleRequest;
import com.site.mylog.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
