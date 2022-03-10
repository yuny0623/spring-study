package jpabook.jpashop;

import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        // 얘는 하나만 만들어놔야 한다.
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        //code 여기에 이제 작성
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //JPA의 목적은 객체를 컬렉션처럼 다루기 위함이다.
        try {
            Order order = new Order();
            order.addOrderItem(new OrderItem());


            tx.commit(); //이거 해줘야 반영된다.
        }catch(Exception e){
            tx.rollback();
        }finally{
            em.close(); //자원 다쓰면 꼭 반환하세요.
        }
        emf.close(); //was가 내려갈때 factory도 내려야 한다.
    }
}
