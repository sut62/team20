package com.cpe.backend.cancelsend.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@NoArgsConstructor
@Table(name="Cancelsend")
public class Cancelsend {



    @Id
    @SequenceGenerator(name="Cancelsend_seq",sequenceName="cancelsend_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="cancelsend_seq")
    @Column(name = "Cancel_ID", unique = true, nullable = true)
    private @NonNull Long id;

    @Column(name="Cancel_Price")
    private @NonNull Long price;

//ew
}
