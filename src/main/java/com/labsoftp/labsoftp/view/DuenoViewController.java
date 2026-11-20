package com.labsoftp.labsoftp.view;

import com.labsoftp.labsoftp.entity.Dueno;
import com.labsoftp.labsoftp.repository.DuenoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/duenos")
public class DuenoViewController {

    @Autowired
    private DuenoRepository duenoRepository;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("duenos", duenoRepository.findAll());
        return "duenos/list";
    }

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("dueno", new Dueno());
        return "duenos/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Dueno dueno, RedirectAttributes redirectAttributes) {
        duenoRepository.save(dueno);
        redirectAttributes.addFlashAttribute("success", "Dueño guardado correctamente.");
        return "redirect:/duenos";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        return duenoRepository.findById(id)
                .map(dueno -> {
                    model.addAttribute("dueno", dueno);
                    return "duenos/form";
                })
                .orElseGet(() -> {
                    redirectAttributes.addFlashAttribute("error", "Dueño no encontrado.");
                    return "redirect:/duenos";
                });
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if (duenoRepository.existsById(id)) {
            duenoRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Dueño eliminado correctamente.");
        } else {
            redirectAttributes.addFlashAttribute("error", "Dueño no encontrado.");
        }
        return "redirect:/duenos";
    }
}
