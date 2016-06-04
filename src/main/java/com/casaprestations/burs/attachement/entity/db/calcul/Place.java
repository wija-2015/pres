package com.casaprestations.burs.attachement.entity.db.calcul;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Place {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer idPlace;
		private String nomPlace;
		private Integer lot;
		private Double surfaceBrut;
		private Double surfaceNet;
		
		@JsonIgnore
		@OneToMany(mappedBy="place")
		private List<LavageBrossage> lavageBrossages ;

		public Place() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Integer getIdPlace() {
			return idPlace;
		}

		public void setIdPlace(Integer idPlace) {
			this.idPlace = idPlace;
		}

		
		public String getNomPlace() {
			return nomPlace;
		}

		public void setNomPlace(String nomPlace) {
			this.nomPlace = nomPlace;
		}

		public Integer getLot() {
			return lot;
		}

		public void setLot(Integer lot) {
			this.lot = lot;
		}

		public Double getSurfaceBrut() {
			return surfaceBrut;
		}

		public void setSurfaceBrut(Double surfaceBrut) {
			this.surfaceBrut = surfaceBrut;
		}

		public Double getSurfaceNet() {
			return surfaceNet;
		}

		public void setSurfaceNet(Double surfaceNet) {
			this.surfaceNet = surfaceNet;
		}

		public List<LavageBrossage> getLavageBrossages() {
			return lavageBrossages;
		}

		public void setLavageBrossages(List<LavageBrossage> lavageBrossages) {
			this.lavageBrossages = lavageBrossages;
		}		
}
