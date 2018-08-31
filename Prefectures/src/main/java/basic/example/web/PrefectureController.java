package basic.example.web;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import basic.example.domain.Prefecture;
import basic.example.service.LoginUserDetails;
import basic.example.service.PrefectureService;

@Controller							// 画面遷移用のコントローラ
@RequestMapping("prefectures")		// URLの接頭辞
public class PrefectureController {
	@Autowired
	PrefectureService prefectureService;
	
	@ModelAttribute					// 初期化
	PrefectureForm setUpForm() {
		return new PrefectureForm();
	}
	
	@GetMapping						// listメソッドがURLにマッピングされるようにする
	String list(Model model) {
		List<Prefecture> prefectures = prefectureService.findAll();
		model.addAttribute("prefectures", prefectures);
		return "prefectures/list";
	}

	// 画面の情報はPrefectureFormで表現し、コントローラで変換する
	@PostMapping(path = "create")
	String create(
				@Validated	// 送信されたフォームの情報の入力チェックを行う
					PrefectureForm form,
					BindingResult result,
					Model model,
				@AuthenticationPrincipal	// ログイン中のLoginUserDetailsオブジェクトが取得できる
					LoginUserDetails userDetails
				) {
		if (result.hasErrors()) {	// 入力チェックの結果を確認し、エラーの場合は一覧画面表示に戻る
			return list(model);
		}
		Prefecture prefecture = new Prefecture();
		BeanUtils.copyProperties(form, prefecture);
		prefectureService.create(prefecture, userDetails.getUser());
		return "redirect:/prefectures";	// 正常終了時、一覧画面表示にリダイレクトする 
	}

	@GetMapping(path="edit", params="form")
	String editForm(@RequestParam	// 特定のリクエストパラメータをマッピング
					Integer id,		// リクエストパラメータのidをマッピング
					PrefectureForm form) {
		Prefecture prefecture = prefectureService.findOne(id);
		BeanUtils.copyProperties(prefecture, form);
		return "prefectures/edit";		// 顧客情報編集画面
	}
	
	@PostMapping(path="edit")
	String edit(@RequestParam
					Integer id,
				@Validated
					PrefectureForm form,
					BindingResult result,
				@AuthenticationPrincipal	// ログイン中のLoginUserDetailsオブジェクトが取得できる
					LoginUserDetails userDetails
					) {
		if (result.hasErrors()) {
			return editForm(id, form);
		}
		
		// 送信されたPrefectureFormをprefectureにコピーする
		Prefecture prefecture = new Prefecture();
		BeanUtils.copyProperties(form, prefecture);
		// 更新処理を実施
		prefecture.setId(id);
		prefectureService.update(prefecture, userDetails.getUser());
		return "redirect:/prefectures";	// 処理が完了したら一覧表示画面にリダイレクトする
	}
	
	@PostMapping(path="edit", params="goToTop")
	String goToTop() {
		return "redirect:/prefectures";
	}

	@PostMapping(path="delete")
	String delete(@RequestParam Integer id) {
		prefectureService.delete(id);
		return "redirect:/prefectures";	// 処理が完了したら一覧表示画面にリダイレクトする
	}
}
