package com.scotch.simpleorders.dao.impl;

import com.scotch.simpleorders.dao.IndentDao;
import com.scotch.simpleorders.entity.Commodity;
import com.scotch.simpleorders.entity.Customer;
import com.scotch.simpleorders.entity.Indent;
import com.scotch.simpleorders.entity.IndentStatus;

import java.util.List;

/**
 * Created by sutupin on 30.12.2014.
 */
public class JpaIndentDao extends AbstractJpaDao<Indent, Integer> implements IndentDao {
    @Override
    public List<Indent> getAllForCustomer(Customer customer) {
        return entityManager.createQuery("SELECT i FROM Indent i WHERE i.customer = :customerId", entityClass)
                .setParameter("customerId", customer.getId()).getResultList();
    }

    @Override
    public List<Indent> getAllForStatus(IndentStatus status) {
        return entityManager.createQuery("SELECT i FROM Indent i WHERE i.status = :indentStatus", entityClass)
                .setParameter("indentStatus", status).getResultList();
    }

    @Override
    public List<Indent> getAllForCommodity(Commodity commodity) {
        return null; //todo: ))
    }
}
