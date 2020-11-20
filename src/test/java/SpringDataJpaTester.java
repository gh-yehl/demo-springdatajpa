import com.hongli.domain.BseOrder;
import com.hongli.jpaconfig.JpaConfig;
import com.hongli.repository.BseOrderRepository;
import com.hongli.service.BseOrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= JpaConfig.class)
public class SpringDataJpaTester {

    @Autowired
    BseOrderRepository bseOrderRepository;

    @Autowired
    BseOrderService bseOrderService;


    /**
     * 正确处理事务 的测试
     */
    @Test
    public void createOrder() {
        bseOrderService.createOrder();
    }

    @Test
    public void deleteOrder() {
        bseOrderService.deleteOrder();
    }


    /**
     * 错误处理事务 的测试， @Transactional必须置于 @Service, @Controller OR @Component 被Spring管理得 Bean上
     * 所以，下面得 @Transactional无效，同时莫名其妙得 导致了 无法commit得情况
     */
    @Test
    @Transactional
    public void testJpaDelete() {

        try{
            bseOrderRepository.deleteById(6);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }



}
