package com.pfe.ai.ai.controller;

import com.pfe.ai.ai.model.Fiche;
import com.pfe.ai.ai.repository.FicheRepository;
import com.pfe.ai.ai.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fiche")
public class FicheController {
    private final FicheRepository ficheRepository;
    private final UserRepository userRepository;
    @PostMapping("/create")
    public ResponseEntity<?> createFiche(@RequestBody Fiche newFiche){
        Fiche savedFiche = ficheRepository.save(newFiche);
        return ResponseEntity.ok(savedFiche);
    }

    @GetMapping("/all/{teacherId}")
    public ResponseEntity<?> getFichesForTeacher(@PathVariable Long teacherId){
       // List<Fiche> fiches = ficheRepository.findByTeacher_id(teacherId);
        return ResponseEntity.ok(
                ficheRepository.findAll()
                        .stream()
                        .filter(fiche -> fiche.getTeacher_id().equals(teacherId))
                        .collect(Collectors.toList())
        );
    }
}
