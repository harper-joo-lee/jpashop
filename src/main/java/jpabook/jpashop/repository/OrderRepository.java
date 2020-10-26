package jpabook.jpashop.repository;


import jpabook.jpashop.domain.Order;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.hibernate.internal.CriteriaImpl;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    // JPA를 사용하면 어플리케이션과 데이터베이스 사이에 영속성 컨텍스트라는 개념을 두고
    // 데이터를 관리한다. EntityManager : 엔티티를 저장,수정,삭제,조회 등 엔티티와 관련된 작업을 수행
    private final EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }

    // 첫번째 방법
//   public List<Order> findAll(OrderSearch orderSearch) {
//
//     String jpql = "selecte o from Order o join o.member m";
//     boolean isFirstCondition = true;
//
//     //주문 상태검색
//       if(orderSearch.getOrderStatus() != null) {
//           if(isFirstCondition) {
//               jpql += "where";
//               isFirstCondition = false;
//           } else {
//               jpql += "o.status = :status";
//           }
//       }
//
//        // 회원 이름 검색
//       if(StringUtils.hasText(orderSearch.getMemberName())) {
//           if(isFirstCondition) {
//               jpql += "where";
//               isFirstCondition = false;
//           } else {
//               jpql += "and";
//           }
//           jpql += "m.name like :name";
//       }
//
//       TypedQuery<Order> query =em.createQuery(jpql, Order.class)
//                 .setMaxResults(1000);
//
//       if(orderSearch.getOrderStatus() != null ) {
//           query = query.setParameter("status", orderSearch.getOrderStatus());
//       }
//       if(StringUtils.hasText(orderSearch.getMemberName())) {
//           query = query.setParameter("name", orderSearch.getMemberName());
//       }
//
//       return query.getResultList();
//   }

    public List<Order> findAllByString(OrderSearch orderSearch) {

        String jpql = "selecte o from Order o join o.member m";
        boolean isFirstCondition = true;

        //주문 상태검색
        if(orderSearch.getOrderStatus() != null) {
            if(isFirstCondition) {
                jpql += "where";
                isFirstCondition = false;
            } else {
                jpql += "o.status = :status";
            }
        }

        // 회원 이름 검색
        if(StringUtils.hasText(orderSearch.getMemberName())) {
            if(isFirstCondition) {
                jpql += "where";
                isFirstCondition = false;
            } else {
                jpql += "and";
            }
            jpql += "m.name like :name";
        }

        TypedQuery<Order> query =em.createQuery(jpql, Order.class)
                .setMaxResults(1000);

        if(orderSearch.getOrderStatus() != null ) {
            query = query.setParameter("status", orderSearch.getOrderStatus());
        }
        if(StringUtils.hasText(orderSearch.getMemberName())) {
            query = query.setParameter("name", orderSearch.getMemberName());
        }

        return query.getResultList();
    }

    /*
    *   JPA Criteria (권장하는 방법은 아님 )
    *   치명적인 단점 : 실무에 적합하지 않다.
    * */
    public List<Order> findAllByCriteria(OrderSearch orderSearch) {
       CriteriaBuilder cb = em.getCriteriaBuilder();
       CriteriaQuery<Order> cq = cb.createQuery(Order.class);

       Root<Order> o = cq.from(Order.class);
       Join<Object, Object> m = o.join("memeber", JoinType.INNER);

       List<Predicate> criteria = new ArrayList<>();

       // 주문 상태 검색
       if ( orderSearch.getOrderStatus() != null) {
          Predicate status = cb.equal(o.get("status"), orderSearch.getOrderStatus());
           criteria.add(status);
       }

       //회원 이름 검색
        if(StringUtils.hasText(orderSearch.getMemberName())) {
            Predicate name =
                        cb.like(m.<String>get("name"), "%" + orderSearch.getMemberName() + "%");
            criteria.add(name);
        }

        cq.where(cb.and(criteria.toArray(new Predicate[criteria.size()])));
        TypedQuery<Order> query = em.createQuery(cq).setMaxResults(1000);
        return query.getResultList();
    }


}
