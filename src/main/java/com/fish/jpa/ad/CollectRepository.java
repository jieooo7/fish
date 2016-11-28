package com.fish.jpa.ad;

import com.fish.model.entity.ad.AdCollect;
import com.fish.model.entity.ad.Images;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by thy on 16-11-27.
 */

public interface CollectRepository extends CrudRepository<AdCollect, Long> {

    long countByUserIdAndAdId(int user_id,int ad_id);
//    带前缀的机制 findXXBy , readAXXBy , queryXXBy , countXXBy , getaXXBy 自动解析的其余部分
}

//public interface UserRepository extends JpaRepository<User, Long> {
//支持本地查询，所谓本地查询，就是使用原生的sql语句（根据数据库的不同，在sql的语法或结构方面可能有所区别）进行查询数据库的操作。
//    @Query(value = "SELECT * FROM USERS WHERE LASTNAME = ?1",
//            countQuery = "SELECT count(*) FROM USERS WHERE LASTNAME = ?1",  count作为数量
//            nativeQuery = true)
//    Page<User> findByLastname(String lastname, Pageable pageable);
//}
//    This also works with named native queries by adding the suffix .count to a copy of your query. Be aware that you probably must register a result set mapping for your count query, though.



//    MonetaryAmount amount = new MonetaryAmount(200.0, Currencies.DOLLAR);
//    List<Customer> customers = customerRepository.findAll(
//            where(isLongTermCustomer()).or(hasSalesOfMoreThan(amount)));
//    As you can see, Specifications offers some glue-code methods to chain and combine Specification instances. Thus extending your data access layer is just a matter of creating new Specification implementations and combining them with ones already existing.