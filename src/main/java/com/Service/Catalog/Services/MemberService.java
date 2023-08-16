package com.Service.Catalog.Services;

import com.Service.Catalog.Entities.Member;
import com.Service.Catalog.Repositories.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<Member> getAllMembers(){
        return memberRepository.findAll();
    }

    public Member findMemberByCin(String cin){
        return memberRepository.findByCin(cin).orElseThrow(() -> new IllegalStateException("User doesn't exist"));
    }

    public void addMember(Member member){
        Optional<Member> userOptional = memberRepository.findByCin(member.getCin());
        if(userOptional.isPresent()){
            throw new IllegalStateException("User already exists");
        }
        memberRepository.save(member);
    }

    @Transactional
    public void updateMember(String cin, Member request){
        Member member = memberRepository.findByCin(cin).orElseThrow(() -> new IllegalStateException("User doesn't exist"));
        if(request.getMatriculation() != null && request.getMatriculation().length() > 0 && !Objects.equals(member.getMatriculation(), request.getMatriculation())){
            member.setMatriculation(request.getMatriculation());
        }
        if(request.getPassword() != null && request.getPassword().length() >0 && !Objects.equals(member.getPassword(), request.getPassword())){
            member.setPassword(request.getPassword());
        }
    }

    public void deleteMemberByCin (String cin){
        boolean exists = memberRepository.findByCin(cin).isPresent();
        if(!exists){
            throw new IllegalStateException("User with CIN "+cin+" does not exist");
        }
        memberRepository.deleteByCin(cin);
    }
}
