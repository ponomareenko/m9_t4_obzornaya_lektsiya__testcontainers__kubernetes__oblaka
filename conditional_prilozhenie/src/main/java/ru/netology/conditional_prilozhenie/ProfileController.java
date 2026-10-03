package ru.netology.conditional_prilozhenie;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileController {
    private final SystemProfile profile;

    public ProfileController(SystemProfile profile) {
        this.profile = profile;
    }

    @GetMapping("/profile")
    public String getProfile() {
        return profile.getProfile();
    }
}
