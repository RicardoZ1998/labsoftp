package com.labsoftp.labsoftp.view;

import com.labsoftp.labsoftp.entity.Clientes;
import com.labsoftp.labsoftp.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ClientesView
{
    @Autowired
    private ClientesRepository clientesRepository;

    @GetMapping("/view/clientes")
    public String lista(Model model)
    {
        model.addAttribute("clientes", clientesRepository.findAll());
        return "clientes/list";
    }

    @GetMapping("/view/clientes/form")
    public String form(Model model)
    {
        model.addAttribute("clientes", new Clientes());
        return "clientes/form";
    }

    @PostMapping("/view/clientes/save")
    public String save(@ModelAttribute Clientes clientes, RedirectAttributes rs)
    {
        clientesRepository.save(clientes);
        rs.addFlashAttribute("message", "Cliente guardado correctamente");
        return "redirect:/view/clientes";
    }

    @GetMapping("/view/clientes/edit/{id}")
    public String edit(@PathVariable Long id, Model model)
    {
        Clientes cliente = clientesRepository.findById(id).orElse(null);
        model.addAttribute("clientes", cliente);
        return "clientes/form";
    }

    @PostMapping("/view/clientes/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes rs)
    {
        clientesRepository.deleteById(id);
        rs.addFlashAttribute("message", "Cliente eliminado correctamente");
        return "redirect:/view/clientes";
    }
}
