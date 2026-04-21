package me.dmddo.springdeveloper.repository;

import me.dmddo.springdeveloper.dao.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long> {
}
