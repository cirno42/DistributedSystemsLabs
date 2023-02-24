package ru.nsu.nikolotov.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OsmNode {

    private Long id;
    private String user;
    private List<OsmNodeTag> tags = new ArrayList<>();
}
