package com.hongli.repository;

import com.hongli.domain.BseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BseOrderRepository extends JpaRepository<BseOrder, Integer> {
    public BseOrder findById(int i);
    public BseOrder findByOrderNum(String str);
}
