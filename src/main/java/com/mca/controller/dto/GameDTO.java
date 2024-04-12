package com.mca.controller.dto;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class GameDTO {

    String id;

    boolean availability;

    String title;

    BigDecimal price;
}
