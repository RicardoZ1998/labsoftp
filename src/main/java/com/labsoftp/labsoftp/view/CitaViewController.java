package com.labsoftp.labsoftp.view;

import com.labsoftp.labsoftp.entity.Cita;
import com.labsoftp.labsoftp.repository.CitaRepository;
import com.labsoftp.labsoftp.repository.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/citas")
public class CitaViewController {

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private MascotaRepository mascotaRepository;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("citas", citaRepository.findAll());
        return "citas/list";
    }

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("cita", new Cita());
        model.addAttribute("mascotas", mascotaRepository.findAll());
        return "citas/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Cita cita, RedirectAttributes redirectAttributes) {
        citaRepository.save(cita);
        redirectAttributes.addFlashAttribute("success", "Cita médica guardada correctamente.");
        return "redirect:/citas";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        return citaRepository.findById(id)
                .map(cita -> {
                    model.addAttribute("cita", cita);
                    model.addAttribute("mascotas", mascotaRepository.findAll());
                    return "citas/form";
                })
                .orElseGet(() -> {
                    redirectAttributes.addFlashAttribute("error", "Cita no encontrada.");
                    return "redirect:/citas";
                });
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if (citaRepository.existsById(id)) {
            citaRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Cita médica eliminada correctamente.");
        } else {
            redirectAttributes.addFlashAttribute("error", "Cita no encontrada.");
        }
        return "redirect:/citas";
    }
}
