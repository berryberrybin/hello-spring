package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class MemberController {
    private final MemberService memberService;

    // 생성자 주입
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // @Autowired private MemberService memberSerivce;  필드 주입

    /* Setter 주입
    @Autowired
    public void setMemberService(MemberService memberService){
        this.memberService = memberService;
    }
     */

    @GetMapping("/members/new") // 생성할 화면을 보여줌
    public String createForm() {
        return "members/createMemberForm";
    }


    // <form action="/members/new" method="post"> 등록버튼을 눌렀을때 @PostMapping("/members/new")로 요청이 옴
    @PostMapping("/members/new") // 멤버 추가 API
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/"; // 다시 홈페이지로 돌아감
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberlist";
    }

}
