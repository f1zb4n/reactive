package com.example.reactive.model;

import lombok.*;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * Class which does something useful. Really.
 *
 * @author Marco Werner
 */
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    private Long id;
    @Size(min = 2, max = 560) // beat twitter by factor 2
    private String text;
    private LocalDateTime timestamp;

}
