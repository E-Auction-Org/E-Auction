
package edu.eci.eauction.service.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Objects;

@Document(collection="db_sequence")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DbSequence {

    @Id
    private String id;

    private int seq;
}
