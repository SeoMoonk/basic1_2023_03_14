package com.ll.basic1.boundedContext.article.repository;

import com.ll.basic1.boundedContext.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;


//이 클래스에는 @Repository를 따로 안해줘도 jpa에서 상속받았다는 것 때문에 repository로 인식된다.
public interface ArticleRepository extends JpaRepository<Article, Long> {



}
