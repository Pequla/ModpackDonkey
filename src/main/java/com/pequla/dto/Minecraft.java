package com.pequla.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class Minecraft {

    private String version;
    private List<ModLoader> modLoaders;
}
