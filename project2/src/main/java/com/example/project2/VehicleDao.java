package com.example.project2;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@Repository
@Transactional
public class VehicleDao {
    @PersistenceContext
    private EntityManager entityManager;

    public void createVehicle(Vehicle vehicle) {
        entityManager.persist(vehicle);
    }

    public Vehicle getById(int id) {
        return entityManager.find(Vehicle.class, id);
    }

    public Vehicle updateVehicle(Vehicle newVehicle) {
        entityManager.merge(newVehicle);
        return newVehicle;
    }

    public void deleteVehicle(int id) {
        entityManager.remove(getById(id));

    }

    public int queryLatestVehicle() {
        Query query =
                entityManager.createNativeQuery("select MAX(id) from Vehicle");
        Integer i = (Integer) query.getSingleResult();
        return i;
    }

    public int queryEarliestVehicle() {
        Query query =
                entityManager.createNativeQuery("select MIN(id) from Vehicle");
        Integer i = (Integer) query.getSingleResult();
        return i;
    }

}
