package com.example.demo.service;

import com.example.demo.entity.Rating;
import com.example.demo.entity.User;
import com.example.demo.entity.userdata.ActivitySphere;
import com.example.demo.entity.userdata.EducationLevel;
import com.example.demo.entity.userdata.Gender;
import com.example.demo.entity.userdata.IncomeLevel;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.userdata.ActivitySphereRepository;
import com.example.demo.repository.userdata.EducationRepository;
import com.example.demo.repository.userdata.GenderRepository;
import com.example.demo.repository.userdata.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.naming.NoPermissionException;
import javax.security.auth.login.LoginException;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl {
    @Autowired
    private UserRepository repository;

    @Autowired
    private GenderRepository genderRepository;

    @Autowired
    private EducationRepository educationRepository;

    @Autowired
    private ActivitySphereRepository activitySphereRepository;

    @Autowired
    private IncomeRepository incomeRepository;

    public User login(String login, String password) throws LoginException {
        User foundedUser = repository.findByEmail(login);
        if (foundedUser != null) {
            if (DigestUtils.md5DigestAsHex(password.getBytes()).equals(foundedUser.getPassword())) {
                return foundedUser;
            } else throw new LoginException();
        } else throw new LoginException();
    }

    public User signUp(User user) throws LoginException {
        Pattern email = Pattern.compile(
                "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                        "\\@" +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                        "(" +
                        "\\." +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                        ")+"
        );
        if (email.matcher(user.getEmail()).matches()) {
            User foundedUser = repository.findByEmail(user.getEmail());
            if (foundedUser == null) {
//                User user = new User();
//                user.setEmail(login);
                user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
                return repository.save(user);
            } else {
                throw new LoginException("User exists");
            }
        } else {
            throw new LoginException();
        }
    }
    
    public User instantSignUp(String login, String password) throws NoPermissionException {
        User foundedUser = repository.findByEmail(login);
        if (foundedUser != null) {
            if (DigestUtils.md5DigestAsHex(password.getBytes()).equals(foundedUser.getPassword())) {
                return foundedUser;
            } else throw new NoPermissionException();
        } else {
            User user = new User();
            user.setEmail(login);
            user.setPassword(password);
            return repository.save(user);
        }
    }

    @Transactional
    public User update(User user) {
        return repository.save(user);
    }

    public User getUserById(Long id) {
        return repository.findById(id).orElse(new User());
    }

    public List<Gender> getAllGenders() {
        return (List<Gender>) genderRepository.findAll();
    }

    public List<EducationLevel> getAllEducationLevels() {
        return (List<EducationLevel>) educationRepository.findAll();
    }

    public List<ActivitySphere> getAllActivities() {
        return (List<ActivitySphere>) activitySphereRepository.findAll();
    }

    public List<IncomeLevel> getAllIncomes() {
        return (List<IncomeLevel>) incomeRepository.findAll();
    }
}
