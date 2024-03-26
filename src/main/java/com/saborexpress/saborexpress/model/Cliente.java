package com.saborexpress.saborexpress.model;


import com.saborexpress.saborexpress.domain.ClienteEnum;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Cliente")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Cliente {

    @Id
    private String nome;

    @Column(length = 100)
    private String email;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ClienteEnum documento;



}
