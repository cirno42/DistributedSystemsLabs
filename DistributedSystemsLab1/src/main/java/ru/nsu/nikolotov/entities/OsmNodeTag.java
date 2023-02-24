package ru.nsu.nikolotov.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class OsmNodeTag {
    private String key;
    private String value;
}
