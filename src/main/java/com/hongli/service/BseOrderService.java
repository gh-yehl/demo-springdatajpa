package com.hongli.service;

import com.hongli.domain.BseOrder;
import com.hongli.repository.BseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BseOrderService {

    @Autowired
    BseOrderRepository bseOrderRepository;

    @Transactional
    public void createOrder() {
        BseOrder bseOrder = new BseOrder();
        bseOrder.setId(11);
        bseOrder.setOrderNum("order_num_11");

        bseOrderRepository.save(bseOrder);

    }

    @Transactional
    public void deleteOrder() {
        bseOrderRepository.deleteById(9);
        String i = null;
        System.out.println(i.substring(2));
    }

}
