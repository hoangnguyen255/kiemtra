package nguyennguyenhoang.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import jakarta.validation.Valid;
import nguyennguyenhoang.models.Nhanvien;
import nguyennguyenhoang.services.NhanvienService;

@Controller
@RequestMapping("/nhanviens")
public class NhanvienController {
	@Autowired
	private NhanvienService nhanvienService;

	@GetMapping("")
	public String index(Model model) {
		model.addAttribute("listnhanvien", nhanvienService.GetAll());
		return "nhanviens/index";
	}

	@GetMapping("/create")
	public String addBookForm(Model model) {
		model.addAttribute("nhanvien", new Nhanvien());
		// model.addAttribute("phongbans",p());
		return "nhanviens/create";
	}

	@PostMapping("/create")
	public String addBook(@ModelAttribute("nhanvien") Nhanvien nhanVien) {
		nhanvienService.save(nhanVien);
		return "redirect:/nhanviens";

	}
	/*
	 * @RequestMapping("/edit/{id}") public ModelAndView
	 * showEditProductPage(@PathVariable(name = "id") String id) { ModelAndView mav
	 * = new ModelAndView("nhanvien/edit"); Nhanvien nhanvien =
	 * nhanvienService.get(id); mav.addObject("nhanvien", nhanvien);
	 * 
	 * return mav; }
	 * 
	 * @RequestMapping(value = "/save", method = RequestMethod.POST) public String
	 * saveProduct(@ModelAttribute("product") Nhanvien nhanvien, BindingResult
	 * result, Model model) { nhanvienService.save(nhanvien); return
	 * "redirect:/products"; }
	 */

	@GetMapping("/edit/{id}")
    public String editNhanVienForm(@PathVariable("id") String id, Model model){
        Nhanvien editNhanVien = nhanvienService.get((id));
        if(editNhanVien != null){
            model.addAttribute("nhanvien", editNhanVien);
            return "nhanviens/edit";
        }else {
            return "not-found";
        }
    }
	
    @PostMapping("/edit")
    public String editBook(@Valid @ModelAttribute("nhanvien") Nhanvien updatenhanVien, BindingResult bindingResult, Model model ){
        if (bindingResult.hasErrors()){
            return "nhanvien/edit";
        }
        nhanvienService.GetAll().stream()
                .filter(nhanvien -> nhanvien.getId().equals(updatenhanVien.getId()))
                .findFirst()
                .ifPresent(nhanvien -> {

                    nhanvienService.update(updatenhanVien);
                });
        return "redirect:/nhanviens";
    }
    
	@RequestMapping("/delete/{id}")
	public String deleteProduct(@PathVariable(name = "id") String id) {
		nhanvienService.delete(id);
		return "redirect:/nhanviens";
	}
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo, @RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir, Model model) {
		int pageSize = 5;

		Page<Nhanvien> page = nhanvienService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Nhanvien> listnhanviens = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());

		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

		model.addAttribute("listnhanviens", listnhanviens);
		return "index";
	}
	@GetMapping("/")
	public String viewHomePage(Model model) {
		return findPaginated(1, "firstName", "asc", model);
	}

}
