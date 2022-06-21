package uz.online.mahsulotlar.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.online.mahsulotlar.Dto.RegisterDto;
import uz.online.mahsulotlar.Entity.Enum.RoleNames;
import uz.online.mahsulotlar.Entity.Users;
import uz.online.mahsulotlar.Entity.model.ApiResponse;
import uz.online.mahsulotlar.Repository.RoleRepository;
import uz.online.mahsulotlar.Repository.UserRepository;
import uz.online.mahsulotlar.Security.JwtProvider;

import java.util.Collections;
import java.util.List;

@Service
public class MyAuthService implements UserDetailsService {

    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<Users> userList = userRepository.findAll();

        for (Users user : userList) {
            if (user.getUsername().equals(username)) {
                System.out.println(jwtProvider.generateToken(username));
                return user;
            }
        }
        throw new UsernameNotFoundException("User topilmadi");
    }
    public ApiResponse registerUser(RegisterDto registerDto) {
        boolean existsByEmail = userRepository.existsByEmail(registerDto.getEmail());
        if (existsByEmail)
            return new ApiResponse("That employee already exists", false);
        Users users = new Users();
        users.setFirstName(registerDto.getFirstName());
        users.setLastName(registerDto.getLastName());
        users.setEmail(registerDto.getEmail());
        users.setPhoneNumber(registerDto.getPhoneNumber());
        users.setRoles(Collections.singleton(roleRepository.findByRoleNames(RoleNames.ROLE_ADMIN)));
        users.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        userRepository.save(users);
        return new ApiResponse("saqlandi",true);
    }

}
