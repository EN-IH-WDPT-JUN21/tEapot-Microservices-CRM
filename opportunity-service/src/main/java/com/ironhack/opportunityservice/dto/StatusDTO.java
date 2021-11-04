package com.ironhack.opportunityservice.dto;

import com.ironhack.opportunityservice.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatusDTO {
    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;
}
