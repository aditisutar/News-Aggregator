package com.newsaggregator.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class VerificationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long tokenId;
    private String token;
    private Date expirationTime;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false,name = "userId")
    private Users users;

    public VerificationToken(Users users,String token){
        this.token=token;
        this.users=users;
        this.expirationTime=calculateExpirationTime();

    }

    public Date calculateExpirationTime() {
        return new Date(System.currentTimeMillis()+10*60*1000);  //10 minutes expiration time
    }
}
