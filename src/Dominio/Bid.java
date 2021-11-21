package Dominio;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Bid implements Comparable<Bid> {
	private long date;
	private float amount;	
	private Reto article;
	private Usuario user;

	public void setDate(Date date) {		
		this.date = date.getTime();
	}

	public Date getDate() {
		return new Date(this.date);
	}
	
	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Reto getArticle() {
		return article;
	}

	public void setArticle(Reto article) {
		this.article = article;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	@Override
	public int compareTo(Bid bid) {
		if (bid != null) {
			return Long.compare(this.date, bid.date);
		} else {		
			return 0;
		}
	}
	
	@Override
	public String toString() {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-YY - hh:mm");
		NumberFormat numberFormatter = NumberFormat.getCurrencyInstance(Locale.getDefault()); 

		StringBuffer result = new StringBuffer("User:");
		
		result.append(this.getUser().getEmail());
		result.append(" - Reto:");
		result.append(this.getArticle().getName());
		result.append(" - Date:");
		result.append(dateFormatter.format(this.date));
		
		return result.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this.getClass().getName().equals(obj.getClass().getName())) {
			Bid bid = (Bid)obj;
			
			if (this.user != null && this.article != null &&
					bid.user != null && bid.article != null) {			
					return this.date == bid.date &&
						   this.amount == bid.amount &&
						   this.article.equals(bid.article) &&
						   this.user.equals(bid.user);
				}
		}
		
		return false;
	}
}