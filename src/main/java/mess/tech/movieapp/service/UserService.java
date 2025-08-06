package mess.tech.movieapp.service;

import jakarta.persistence.EntityNotFoundException;
import mess.tech.movieapp.entites.User;
import mess.tech.movieapp.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.Optional;


@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(User user) {
        this.userRepository.save(user);
    }

    public Page<User> getAllUsers(Pageable pageable) {
        return this.userRepository.findAll(pageable);
    }

    public User getUserById(Long id) {
        Optional<User> optionalUser = this.userRepository.findById(id);
        return optionalUser.orElseThrow(
                () -> new EntityNotFoundException("No user exists with this id")
        );
    }

    public void removeUserById(Long id) {
        this.userRepository.deleteById(id);
    }

    public void editUserById(Long id, User user) {
        User userById = this.getUserById(id);
        User userEdited = this.setEditUserById(userById, user);
        this.userRepository.save(userEdited);
    }

    public User setEditUserById(User userById, User user){
        userById.setUsername(user.getUsername());
        userById.setPassword(user.getPassword());
        userById.setEmail(user.getEmail());
        userById.setRole(user.getRole());

        return userById;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(userName);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with : " + userName);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole().toString())));
    }
}
