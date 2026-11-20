package com.labsoftp.labsoftp.view;

import com.labsoftp.labsoftp.repository.CitaRepository;
import com.labsoftp.labsoftp.repository.DuenoRepository;
import com.labsoftp.labsoftp.repository.MascotaRepository;
import com.labsoftp.labsoftp.repository.TratamientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private DuenoRepository duenoRepository;

    @Autowired
    private MascotaRepository mascotaRepository;

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private TratamientoRepository tratamientoRepository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("totalDuenos", duenoRepository.count());
        model.addAttribute("totalMascotas", mascotaRepository.count());
        model.addAttribute("totalCitas", citaRepository.count());
        model.addAttribute("totalTratamientos", tratamientoRepository.count());
        model.addAttribute("ultimasCitas", citaRepository.findAll());
        return "index";
    }
}
