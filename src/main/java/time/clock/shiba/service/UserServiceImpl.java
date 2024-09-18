package time.clock.shiba.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import time.clock.shiba.domain.User;
import time.clock.shiba.global.exception.BadRequestException;
import time.clock.shiba.record.request.CreateUser;
import time.clock.shiba.repo.UserRepo;

import java.util.ArrayList;
import java.util.Collection;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User getUser(String userId) {
        return userRepo.findByUserId(userId);
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userRepo.findByUserId(userId);
        if(user == null) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        }else{
            log.info("User found in the database : {}", userId);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("임시"));
        return new org.springframework.security.core.userdetails.User(user.getUserId(), user.getUserPw(), authorities);
    }

    @Override
    @Transactional
    public void createUser(CreateUser request) {

        User user = userRepo.findByUserId(request.userId());

        if(user != null){
            throw new BadRequestException("이미 있는 유저아이디 입니다.");
        }else{
            userRepo.save(request.toEntity(request, passwordEncoder));
        }

    }

    @Override
    @Transactional
    public void deleteUser() {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = user.getUserId();

        User findDeleteUser = userRepo.findById(userId).orElseThrow(()-> new BadRequestException("없는 유저아이디 입니다."));
        findDeleteUser.deleteUser();

    }
}
