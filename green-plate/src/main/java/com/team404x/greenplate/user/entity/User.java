package com.team404x.greenplate.user.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.team404x.greenplate.cart.entity.Cart;
import com.team404x.greenplate.item.review.entity.ItemReview;
import com.team404x.greenplate.orders.entity.Orders;
import com.team404x.greenplate.recipe.entity.Recipe;
import com.team404x.greenplate.recipe.likes.RecipeLike;
import com.team404x.greenplate.recipe.review.RecipeReview;
import com.team404x.greenplate.user.address.entity.Address;
import com.team404x.greenplate.user.keyword.entity.UserKeyword;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String email;
	private String password;
	private String name;
	private String nickName;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	private LocalDate birthday;
	private int loginCount;
	private String role;
	private boolean enabled;
	@ColumnDefault("false")
	private boolean delYN;
	@CreatedDate
	LocalDateTime createdDate;
	@LastModifiedDate
	LocalDateTime modifiedDate;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Orders> orders = new ArrayList<>();

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<ItemReview> itemReviews = new ArrayList<>();

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Cart> carts = new ArrayList<>();

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<RecipeReview> recipeReviews = new ArrayList<>();

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Recipe> recipes = new ArrayList<>();

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<RecipeLike> recipeLikes = new ArrayList<>();

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<UserKeyword> userKeywords = new ArrayList<>();

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Address> addresses = new ArrayList<>();


}
