package br.com.ekom.hateoas.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "empregado")
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "empregadoId",
		scope = Empregado.class
)
public class Empregado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empregadoId;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "departamento_id",
            referencedColumnName = "departamento_id")
    private Departamento departamento;

    public Empregado() {
    }

    public Empregado(Long empregadoId, String nome, String email, Departamento departamento) {
        this.empregadoId = empregadoId;
        this.nome = nome;
        this.email = email;
        this.departamento = departamento;
    }

    public Long getEmpregadoId() {
        return empregadoId;
    }

    public void setEmpregadoId(Long empregadoId) {
        this.empregadoId = empregadoId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
}
