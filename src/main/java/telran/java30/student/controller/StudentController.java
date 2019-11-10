package telran.java30.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import telran.java30.student.dto.ScoreDto;
import telran.java30.student.dto.StudentDto;
import telran.java30.student.dto.StudentResponseDto;
import telran.java30.student.dto.StudentUpdateDto;
import telran.java30.student.model.Student;
import telran.java30.student.service.StudentService;

@RestController
public class StudentController {
	
	@Autowired
	StudentService studentService;

	@PostMapping("/student")
	public boolean addStudent(@RequestBody StudentDto student) {
		return studentService.addStudent(student);
	}

	@GetMapping("/student/{id}")
	public StudentResponseDto findStudent(@PathVariable Integer id) {
		return studentService.findStudent(id);
	}

	@DeleteMapping("/student/{id}")
	public StudentResponseDto deletStudent(@PathVariable Integer id) {
		return studentService.deletStudent(id);
	}
	
	@PutMapping("/student/{id}")
	public StudentDto updateDtostudent(@PathVariable Integer id, @RequestBody StudentUpdateDto student) {
		return studentService.updateDtostudent(id, student);
	}
	
	@PutMapping("/score/student/{id}")
	public boolean addScore(@PathVariable int id, @RequestBody ScoreDto scoreDto) {
		
		return studentService.addScore(id, scoreDto);
		
	}
	@GetMapping("students/name/{name}")
	public List<StudentResponseDto> findStudensByName(@PathVariable String name){
		return studentService.findStudentsByName(name);
	}
	
	@GetMapping("students/count/{name}")
	public long countStudentByName(@PathVariable String name) {
		return studentService.countByName(name);
	}
	@GetMapping("students/score/{exam}/{score}")
	public List<StudentResponseDto> findStudentByExam(@PathVariable(name="exam") String examName, @PathVariable(name="score")int minScore){
		return studentService.findStudentByExam(examName, minScore);
	}

}
