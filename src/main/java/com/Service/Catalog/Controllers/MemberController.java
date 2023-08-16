package com.Service.Catalog.Controllers;

import com.Service.Catalog.Entities.Member;
import com.Service.Catalog.Services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/all")
    public List<Member> getAllMembers(){
        return memberService.getAllMembers();
    }

    @PostMapping("/add")
    public void addMember(@RequestBody Member member){
        memberService.addMember(member);
    }

    @PutMapping("/update/{cin}")
    public void updateMember(@PathVariable("cin") String cin, Member request){
        memberService.updateMember(cin, request);
    }

    @DeleteMapping("/delete/{cin}")
    public void deleteMember(@PathVariable("cin") String cin){
        memberService.deleteMemberByCin(cin);
    }
}
