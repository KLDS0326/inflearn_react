package org.zerock.apiserver.repository;


import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.zerock.apiserver.domain.Product;

import java.util.Optional;
import java.util.UUID;

@SpringBootTest
@Log4j2
public class ProductRepositoryTests {
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testInsert() {
        Product product = Product.builder().pname("Test").pdesc("Test desc").price(1000).build();
        product.addImageString(UUID.randomUUID()+"_" + "IMAGE1.jpg");
        product.addImageString(UUID.randomUUID()+"_" + "IMAGE2.jpg");

        productRepository.save(product);


    }

    @Transactional
    @Test
    public void testRead() {
        Long pno = 1L;
        Optional<Product> result = productRepository.findById(pno);
        Product product = result.orElseThrow();
        log.info(product);

        log.info(product.getImageList());
    }

    // 한번에 조인해서 가져옴. 이것때문에 ibatis를 못버리는 것임. 자원이 유리함. 엘리먼트 그래프를 쓰면 이렇게 됨.
    @Test
    public void testRead2() {
        Long pno = 1L;
        Optional<Product> result = productRepository.selectOne(pno);
        Product product = result.orElseThrow();
        log.info(product);

        log.info(product.getImageList());
    }

    @Commit
    @Transactional
    @Test
    public void testDelete() {
        Long pno = 1L;
        productRepository.updateToDelete(pno,true);
    }


    //Element collection을 쓰면, 다뤄야하는 엔티티의 수가 줄어듦
    @Test
    public void testUpdate() {
        Product product = productRepository.selectOne(1L).get();
        product.changePrice(3000);
        product.clearList();

        product.addImageString(UUID.randomUUID()+"_" + "PIMAGE1.jpg");
        product.addImageString(UUID.randomUUID()+"_" + "PIMAGE2.jpg");
        product.addImageString(UUID.randomUUID()+"_" + "PIMAGE2.jpg");

        productRepository.save(product);


    }


}
