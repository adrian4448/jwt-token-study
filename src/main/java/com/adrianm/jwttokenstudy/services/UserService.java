package com.adrianm.jwttokenstudy.services;

import com.adrianm.jwttokenstudy.model.Role;
import com.adrianm.jwttokenstudy.model.User;
import com.adrianm.jwttokenstudy.model.repository.UsersRepository;
import com.adrianm.jwttokenstudy.rest.controllers.dto.LinkRolesDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    private final UsersRepository usersRepository;

    private final RoleService roleService;

    public User createUser(final User userToCreate) {
        final User userToFind = User
                .builder()
                .userId(userToCreate.getUserId())
                .build();

        final Example<User> example = Example
                .of(userToFind);

        if (usersRepository.exists(example)) {
            log.info("Ja existe um usuario com esse userId {}", userToCreate.getUserId());
            throw new RuntimeException("Ja existe um usuario com esse userId");
        }

        return usersRepository.save(userToCreate);
    }

    public Optional<User> findUserById(final String id) {
        return usersRepository.findById(id);
    }

    public List<User> getUsers(final User userFilter) {
        final Example example = Example.of(userFilter);

        return usersRepository.findAll(example);
    }

    public Optional<User> getUser(final User userFilter) {
        final Example example = Example.of(userFilter);

        return usersRepository.findOne(example);
    }

    public void linkRoles(LinkRolesDTO roles, String userId) {
        User user = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("nao existe um usuario com esse userId"));

        roles.getRolesId().forEach(roleId -> {
            final Optional<Role> role = roleService.findRoleById(roleId);

            if (role.isEmpty()) {
                throw new RuntimeException("Este papel nao existe e nao pode ser vinculado");
            }

            user.addRole(role.get());
        });

        usersRepository.save(user);
    }

    public List<Role> getUserRoles(String userId) {
        User user = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("nao existe um usuario com esse userId"));

        return user.getRoles();
    }

    @Autowired
    public UserService(final UsersRepository usersRepository,
                       final RoleService roleService) {
        this.usersRepository = usersRepository;
        this.roleService = roleService;
    }
}
