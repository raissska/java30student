package telran.java30.student.service;

import java.util.List;

import telran.java30.student.dto.ScoreDto;
import telran.java30.student.dto.StudentDto;
import telran.java30.student.dto.StudentResponseDto;
import telran.java30.student.dto.StudentUpdateDto;

public interface StudentService {
	boolean addStudent(StudentDto student);
	StudentResponseDto findStudent(Integer id);
	StudentResponseDto deletStudent(Integer id);
	StudentDto updateDtostudent(Integer id, StudentUpdateDto student);
	boolean addScore(Integer id, ScoreDto score);
	List<StudentResponseDto> findStudentsByName(String name);
	long countByName(String name);
	List<StudentResponseDto> findStudentByExam(String examName, int minScore);
	

}
