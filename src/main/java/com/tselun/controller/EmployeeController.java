package com.tselun.controller;

import com.tselun.model.Employee;
import com.tselun.repository.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author csdcodes.net
 * @since 2020-3-25
 */

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeMapper mapper;

    @GetMapping("/")
    private String emp(){
        return "test";
    }

    @PostMapping("/insert")
    private String insert(@ModelAttribute("employee") Employee employee){
        mapper.insert(employee);
        return "redirect:/emp-list";
    }

    @GetMapping("/emp-list")
    private String employees(Model model){
        model.addAttribute("employees", mapper.findAll());
        return "employee";
    }

    @GetMapping("/delete")
    private String delete(@RequestParam("id") int id){
        mapper.deleteById(id);
        return "redirect:/emp-list";
    }

    @GetMapping("/form")
    private String updateForm(@RequestParam("id") int id, Model model){
        Employee emp = mapper.findById(id);
        if(emp != null){
            model.addAttribute("emp", emp);
        }
        return "form";
    }

    @PostMapping("/update")
    private String update(@ModelAttribute("employee") Employee employee){
        mapper.update(employee);
        return "redirect:/emp-list";
    }

}
