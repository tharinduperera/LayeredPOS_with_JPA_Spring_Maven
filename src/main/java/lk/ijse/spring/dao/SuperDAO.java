package lk.ijse.spring.dao;

import lk.ijse.spring.entity.SuperEntity;

import javax.persistence.EntityManager;
import java.io.Serializable;

public interface SuperDAO<T extends SuperEntity,ID extends Serializable> {

    public void setEntityManager(EntityManager entityManager);

}

