package com.rdas.hz.hzq.mq;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReliableMessage implements Serializable {
    private Long id;
    private String message;
}
