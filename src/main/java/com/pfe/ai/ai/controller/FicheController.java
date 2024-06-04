package com.pfe.ai.ai.controller;

import com.pfe.ai.ai.model.Fiche;
import com.pfe.ai.ai.repository.FicheRepository;
import com.pfe.ai.ai.repository.UserRepository;
import com.pfe.ai.ai.system.Result;
import com.pfe.ai.ai.system.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fiche")
@CrossOrigin(origins = "http://localhost:5173")
public class FicheController {
    private final FicheRepository ficheRepository;
    private final UserRepository userRepository;
    @PostMapping("/create")
    public ResponseEntity<?> createFiche(@RequestBody Fiche newFiche){
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Customize the format here
        String formattedDate = today.format(formatter);
        newFiche.setUploadedAt(formattedDate);
        Fiche savedFiche = ficheRepository.save(newFiche);
        return ResponseEntity.ok(savedFiche);
    }

    @GetMapping("/all/{teacherId}")
    public ResponseEntity<?> getFichesForTeacher(@PathVariable Long teacherId){
       List<Fiche> fiches = ficheRepository.findAll()
               .stream()
               .filter(fiche -> fiche.getTeacher_id().equals(teacherId))
               .collect(Collectors.toList());

        return ResponseEntity.ok(fiches);
    }
}
