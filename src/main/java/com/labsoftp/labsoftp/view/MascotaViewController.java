package com.labsoftp.labsoftp.view;

import com.labsoftp.labsoftp.entity.Mascota;
import com.labsoftp.labsoftp.repository.DuenoRepository;
import com.labsoftp.labsoftp.repository.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/mascotas")
public class MascotaViewController {

    @Autowired
    private MascotaRepository mascotaRepository;

    @Autowired
    private DuenoRepository duenoRepository;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("mascotas", mascotaRepository.findAll());
        return "mascotas/list";
    }

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("mascota", new Mascota());
        model.addAttribute("duenos", duenoRepository.findAll());
        return "mascotas/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Mascota mascota, RedirectAttributes redirectAttributes) {
        mascotaRepository.save(mascota);
        redirectAttributes.addFlashAttribute("success", "Mascota guardada correctamente.");
        return "redirect:/mascotas";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        return mascotaRepository.findById(id)
                .map(mascota -> {
                    model.addAttribute("mascota", mascota);
                    model.addAttribute("duenos", duenoRepository.findAll());
                    return "mascotas/form";
                })
                .orElseGet(() -> {
                    redirectAttributes.addFlashAttribute("error", "Mascota no encontrada.");
                    return "redirect:/mascotas";
                });
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if (mascotaRepository.existsById(id)) {
            mascotaRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Mascota eliminada correctamente.");
        } else {
            redirectAttributes.addFlashAttribute("error", "Mascota no encontrada.");
        }
        return "redirect:/mascotas";
    }
}
