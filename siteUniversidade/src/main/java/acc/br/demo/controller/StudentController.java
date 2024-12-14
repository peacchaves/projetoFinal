package acc.br.demo.controller;

import acc.br.demo.model.Address;
import acc.br.demo.model.Student;
import acc.br.demo.service.CepService;
import acc.br.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/students")
public class StudentController {

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private CepService cepService;

    @Autowired
    private StudentService studentService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("student", new Student());
        return "studentRegistration";
    }

    @PostMapping("/register")
    public String processRegistration(@ModelAttribute Student student, Model model) {
        logger.info("Processing registration for student with CEP: {}", student.getCep());
        Address address = cepService.findAddressByCep(student.getCep());
        if (address != null) {
            logger.info("Address found: {}", address);
            student.setCity(address.getLocalidade());
            student.setNeighborhood(address.getBairro());
        } else {
            logger.warn("No address found for CEP: {}", student.getCep());
        }
        model.addAttribute("student", student);
        return "reviewRegistration";
    }

    @PostMapping("/submit")
    public String submitRegistration(@ModelAttribute Student student) {
        studentService.saveStudent(student);
        return "success";
    }

    @GetMapping("/edit")
    public String editRegistration(@ModelAttribute Student student, Model model) {
        model.addAttribute("student", student);
        return "studentRegistration";
    }

    @GetMapping("/cancel")
    public String cancelRegistration() {
        return "cancel";
    }
}