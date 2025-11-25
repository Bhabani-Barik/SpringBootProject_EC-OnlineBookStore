package com.nareshIt.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class CartEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private int quantity;
	
	private double totalPrice;
	
	@ManyToOne
	@JoinColumn(name="book_id", updatable = false, nullable = false)
	private BooksEntity booksEntity;
	
	@ManyToOne
	@JoinColumn(name="customer_id", updatable = false, nullable = false)
	private Customer customer;
	
	@CreationTimestamp
	@Column(name = "create_date")
	private LocalDateTime createdDate;
	
	@CreationTimestamp
	@Column(name = "update_date")
	private LocalDateTime updateDate;

	public CartEntity(int quantity, BooksEntity booksEntity, Customer customer) {
		super();
		this.quantity = quantity;
		this.booksEntity = booksEntity;
		this.customer = customer;
	}
	
	
	
	
	
}
