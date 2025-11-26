package com.nieve.backend.config;

import com.nieve.backend.model.Producto;
import com.nieve.backend.model.User;
import com.nieve.backend.repository.ProductoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import com.nieve.backend.repository.UserRepository;

import java.util.Date;

@Component
public class DataLoader implements CommandLineRunner {

    private final ProductoRepository repo;
    private final UserRepository userRepo;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public DataLoader(ProductoRepository repo, UserRepository userRepo) {
        this.repo = repo;
        this.userRepo = userRepo;

    }

    @Override
    public void run(String... args) {

        if (repo.count() == 0) {

            repo.save(new Producto(
                    null,
                    17990,
                    "Tarta de Queso",
                    "Clásica Tarta de queso.",
                    "https://th.bing.com/th/id/R.634fa577892c3ff6717ddc8c97a396d9?rik=6pyiG1yVAsgFcA&riu=http%3a%2f%2fwww.taste.com.au%2fimages%2frecipes%2fagt%2f2008%2f05%2f19621.jpg&ehk=5yT6egtaJbt%2bAOKsKjm5tJmh3i%2b0OcKZcPvEIHuQy%2fQ%3d&risl=&pid=ImgRaw&r=0",
                    "Tortas",
                    null
            ));

            repo.save(new Producto(
                    null,
                    15990,
                    "Tarta de Chocolate",
                    "Rica tarta de chocolate amargo, ideal para compartir.",
                    "https://www.chocolatto.co/wp-content/uploads/2020/07/IMG_0170-scaled.jpg",
                    "Tortas",
                    null
            ));

            repo.save(new Producto(
                    null,
                    14990,
                    "Torta Frutilla",
                    "Pastel de bizcocho de chocolate con cereza",
                    "https://www.rama.com.co/-/media/Project/Upfield/Brands/Rama/Rama-CO/Assets/Recipes/sync-img/5c3923a9-ab27-4d26-aaa1-9500924c666f.png?rev=96b46b00ed0a4007ace051f2b1161f54&w=900",
                    "Tortas",
                    null
            ));

            }
        if (userRepo.count() == 0) {

            userRepo.save(new User(
                    null,
                    "fran@nieve.cl",
                    encoder.encode("holis123"),
                    "Cliente",
                    "12345678-9",
                    new Date(),
                    null
            ));

            userRepo.save(new User(
                    null,
                    "felipe@nieve.cl",
                    encoder.encode("holis123"),
                    "Cliente",
                    "98765432-1",
                    new Date(),
                    null
            ));
        }
    }
}