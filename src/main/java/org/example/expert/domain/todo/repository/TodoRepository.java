package org.example.expert.domain.todo.repository;

import org.example.expert.domain.todo.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    // EntityGraph: JOIN FETCH 처럼 연관된 엔티티를 즉시 로딩하겠다는 의미
    // attributePaths = {"users"}: FROM 의 엔티티와 연관된 {" "}의 엔티티를 즉시 로딩.
    // {"A", "B"} 이렇게 여러 속성도 가능
    // (중요) @Query 없애 더라도 JPA 메서드 이름에 OrderByModifiedAtDesc 이 들어가 있어서 자동으로 인식해서 ORDER BY t.modifiedAt DESC 해줌.
    @EntityGraph(attributePaths = {"users"})
//    @Query("SELECT t FROM Todo t LEFT JOIN FETCH t.user u ORDER BY t.modifiedAt DESC")
    Page<Todo> findAllByOrderByModifiedAtDesc(Pageable pageable);


    // 이건 "WHERE t.id = :todoId"를 해줘야 하기에 @쿼리를 병행해서 사용.
    // 하지만 @EntityGraph 으로 즉시 로딩 하기에 "LEFT JOIN FETCH t.user " 는 필요 없음.
    @EntityGraph(attributePaths = {"users"})
    @Query("SELECT t FROM Todo t " +
            "WHERE t.id = :todoId")
//    @Query("SELECT t FROM Todo t " +
//            "LEFT JOIN FETCH t.user " +
//            "WHERE t.id = :todoId")
    Optional<Todo> findByIdWithUser(@Param("todoId") Long todoId);

    int countById(Long todoId);
}
