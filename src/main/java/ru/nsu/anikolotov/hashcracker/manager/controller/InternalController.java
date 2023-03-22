package ru.nsu.anikolotov.hashcracker.manager.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.anikolotov.hashcracker.manager.dto.CrackHashWorkerResponse;
import ru.nsu.anikolotov.hashcracker.manager.service.ManagerService;

@RestController
@RequestMapping("/internal/api/manager")
@RequiredArgsConstructor
public class InternalController {

    private final ManagerService managerService;

    @PatchMapping("/hash/crack/request")
    public void updateStatus(@RequestBody CrackHashWorkerResponse response) {
        managerService.updateStatus(response);
    }
}
