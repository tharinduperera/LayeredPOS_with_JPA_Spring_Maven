package lk.ijse.spring.dao.custom.impl;

import lk.ijse.spring.dao.CrudDAOImpl;
import lk.ijse.spring.dao.custom.CustomerDAO;
import lk.ijse.spring.entity.Customer;
import org.springframework.stereotype.Component;

import javax.persistence.NoResultException;

@Component
public class CustomerDAOImpl extends CrudDAOImpl<Customer,String> implements CustomerDAO {

    @Override
    public String getLastCustomerId() throws Exception {
        try {
            return (String) entityManager.createNativeQuery("SELECT id FROM CUSTOMER ORDER BY id DESC LIMIT 1").getSingleResult();
        }catch (NoResultException ex){
            return null;
        }

    }


}
