package com.newsaggregator.service.serviceImpl;

import com.newsaggregator.entity.Users;
import com.newsaggregator.entity.VerificationToken;
import com.newsaggregator.exception.EmailAlreadyExistsException;
import com.newsaggregator.model.UserModel;
import com.newsaggregator.repo.UserRepo;
import com.newsaggregator.repo.VerificationTokenRepo;
import com.newsaggregator.service.EmailService;
import com.newsaggregator.service.ResgistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResgistrationServiceImpl implements ResgistrationService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private VerificationTokenRepo verificationTokenRepo;
    @Autowired
    private EmailService emailService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Users registerUser(UserModel userModel) {
        Users users=new Users();
        users.setEmail(userModel.getEmail());
        //check if email already present
        Optional<Users> userAlreadyPresent=userRepo.findByEmail(users.getEmail());
        if(userAlreadyPresent.isPresent()){
            throw new EmailAlreadyExistsException("Email already exists: " + users.getEmail());
        }
        users.setFirstName(userModel.getFirstName());
        users.setLastName(userModel.getLastName());
        users.setPassword(passwordEncoder.encode(userModel.getPassword()));
        users.setRole("User");
        users.setIsEnabled(false);
        userRepo.save(users);

        return users;
    }
    public void createVerificationToken(Users users, String token){
        VerificationToken verificationToken=new VerificationToken(users,token);
       verificationTokenRepo.save(verificationToken);
    }
    public boolean validateTokenANdEnableUser(String token){
      VerificationToken verificationToken= verificationTokenRepo.findByToken(token);
      if(verificationToken==null){
        return false;

      }
       if(verificationToken.getExpirationTime().getTime() > System.currentTimeMillis()){
        Users users=verificationToken.getUsers();
        if(!users.getIsEnabled()){
            users.setIsEnabled(true);
            userRepo.save(users);
            verificationTokenRepo.delete(verificationToken);
            return true;
        }
       }
       return false;
    }


}
