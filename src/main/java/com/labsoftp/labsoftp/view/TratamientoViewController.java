package com.labsoftp.labsoftp.view;

import com.labsoftp.labsoftp.entity.Tratamiento;
import com.labsoftp.labsoftp.repository.CitaRepository;
import com.labsoftp.labsoftp.repository.TratamientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/tratamientos")
public class TratamientoViewController {

    @Autowired
    private TratamientoRepository tratamientoRepository;

    @Autowired
    private CitaRepository citaRepository;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("tratamientos", tratamientoRepository.findAll());
        return "tratamientos/list";
    }

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("tratamiento", new Tratamiento());
        model.addAttribute("citas", citaRepository.findAll());
        return "tratamientos/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Tratamiento tratamiento, RedirectAttributes redirectAttributes) {
        tratamientoRepository.save(tratamiento);
        redirectAttributes.addFlashAttribute("success", "Tratamiento guardado correctamente.");
        return "redirect:/tratamientos";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        return tratamientoRepository.findById(id)
                .map(tratamiento -> {
                    model.addAttribute("tratamiento", tratamiento);
                    model.addAttribute("citas", citaRepository.findAll());
                    return "tratamientos/form";
                })
                .orElseGet(() -> {
                    redirectAttributes.addFlashAttribute("error", "Tratamiento no encontrado.");
                    return "redirect:/tratamientos";
                });
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if (tratamientoRepository.existsById(id)) {
            tratamientoRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Tratamiento eliminado correctamente.");
        } else {
            redirectAttributes.addFlashAttribute("error", "Tratamiento no encontrado.");
        }
        return "redirect:/tratamientos";
    }
}
