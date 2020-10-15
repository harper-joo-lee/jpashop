package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    // EntityManager를 생성자로 인젝션
    private final EntityManager em;

    public void save(Member member) {
        // 영속성 컨텍스트는 id값이 pkrk ehlsek.
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    // sql은 테이블을 대상으로 쿼리를 구성하는것이고 JPA는 엔티티를 대상으로 쿼리를 구성한다.
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                        .getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                    .setParameter("name", name)
                    .getResultList();
    }
}
