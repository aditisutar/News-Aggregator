package com.newsaggregator.service;

import com.newsaggregator.entity.Users;
import com.newsaggregator.model.UserModel;


public interface ResgistrationService {
  public   Users registerUser(UserModel userModel);
   public void createVerificationToken(Users users,String token);
   public boolean validateTokenANdEnableUser(String token);


}
