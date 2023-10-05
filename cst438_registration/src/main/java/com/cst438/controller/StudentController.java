package com.cst438.controller;

/*
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cst438.domain.Course;
import com.cst438.domain.StudentDTO;
import com.cst438.domain.CourseRepository;
import com.cst438.domain.Enrollment;
import com.cst438.domain.EnrollmentRepository;
import com.cst438.domain.ScheduleDTO;
import com.cst438.domain.Student;
import com.cst438.domain.StudentRepository;
import com.cst438.service.GradebookService;

@RestController
@CrossOrigin
public class StudentController {
	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	EnrollmentRepository enrollmentRepository;
	
	@Autowired
	GradebookService gradebookService;
	
	// get by primary key
	//	REST API for a resource named "course" 
	// get by primary key
	@GetMapping("/student/{student_id}")
	public StudentDTO getStudent(@PathVariable("student_id") int student_id) {
		Optional<Student> studentOptional = studentRepository.findById(student_id);
		
		if (studentOptional.isPresent()) {
	        // Convert the Student object to a StudentDTO (You need to create StudentDTO)
	        Student student = studentOptional.get();
	        StudentDTO studentDTO = new StudentDTO(
	        		student.getStudent_id(), 
	        		student.getName(), 
	        		student.getEmail(), 
	        		student.getStatus(), 
	        		student.getStatusCode());

	        return studentDTO;
	    } else {
	        // If the student is not found, return a 404 Not Found status
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found");
	    }
	}

	// create a new course and return the system generated course_id
	@PostMapping("/student")
	@Transactional	
	public int createStudent(@RequestBody StudentDTO studentDTO) {
		Student student = new Student();
		student.setName(studentDTO.name());
		student.setEmail(studentDTO.email());
		student.setStatus(studentDTO.status());
		student.setStatusCode(studentDTO.status_code());
		studentRepository.save(student);
		return student.getStudent_id();
	}

	// delete a course
	//   DELETE  /course/12389
	//   DELETE  /course/12389?force=yes
	@DeleteMapping("/student/{student_id}")
    public void deleteStudent(@PathVariable("student_id") int student_id, 
    		@RequestParam("force") Optional<String> force) {
		Student student = studentRepository.findById(student_id).orElseThrow(() -> 
		new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));
        if (force.isPresent() && force.get().equals("yes")) {
            // Implement logic for force delete if needed
        	//studentRepository.delete(student);
        	
        }
        studentRepository.delete(student);
    }



	// update course
	@PutMapping("/student/{student_id}")
	public void updateStudent(@RequestBody StudentDTO studentDTO, @PathVariable("student_id") int student_id) {
		Student student = studentRepository.findById(student_id).orElseThrow(() -> 
        new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));
		student.setName(studentDTO.name());
		student.setEmail(studentDTO.email());
		student.setStatus(studentDTO.status());
    	student.setStatusCode(studentDTO.status_code());
    	studentRepository.save(student);
	}

	// get all courses
	//   GET  /course 
	//   GET  /course?dept=CS&semester=Fall&year=2023
	@GetMapping("/student")
	public StudentDTO[] getAllStudents() {
		List<Student> students = (List<Student>) studentRepository.findAll();
		StudentDTO[] result = new StudentDTO[students.size()];
		for(int i =0; i <students.size();i++) {
			Student s = students.get(i);
			StudentDTO dto = new StudentDTO(
								s.getStudent_id(),
								s.getName(),
								s.getEmail(),
								s.getStatus(),
								s.getStatusCode());
			result[i] = dto;
		}
		
		return result;
	}
}*/

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cst438.domain.Enrollment;
import com.cst438.domain.EnrollmentRepository;
import com.cst438.domain.Student;
import com.cst438.domain.StudentDTO;
import com.cst438.domain.StudentRepository;

@RestController
@CrossOrigin 
public class StudentController {
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	EnrollmentRepository enrollmentRepository;
	
	@GetMapping("/student")
	public StudentDTO[] getStudents() {
		Iterable<Student> list = studentRepository.findAll();
		ArrayList<StudentDTO> alist = new ArrayList<>();
		for (Student s : list) {
			StudentDTO sdto = new StudentDTO(s.getStudent_id(), s.getName(), s.getEmail(), s.getStatusCode(), s.getStatus());
			alist.add(sdto);
		}
		return alist.toArray(new StudentDTO[alist.size()]);
	}
	
	@GetMapping("/student/{id}")
	public StudentDTO getStudent(@PathVariable("id") int id) {
		Student s = studentRepository.findById(id).orElse(null);
		if (s!=null) {
			StudentDTO sdto = new StudentDTO(s.getStudent_id(), s.getName(), s.getEmail(), s.getStatusCode(), s.getStatus());
			return sdto;
		} else {
			throw  new ResponseStatusException( HttpStatus.NOT_FOUND, "student not found "+id);
		}
	}
	
	@PutMapping("/student/{id}") 
	public void updateStudent(@PathVariable("id")int id, @RequestBody StudentDTO sdto) {
		Student s = studentRepository.findById(id).orElse(null);
		if (s==null) {
			throw  new ResponseStatusException( HttpStatus.NOT_FOUND, "student not found "+id);
		}
		// has email been changed, check that new email does not exist in database
		if (!s.getEmail().equals(sdto.email())) {
		// update name, email.  new email must not exist in database
			Student check = studentRepository.findByEmail(sdto.email());
			if (check != null) {
				// error.  email exists.
				throw  new ResponseStatusException( HttpStatus.BAD_REQUEST, "student email already exists "+sdto.email());
			}
		}
		s.setEmail(sdto.email());
		s.setName(sdto.name());
		s.setStatusCode(sdto.statusCode());
		s.setStatus(sdto.status());
		studentRepository.save(s);
	}
	
	@PostMapping("/student")
	public int createStudent(@RequestBody StudentDTO sdto) {
		Student check = studentRepository.findByEmail(sdto.email());
		if (check != null) {
			// error.  email exists.
			throw  new ResponseStatusException( HttpStatus.BAD_REQUEST, "student email already exists "+sdto.email());
		}
		Student s = new Student();
		s.setEmail(sdto.email());
		s.setName(sdto.name());
		s.setStatusCode(sdto.statusCode());
		s.setStatus(sdto.status());
		studentRepository.save(s);
		// return the database generated student_id 
		return s.getStudent_id();
	}
	
	@DeleteMapping("/student/{id}")
	public void deleteStudent(@PathVariable("id") int id, @RequestParam("force") Optional<String> force) {
		Student s = studentRepository.findById(id).orElse(null);
		if (s!=null) {
			// are there enrollments?
			List<Enrollment> list = enrollmentRepository.findByStudentId(id);
			if (list.size()>0 && force.isEmpty()) {
				throw  new ResponseStatusException( HttpStatus.BAD_REQUEST, "student has enrollments");
			} else {
				studentRepository.deleteById(id);
			}
		} else {
			// if student does not exist.  do nothing
			return;
		}
		
	}

}
