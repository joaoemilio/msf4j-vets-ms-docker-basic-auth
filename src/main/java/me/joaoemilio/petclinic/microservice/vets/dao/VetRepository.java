/*
 * Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *   WSO2 Inc. licenses this file to you under the Apache License,
 *   Version 2.0 (the "License"); you may not use this file except
 *   in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package me.joaoemilio.petclinic.microservice.vets.dao;


import me.joaoemilio.petclinic.microservice.vets.model.Specialty;
import me.joaoemilio.petclinic.microservice.vets.model.Vet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * UserRepository class which is extended from AbstractRepository class.
 */

@Transactional
@Repository
public class VetRepository {

    @Autowired
    private EntityManagerFactory emf;

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void add(Vet model) {
        EntityManager manager = getEntityManager();
        manager.getTransaction().begin();

        List<Specialty> list = model.getSpecialties();
        for( Specialty specialty: list ) {
            manager.persist( specialty );
            System.out.println( "specialty id: " + specialty.getName() + " " + specialty.getId() );
        }

        manager.persist(model);
        manager.getTransaction().commit();
        manager.close();
    }

    public void update( Vet model ) {
        EntityManager manager = getEntityManager();
        manager.getTransaction().begin();

        manager.merge(model);
        manager.getTransaction().commit();
        manager.close();    
    }

    public void remove(long id) {
        EntityManager manager = getEntityManager();
        manager.getTransaction().begin();
        manager.remove(manager.find(Vet.class, id));
        manager.getTransaction().commit();
        manager.close();
    }

    public Vet find(long id) {
        System.out.println( "vet id: " + id  );
        Vet model = getEntityManager().find(Vet.class, id);
        System.out.println( "repository find: " + id  );
        return model;
    }

    public List<Vet> findAll() {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Vet> cq = cb.createQuery(Vet.class);
        Root<Vet> rootEntry = cq.from(Vet.class);
        CriteriaQuery<Vet> all = cq.select(rootEntry);
        TypedQuery<Vet> allQuery = getEntityManager().createQuery(all);
        return allQuery.getResultList();
    }
}
