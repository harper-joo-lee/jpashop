package jpabook.jpashop;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

// repository는 entity같은것들을 찾아주는것 (dao랑 비슷함)
@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    public Long save(Member member) {
        em.persist(member);

        return member.getId();
    }

    public Member find(Long id) {
        return em.find(Member.class, id);
    }
}
