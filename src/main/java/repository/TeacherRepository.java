package repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import Model.Teacher;
import Model.Tutor;

public class TeacherRepository {

    private EntityManager entityManager;
    private EntityManagerFactory emf;

    public TeacherRepository() {
        this.emf = Persistence.createEntityManagerFactory("student");
        this.entityManager = this.emf.createEntityManager();
    }

    public TeacherRepository(String student) {
        this.emf = Persistence.createEntityManagerFactory(student);
        this.entityManager = this.emf.createEntityManager();
    }

    public Teacher add(Teacher teacher) {
        entityManager.getTransaction().begin();
        entityManager.persist(teacher);
        entityManager.getTransaction().commit();
        return teacher;
    }

    public Teacher find(Long id) {
        return entityManager.find(Teacher.class, id);
    }

    public Teacher update(Tutor teacher) {
        Teacher teacherToUpdate = find(teacher.getId());
        entityManager.getTransaction().begin();
        teacherToUpdate.setLastName(teacher.getLastName());
        teacherToUpdate.setFirstName(teacher.getFirstName());
        entityManager.getTransaction().commit();
        return teacherToUpdate;
    }

    public void delete(Teacher teacher) {
        entityManager.getTransaction().begin();
        entityManager.remove(teacher);
        entityManager.getTransaction().commit();
    }

    public void close() {
        this.entityManager.close();
        this.emf.close();
    }
}