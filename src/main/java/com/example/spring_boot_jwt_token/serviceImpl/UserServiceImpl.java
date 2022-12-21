package com.example.spring_boot_jwt_token.serviceImpl;

import com.example.spring_boot_jwt_token.dto.mapper.UserMapper;
import com.example.spring_boot_jwt_token.dto.request.UserRequest;
import com.example.spring_boot_jwt_token.dto.response.UserResponse;
import com.example.spring_boot_jwt_token.entity.Role;
import com.example.spring_boot_jwt_token.entity.User;
import com.example.spring_boot_jwt_token.repository.RoleRepository;
import com.example.spring_boot_jwt_token.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserResponse create(UserRequest request) {
        User user = userMapper.mapToEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        Role role = roleRepository.findById(3L).get();
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
        return userMapper.mapToResponse(user);
    }

    private List<UserResponse> mapToResponseList(List<User> users){
        List<UserResponse> registerResponses = new ArrayList<>();
        for (User user: users) {
            registerResponses.add(userMapper.mapToResponse(user));
        }

        return registerResponses;
    }


    @PostConstruct
    public void initMethod(){
        Role role1 = new Role();
        role1.setName("ADMIN");

        Role role2 = new Role();
        role2.setName("INSTRUCTOR");

        Role role3 = new Role();
        role3.setName("STUDENT");

        UserRequest request = new UserRequest();
        request.setEmail("aruuke@gmail.com");
        request.setPassword(passwordEncoder.encode("1301"));

        User user2 = userMapper.mapToEntity(request);

        user2.setRoles(Arrays.asList(role1));
        role1.setUsers(Arrays.asList(user2));

        userRepository.save(user2);
        roleRepository.save(role1);
        roleRepository.save(role2);
        roleRepository.save(role3);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("not found email"));
    }

    public List<UserResponse> getAllUsers(){
        return mapToResponseList(userRepository.findAll());
    }

    public UserResponse getUserById(Long id){
        return userMapper.mapToResponse(userRepository.findById(id).get());
    }

    public UserResponse updateUser(Long id, UserRequest userRequest){
        User user = userRepository.findById(id).get();
        if (userRequest.getEmail() != null) {
            user.setEmail(userRequest.getEmail());
        }
        if (userRequest.getPassword() != null) {
            user.setPassword(userRequest.getPassword());
        }
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        userRepository.save(user);
        return userMapper.mapToResponse(user);
    }

    public UserResponse deleteUser(Long id) {
        User user = userRepository.findById(id).get();
        for (Role role: user.getRoles()) {
            role.getUsers().remove(user);
        }
        userRepository.delete(user);
        return userMapper.mapToResponse(user);
    }

    public UserResponse changeRole(Long roleId, Long userId) throws IOException {
        User user = userRepository.findById(userId).get();
        Role role = roleRepository.findById(roleId).get();
        if (role.getName().equals("Admin")) {
            throw new IOException("only 1 user can be admin");
        }

        for (Role r: user.getRoles()) {
            if (r.getName().equals(role.getName())){
                throw new IOException("This user already have this role");
            }
        }

        user.getRoles().add(role);
        role.getUsers().add(user);

        userRepository.save(user);
        roleRepository.save(role);

        return userMapper.mapToResponse(user);
    }
}