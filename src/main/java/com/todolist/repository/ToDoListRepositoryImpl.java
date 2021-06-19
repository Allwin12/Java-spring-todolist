package com.todolist.repository;

import com.todolist.entity.ToDoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Repository
public class ToDoListRepositoryImpl implements ToDoListRepositoryCustom {
    @Autowired
    private EntityManager em;

    // Custom filter implementation
    @Override
    public List<ToDoList> findTasks(Boolean completed, String from, String to, String search) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ToDoList> cq = cb.createQuery(ToDoList.class);
        Root<ToDoList> tasks = cq.from(ToDoList.class);

        List<Predicate> predicates = new ArrayList<>();
        if (completed!=null){
            predicates.add(cb.equal(tasks.get("completed"), completed));
        }

        if (from!=null){
            try {
                Date fromDate=new SimpleDateFormat("dd-MM-yyyy").parse(from);
                predicates.add(cb.greaterThanOrEqualTo(tasks.get("createdAt"), fromDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (to!=null){
            try {
                Date toDate=new SimpleDateFormat("dd-MM-yyyy").parse(to);
                predicates.add(cb.lessThanOrEqualTo(tasks.get("createdAt"), toDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (search!=null)
        {
            System.out.println(search);
            predicates.add(cb.like(cb.lower(tasks.get("task")), "%"+search.toLowerCase(Locale.ROOT)+"%"));
        }

        cq.where(predicates.toArray(new Predicate[0]));
        cq.orderBy(cb.desc(tasks.get("createdAt")));
        TypedQuery<ToDoList> query = em.createQuery(cq);
        return query.getResultList();
    }
}

