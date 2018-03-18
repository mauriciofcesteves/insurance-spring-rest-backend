
package com.study.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.joda.time.LocalDate;

@Entity(name = "Professor")
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Customer extends Funcionario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(length=11)
	private String cpf;
	
	@Column(length=14)
	private String cnpj;
	
	@Column(length=7)
	private String placa;
	
	@Column(length=50)
	private String seguradora;

	@Column(length=50)
	private String titulacao;
	
	@Column(name="vigenciainicio", nullable = true)
	private Date vigenciaInicio;
	
	@Column(name="vigenciafim", nullable = true)
	private Date vigenciaFim;

	@Column(length=100)
	private String observacao;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getSeguradora() {
		return seguradora;
	}

	public void setSeguradora(String seguradora) {
		this.seguradora = seguradora;
	}

	public String getTitulacao() {
		return titulacao;
	}

	public void setTitulacao(String titulacao) {
		this.titulacao = titulacao;
	}

	public String getVigenciaInicio() {
		return new LocalDate(vigenciaInicio).toString("dd/MM/yyyy");
	}

	public void setVigenciaInicio(Date vigenciaInicio) {
		this.vigenciaInicio = vigenciaInicio;
	}

	public String getVigenciaFim() {
		return new LocalDate(vigenciaFim).toString("dd/MM/yyyy");
	}

	public void setVigenciaFim(Date vigenciaFim) {
		this.vigenciaFim = vigenciaFim;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
}
