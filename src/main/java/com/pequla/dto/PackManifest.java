package com.pequla.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class PackManifest {
    private Minecraft minecraft;
    private String manifestType;
    private int manifestVersion;
    private String name;
    private String version;
    private String author;
    private List<FileData> files;
    private String overrides;
}
