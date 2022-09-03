package com.pequla.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class FileData {

    private int projectID;
    private int fileID;
    private boolean required;

    @Override
    public String toString() {
        return this.projectID + ": " + this.fileID;
    }
}
