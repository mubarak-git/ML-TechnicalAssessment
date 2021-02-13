package com.demo.featureswitches.featureswitches.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
@NoArgsConstructor
public class Request {

   @Size(min=4, max=255)
   private String featureName;

   @Size(min=4, max=255)
   private String email;

   @NotNull
   private boolean enable;
}
