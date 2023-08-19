package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Repository //Spring Bean으로 등록
@RequiredArgsConstructor
public class MemberRepository {

//    @PersistenceContext //JPA가 제공하는 표준 어노테이션
    private final EntityManager em;

    //     엔티티 팩토리를 직접 주입
//     @PersistenceUnit
//     private EntityManagerFactory emf;

    public void save(Member member) {
        em.persist(member); //.persist() 영속성 컨텍스트에 member객체를 넣어서 트랜잭션이 커밋되는 시점에 db에 반영
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

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
