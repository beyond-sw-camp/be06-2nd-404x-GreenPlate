package com.team404x.greenplate.recipe.model.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.team404x.greenplate.recipe.keyword.entity.RecipeKeyword;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.team404x.greenplate.company.model.entity.Company;
import com.team404x.greenplate.livecommerce.entity.Livecommerce;
import com.team404x.greenplate.recipe.item.RecipeItem;
import com.team404x.greenplate.recipe.likes.RecipeLike;
import com.team404x.greenplate.recipe.review.model.RecipeReview;
import com.team404x.greenplate.user.model.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")
	private Company company;

	@OneToMany(mappedBy = "recipe", fetch = FetchType.LAZY)
	private List<RecipeLike> recipeLikes = new ArrayList<>();

	@OneToMany(mappedBy = "recipe", fetch = FetchType.LAZY)
	private List<RecipeReview> recipeReviews = new ArrayList<>();

	@OneToMany(mappedBy = "recipe", fetch = FetchType.LAZY)
	private List<RecipeItem> recipeItems;

	@OneToMany(mappedBy = "recipe", fetch = FetchType.LAZY)
	private List<RecipeKeyword> recipeKeywords;

	@OneToMany(mappedBy = "recipe", fetch = FetchType.LAZY)
	private List<Livecommerce> livecommerces = new ArrayList<>();

	private String title;

	@Column(length=3000)
	private String contents;

	private String imageUrl;

	private Integer totalCalorie;

	@Builder.Default
	private boolean delYn = false;

	@CreatedDate
	private LocalDateTime createdDate;

	@LastModifiedDate
	private LocalDateTime modifiedDate;

}
