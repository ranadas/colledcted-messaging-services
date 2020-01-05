package com.rdas.common.message.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class ControlMessage implements Serializable {
    private final String name;
    private final String uuid;
}
