package ru.nsu.anikolotov.hashcracker.manager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.nsu.anikolotov.hashcracker.manager.dto.CrackHashRequest;
import ru.nsu.anikolotov.hashcracker.manager.dto.CrackHashManagerResponse;
import ru.nsu.anikolotov.hashcracker.manager.dto.CrackingStatusResponse;
import ru.nsu.anikolotov.hashcracker.manager.service.ManagerService;

import java.util.UUID;

@RestController
@RequestMapping("/api/hash")
@RequiredArgsConstructor
public class HashController {

    private final ManagerService managerService;

    @PostMapping("/crack")
    public CrackHashManagerResponse requestCrackingHash(@RequestBody CrackHashRequest request) {
        UUID requestId = managerService.crackHash(request);
        return new CrackHashManagerResponse(requestId);
    }

    @GetMapping("/status/{requestId}")
    public CrackingStatusResponse getCrackingStatus(@PathVariable UUID requestId) {
        return managerService.getStatus(requestId);
    }
}
