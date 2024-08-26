package com.newsaggregator.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.repository.cdi.Eager;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserPreference {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long prefernceId;
    @NotEmpty
    private Set<String> category;
    @Size(min = 2)
    @Pattern(regexp = "^[a-z]{2}$", message = "Invalid language code")
    private String language;
    @Pattern(regexp = "^[a-z]{2}$", message = "Invalid country code")
    private String country;
    @OneToOne(fetch = FetchType.EAGER)
    private Users user;


}
