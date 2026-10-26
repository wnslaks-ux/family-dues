package com.family.dues.controller;
import com.family.dues.service.DuesService;import com.family.dues.vo.*;import org.springframework.stereotype.Controller;import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.time.YearMonth;import java.util.*;import java.util.stream.*;
@Controller public class DuesController {
 private final DuesService s; public DuesController(DuesService s){this.s=s;}
 private void fill(Model model, String ym){
   List<PaymentVo> list=s.month(ym); List<PaymentVo> unpaid=list.stream().filter(p->!p.isPaid()).collect(Collectors.toList());
   model.addAttribute("ym",ym); model.addAttribute("ymPrev",YearMonth.parse(ym).minusMonths(1).toString()); model.addAttribute("ymNext",YearMonth.parse(ym).plusMonths(1).toString());
   model.addAttribute("payments",list); model.addAttribute("unpaid",unpaid); model.addAttribute("settings",s.settings());
   model.addAttribute("paidCount",list.size()-unpaid.size());
   model.addAttribute("collected",list.stream().filter(PaymentVo::isPaid).mapToLong(PaymentVo::getAmount).sum());
   model.addAttribute("expected",list.stream().mapToLong(PaymentVo::getAmount).sum());
   model.addAttribute("allUnpaidMsg",unpaid.stream().map(PaymentVo::getMessage).collect(Collectors.joining("\n\n----------\n\n"))); }
 @GetMapping("/") public String home(Model m){ fill(m, YearMonth.now().toString()); return "home"; }
 @GetMapping("/dues") public String dues(@RequestParam(required=false) String ym, Model m){ fill(m, DuesService.normalize(ym)); return "dues"; }
 @PostMapping("/dues/{id}/paid") public String paid(@PathVariable long id, @RequestParam boolean paid, @RequestParam String ym){ s.setPaid(id,paid); return "redirect:/dues?ym="+DuesService.normalize(ym); }
 @GetMapping("/members") public String members(@RequestParam(required=false) Long edit, Model m){ m.addAttribute("members",s.members()); m.addAttribute("edit", edit==null?new MemberVo():s.member(edit)); return "members"; }
 @PostMapping("/members") public String save(@ModelAttribute MemberVo v, RedirectAttributes ra){ if(v.getName()!=null&&!v.getName().isBlank()){ s.save(v); ra.addFlashAttribute("msg","저장했습니다."); } return "redirect:/members"; }
 @PostMapping("/members/{id}/delete") public String del(@PathVariable long id, RedirectAttributes ra){ s.delete(id); ra.addFlashAttribute("msg","삭제했습니다."); return "redirect:/members"; }
 @GetMapping("/settings") public String settings(Model m){ m.addAttribute("settings",s.settings()); return "settings"; }
 @PostMapping("/settings") public String saveSettings(@ModelAttribute SettingsVo v, RedirectAttributes ra){ s.saveSettings(v); ra.addFlashAttribute("msg","설정을 저장했습니다. (이미 생성된 달의 금액은 바뀌지 않고, 새 달부터 적용됩니다)"); return "redirect:/settings"; }
}
