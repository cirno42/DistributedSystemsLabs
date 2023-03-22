package ru.nsu.anikolotov.hashcracker.manager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class CrackHashManagerResponse {

    private UUID requestId;
}
