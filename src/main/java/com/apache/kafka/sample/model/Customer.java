package com.apache.kafka.sample.model;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Customer {
    private String id;
    private String name;
    private String email;
    private String contactNo;
}
