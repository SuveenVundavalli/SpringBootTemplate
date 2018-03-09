package me.suveen.portfolio.backend.service;

import me.suveen.portfolio.backend.persistence.domain.backend.User;
import me.suveen.portfolio.backend.persistence.domain.backend.UserRole;
import me.suveen.portfolio.backend.persistence.repositories.RoleRepository;
import me.suveen.portfolio.backend.persistence.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;


@Service
@Transactional(readOnly = true)
public class UserService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /** The application logger **/
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Transactional
    public User createUser(User user, Set<UserRole> userRoles) {

        String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);

        for (UserRole userRole : userRoles) {
            roleRepository.save(userRole.getRole());
        }
        user.getUserRoles().addAll(userRoles);
        user = userRepository.save(user);
        return user;
    }

    @Transactional
    public void updateUserPassword(long userId, String password) {
        password = bCryptPasswordEncoder.encode(password);
        userRepository.updateUserPassword(userId, password);
        LOG.debug("Password updated successfully updated for userId {}", userId);
    }

}
