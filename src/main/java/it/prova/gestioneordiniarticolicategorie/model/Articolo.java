package it.prova.gestioneordiniarticolicategorie.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "articolo")
public class Articolo {

	// (id, descrizione, numeroSeriale, prezzoSingolo, dataInserimento, ordine
	// (nullable=false), categorie )
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "descrizione")
	private String descrizione;
	@Column(name = "numeroseriale")
	private String numeroSeriale;
	@Column(name = "prezzosingolo")
	private Double prezzoSingolo;
	@Column(name = "datainserimento")
	private LocalDate dataInserimento;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ordine_id", nullable = false)
	private Ordine ordine;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "articolo_categoria", joinColumns = @JoinColumn(name = "articolo_id", referencedColumnName = "ID"), inverseJoinColumns = {
			@JoinColumn(name = "categoria_id", referencedColumnName = "ID") })
	Set<Categoria> categorie = new HashSet<>();

	public Articolo() {
		super();
	}

	
	
//	public Articolo(String descrizione, String numeroSeriale, Double prezzoSingolo, LocalDate dataInserimento) {
//		super();
//		this.descrizione = descrizione;
//		this.numeroSeriale = numeroSeriale;
//		this.prezzoSingolo = prezzoSingolo;
//		this.dataInserimento = dataInserimento;
//	}



	public Articolo(String descrizione, String numeroSeriale, Double prezzoSingolo, LocalDate dataInserimento,
			Ordine ordine) {
		super();
		this.descrizione = descrizione;
		this.numeroSeriale = numeroSeriale;
		this.prezzoSingolo = prezzoSingolo;
		this.dataInserimento = dataInserimento;
		this.ordine = ordine;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getNumeroSeriale() {
		return numeroSeriale;
	}

	public void setNumeroSeriale(String numeroSeriale) {
		this.numeroSeriale = numeroSeriale;
	}

	public Double getPrezzoSingolo() {
		return prezzoSingolo;
	}

	public void setPrezzoSingolo(Double prezzoSingolo) {
		this.prezzoSingolo = prezzoSingolo;
	}

	public LocalDate getDataInserimento() {
		return dataInserimento;
	}

	public void setDataInserimento(LocalDate dataInserimento) {
		this.dataInserimento = dataInserimento;
	}

	public Ordine getOrdine() {
		return ordine;
	}

	public void setOrdine(Ordine ordine) {
		this.ordine = ordine;
	}

	public Set<Categoria> getCategorie() {
		return categorie;
	}

	public void setCategorie(Set<Categoria> categorie) {
		this.categorie = categorie;
	}
	
	public void addToCategorie (Categoria categoria) {
		this.categorie.add(categoria);
		categoria.getArticoli().add(this);
		
	}
	
	public void removeFromCategorie (Categoria categoria) {
		this.categorie.remove(categoria);
		categoria.getArticoli().remove(this);
		
	}

}
