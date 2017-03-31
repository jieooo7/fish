package com.fish.jpa.ad;

import com.fish.model.entity.ad.Ad;
import com.fish.test.PartPara;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by thy on 16-11-3.
 */
@CacheConfig(cacheNames = "ads")
public interface AdRepository extends PagingAndSortingRepository<Ad, Long> {
//    @Cacheable(key = "#pwId", value = { "pwId" })
    Page<Ad> findAll(Pageable pageable);
    Ad findById(int id);

    @Query(value = "select a.id as aid ,a.title as titles from ad a where a.id =?1",nativeQuery = true)
    List<PartPara> findAFR(int id);//返回定义的

//    List<Object[]> findAFR(int id);返回对象数组

//    @Query("select u.id, LENGTH(u.firstname) as fn_len from User u where u.lastname like ?1%")
//    List<Object[]> findByAsArrayAndSort(String lastname, Sort sort);



//    需要注意的是当一个支持缓存的方法在对象内部被调用时是不会触发缓存功能的
//    Sort sort = new Sort(Direction.DESC, "id");
//    Pageable pageable = new PageRequest(page, size, sort);
//    return blogRepository.findAll(pageable);


//    @RequestMapping(value = "", method=RequestMethod.GET)
//    public Page<Blog> getEntryByPageable(@PageableDefault(value = 15, sort = { "id" }, direction = Sort.Direction.DESC)
//                                                 Pageable pageable) {
//        return blogRepository.findAll(pageable);


//    page，第几页，从0开始，默认为第0页
//    size，每一页的大小，默认为20
//    sort，排序相关的信息，以property,property(,ASC|DESC)的方式组织，例如sort=firstname&sort=lastname,desc表示在按firstname正序排列基础上按lastname倒序排列
//    }


//    "content":[
//    {"id":123,"title":"blog122","content":"this is blog content"},
//    {"id":122,"title":"blog121","content":"this is blog content"},
//    {"id":121,"title":"blog120","content":"this is blog content"},
//    {"id":120,"title":"blog119","content":"this is blog content"},
//    {"id":119,"title":"blog118","content":"this is blog content"},
//    {"id":118,"title":"blog117","content":"this is blog content"},
//    {"id":117,"title":"blog116","content":"this is blog content"},
//    {"id":116,"title":"blog115","content":"this is blog content"},
//    {"id":115,"title":"blog114","content":"this is blog content"},
//    {"id":114,"title":"blog113","content":"this is blog content"},
//    {"id":113,"title":"blog112","content":"this is blog content"},
//    {"id":112,"title":"blog111","content":"this is blog content"},
//    {"id":111,"title":"blog110","content":"this is blog content"},
//    {"id":110,"title":"blog109","content":"this is blog content"},
//    {"id":109,"title":"blog108","content":"this is blog content"}],
//            "last":false,
//            "totalPages":9,
//            "totalElements":123,
//            "size":15,
//            "number":0,
//            "first":true,
//            "sort":[{
//        "direction":"DESC",
//                "property":"id",
//                "ignoreCase":false,
//                "nullHandling":"NATIVE",
//                "ascending":false
//    }],
//            "numberOfElements":15
//}
//通过查询结果，我们可以知道：
//
//        以id倒序排列的10条数据
//        当前页不是最后一页，后面还有数据
//        总共有9页
//        每页大小为15
//        当前页为第0页
//        当前页是第一页
//        当前页是以id倒序排列的
//        当前页一共有15条数据



}