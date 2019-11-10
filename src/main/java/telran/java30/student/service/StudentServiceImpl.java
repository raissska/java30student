package telran.java30.student.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import telran.java30.student.dao.StudentRepository;
import telran.java30.student.dto.ScoreDto;
import telran.java30.student.dto.StudentDto;
import telran.java30.student.dto.StudentNotFountException;
import telran.java30.student.dto.StudentResponseDto;
import telran.java30.student.dto.StudentUpdateDto;
import telran.java30.student.model.Student;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentRepository studentRepository;

	@Override
	public boolean addStudent(StudentDto studentDto) {
		if (studentRepository.existsById(studentDto.getId())) {
			return false;
		}
		Student student = new Student(studentDto.getId(), studentDto.getName(), studentDto.getPassword());
		studentRepository.save(student);
		return true;
	}

	@Override
	public StudentResponseDto findStudent(Integer id) {

		Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFountException(id));
		return StudentResponseDto.builder().id(student.getId()).name(student.getName()).scores(student.getScores())
				.build();
	}

	@Override
	public StudentResponseDto deletStudent(Integer id) {
		Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFountException(id));
		studentRepository.delete(student);
		return StudentResponseDto.builder().id(student.getId()).name(student.getName()).scores(student.getScores())
				.build();
	}

	@Override
	public StudentDto updateDtostudent(Integer id, StudentUpdateDto studentUpdateDto) {
		Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFountException(id));
		student.setName(studentUpdateDto.getName());
		student.setPassword(studentUpdateDto.getPassword());
		studentRepository.save(student);
		return StudentDto.builder().id(id).name(student.getName()).password(student.getPassword()).build();
	}

	@Override
	public boolean addScore(Integer id, ScoreDto score) {
		Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFountException(id));
		boolean stboolean = student.addScore(score.getExamName(), score.getScore());
		studentRepository.save(student);
		return stboolean;
	}

	@Override
	public List<StudentResponseDto> findStudentsByName(String name) {

		return studentRepository.findByName(name).map(this::studentToStudentResponseDto).collect(Collectors.toList());
	}

	public StudentResponseDto studentToStudentResponseDto(Student student) {
		return StudentResponseDto.builder().id(student.getId()).name(student.getName()).scores(student.getScores())
				.build();
	}

	@Override
	public long countByName(String name) {
		
		return studentRepository.countByName(name);
	}

	@Override
	public List<StudentResponseDto> findStudentByExam(String examName, int minScore) {
		return studentRepository.findByExamScore("scores."+examName, minScore)
				.map(this::studentToStudentResponseDto)
				.collect(Collectors.toList());
	}
	
	

}
