package com.name.chess.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.name.shop.entity.CommentsEntity;
import com.name.shop.entity.ProductCommentsEntity;
import com.name.shop.mode.Product;
import com.name.shop.model.Cart;
import com.name.shop.model.CartItem;
import com.name.shop.model.CartModel;
import com.name.shop.model.CommentsModel;
import com.name.shop.model.ProductModel;

@Repository
public class ChessDaoImpl implements ChessDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Override
	public String startGame() {
		return "READY";
	}
	
}
