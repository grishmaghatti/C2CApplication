package com.grishmaghatti.C2C.entities;

//import jakarta.persistence.*;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Students {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String  name;

    @Column(name = "branch", nullable = false)
    private String  branch;

    @Column(name = "score_class", nullable = false)
    private String  scoreClass;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private AddressEntity address;


}
