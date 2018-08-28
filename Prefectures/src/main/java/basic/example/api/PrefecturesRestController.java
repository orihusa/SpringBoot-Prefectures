/**
 * REST Webサービス
 */
/**
 * @author yasuhiro
 *
 */
package basic.example.api;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import basic.example.domain.Prefecture;
import basic.example.service.PrefectureService;
import basic.example.web.PrefectureForm;

@RestController
@RequestMapping("api/prefecturs")
public class PrefecturesRestController {
	@Autowired
	PrefectureService prefectureService;
	
	@ModelAttribute
	PrefectureForm setUpForm() {
		return new PrefectureForm();
	}
	
	@GetMapping
	String list(Model model) {
		List<Prefecture> prefectures = prefectureService.findAll();
		model.addAttribute("prefectures", prefectures);
		return "Prefectures/list";
	}
	
	@PostMapping(path="create")
	String create(@Validated PrefectureForm form, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return list(model);
		}
		Prefecture prefecture = new Prefecture();
		BeanUtils.copyProperties(form, prefecture);
		prefectureService.create(prefecture);
		return "redirect:/prefectures";
	}
	
}