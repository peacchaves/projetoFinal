package acc.br.demo;

import acc.br.demo.model.Student;
import acc.br.demo.repository.StudentRepository;
import acc.br.demo.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;

public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveStudent() {
        Student student = new Student();
        student.setName("João Zinho");
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        Student savedStudent = studentService.saveStudent(student);

        assertEquals("João Zinho", savedStudent.getName());
        verify(studentRepository, times(1)).save(student);
    }
}