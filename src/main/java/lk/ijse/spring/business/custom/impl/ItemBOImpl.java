package lk.ijse.spring.business.custom.impl;

import lk.ijse.spring.business.custom.ItemBO;
import lk.ijse.spring.dao.custom.ItemDAO;
import lk.ijse.spring.db.JPAUtil;
import lk.ijse.spring.entity.Item;
import lk.ijse.spring.util.ItemTM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class ItemBOImpl implements ItemBO {

    @Autowired
    private ItemDAO itemDAO;

    public String getNewItemCode() throws Exception {

        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        itemDAO.setEntityManager(entityManager);
        String lastItemCode =  null;
        try {
            entityManager.getTransaction().begin();
            lastItemCode = itemDAO.getLastItemCode();
            entityManager.getTransaction().commit();
        }catch (Throwable t){
            throw  t;
        }finally {
            entityManager.close();
        }

        if (lastItemCode == null) {
            return "I001";
        } else {
            int maxId = Integer.parseInt(lastItemCode.replace("I", ""));
            maxId = maxId + 1;
            String id = "";
            if (maxId < 10) {
                id = "I00" + maxId;
            } else if (maxId < 100) {
                id = "I0" + maxId;
            } else {
                id = "I" + maxId;
            }
            return id;
        }
    }

    public List<ItemTM> getAllItems() throws Exception {

        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        itemDAO.setEntityManager(entityManager);
        List<Item> allItems = null;
        try {
            entityManager.getTransaction().begin();
            allItems = itemDAO.getAll();
            entityManager.getTransaction().commit();
        }catch (Throwable t){
            throw  t;
        }finally {
            entityManager.close();
        }

        List<ItemTM> itemTMS = new ArrayList<>();
        for (Item allItem : allItems) {
            itemTMS.add(new ItemTM(allItem.getCode(), allItem.getDescription(), allItem.getQtyOnHand(), allItem.getUnitPrice().doubleValue()));
        }
        return itemTMS;
    }

    public void saveItem(String code, String description, int qtyOnHand, double unitPrice) throws Exception {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        itemDAO.setEntityManager(entityManager);
        try {
            entityManager.getTransaction().begin();
            itemDAO.save(new Item(code, description, BigDecimal.valueOf(unitPrice), qtyOnHand));
            entityManager.getTransaction().commit();
        }catch (Throwable t){
            throw  t;
        }finally {
            entityManager.close();
        }
    }

    public void deleteItem(String itemCode) throws Exception {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        itemDAO.setEntityManager(entityManager);
        try {
            entityManager.getTransaction().begin();
            itemDAO.delete(itemCode);
            entityManager.getTransaction().commit();
        }catch (Throwable t){
            throw  t;
        }finally {
            entityManager.close();
        }
    }

    public void updateItem(String description, int qtyOnHand, double unitPrice, String itemCode) throws Exception {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        itemDAO.setEntityManager(entityManager);
        try {
            entityManager.getTransaction().begin();
            itemDAO.update(new Item(itemCode, description, BigDecimal.valueOf(unitPrice), qtyOnHand));
            entityManager.getTransaction().commit();
        }catch (Throwable t){
            throw  t;
        }finally {
            entityManager.close();
        }
    }

}
