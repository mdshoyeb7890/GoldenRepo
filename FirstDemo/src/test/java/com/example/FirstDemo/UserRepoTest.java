package com.example.FirstDemo;

import com.example.FirstDemo.Repository.UserRepo;
import com.example.FirstDemo.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;





import java.util.Optional;

@DataJpaTest
public class UserRepoTest {

    @Autowired
    private UserRepo userRepo;

    @Test
   void saveAndFetch() {
       UserEntity user = new UserEntity(null, "Shoyeb", 25, "Bihar");
       UserEntity saved = userRepo.save(user);

       Optional<UserEntity> fetchUser = userRepo.findByName("Shoyeb");

        assertThat(fetchUser).isPresent();
        assertThat(fetchUser.get().getCity()).isEqualTo("Bihar");
   }



}
