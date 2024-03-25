package com.tutorial.StudentApp;


import java.util.List;

import Model.School;
import Model.Student;
import Model.Teacher;
import Model.Tutor;
import repository.SchoolRepository;
import repository.StudentRepository;
import repository.TeacherRepository;
import repository.TutorRepository;


public class App {
  public static void main(String[] args) {

    Student student = new Student("karan", "Singh");

    //REPOSITORIES

    StudentRepository studentRepository = new StudentRepository();

    SchoolRepository schoolRepository = new SchoolRepository();

    TutorRepository tutorRepository = new TutorRepository();

    TeacherRepository teacherRepository = new TeacherRepository();

    //ADD STUDENT

    studentRepository.add(student);

    System.out.println("Added student " + student.toString());

    //ADD TUTOR

    Tutor tutor = new Tutor("Dev", "Nagar");

    tutorRepository.add(tutor);

    System.out.println("Added tutor " + tutor.toString());

    studentRepository.addTutor(student.getId(), tutor);

    System.out.println("Student with tutor " + student.toString());

    System.out.println("Found student with school " + student.toString());

    //ADD SCHOOL

    School school = new School("Navankur","Basoda");

    schoolRepository.add(school);

    System.out.println("Added school " + school.toString());

    school = schoolRepository.find(school.getId());

    school.getStudents().forEach(System.out::println);

    school = schoolRepository.find(school.getId());

    school.getStudents().forEach(System.out::println);

    //ADD TEACHER

    Teacher teacher = new Teacher("Anurag","Jain");

    teacher.addStudent(new Student("Naina", "Bhawsar"));
    teacher.addStudent(new Student("Shiva", "Untwal"));

    teacherRepository.add(teacher);

    //Persistence Operations and JPQL

    studentRepository.findFirstNames().forEach(System.out::println);

    studentRepository.findLastNames().forEach(System.out::println);

    student = studentRepository.find(student.getId());

    System.out.println("Found student " + student.toString());

    student = studentRepository.findById(student.getId());

    System.out.println("Found student (JPQL) " + student.toString());

    student.setLastName("Sharma");

    studentRepository.update(student);

    System.out.println("Updated student " + student.toString());

    student = studentRepository.updateFirstNameById("shivi", student.getId());

    System.out.println("Updated first name (JPQL)" + student.toString());

    student = studentRepository.updateLastNameById("Jain", student.getId());

    System.out.println("Updated last name (JPQL)" + student.toString());

    List<Student> students = studentRepository.findByFirstNameStartWith("Fr");

    students.forEach(System.out::println);

    students = studentRepository.findByLastNameEndWith("ow");

    students.forEach(System.out::println);

    System.out.println("Number of student(s): "+  studentRepository.count());

    students = studentRepository.findSortingByFirstName();

    students.forEach(System.out::println);

    students = studentRepository.findSortingById();

    students.forEach(System.out::println);

    //repository.delete(student);

    //System.out.println("Deleted student " + student.toString());


    //CRITERIA BUILDER

    List<Student> studentList = studentRepository.getStudentWithCriteriaBuilder();

    System.out.println("Print Students (Criteria Builder): ");
    studentList.forEach(System.out::println);

    List<Student> studentListWhere = studentRepository.getStudentsWithWHEREFirstName();

    System.out.println("Print Students (Criteria Builder with WHERE and GROUP BY): ");
    studentListWhere.forEach(System.out::println);

    studentRepository.close();

  }
}