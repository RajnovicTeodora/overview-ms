package com.notbooking.overviewms.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(value = "host")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Host{

    @Id
    private String id;
    private String username;
    private List<Accomodation> accomodations;

}
