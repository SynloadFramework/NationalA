package com.synload.nationalanime.search;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.synload.framework.modules.annotations.HasMany;
import com.synload.framework.modules.annotations.HasOne;
import com.synload.framework.modules.annotations.SQLTable;
import com.synload.framework.modules.annotations.SQLType;
import com.synload.framework.sql.Model;

@SQLTable(name="Character",version=1.4, description = "character data for anime")
public class Character extends Model{
	
	
	@SQLType(Type="bigint(20)", Default = "", Key = true, NULL = false, Collation = "", CharSet = "", AutoIncrement = true, Index = false)
	public long id;
	
	@SQLType(Type="varchar(255)", Default = "", Key = false, NULL = false, Collation = "", CharSet = "", AutoIncrement = false, Index = false)
	public String image;
	
	@SQLType(Type="longblob", Default = "", Key = false, NULL = false, Collation = "utf8_general_ci", CharSet = "utf8", AutoIncrement = false, Index = false)
	public String description;
	
	@SQLType(Type="varchar(255)", Default = "", Key = false, NULL = false, Collation = "utf8_general_ci", CharSet = "utf8", AutoIncrement = false, Index = true)
	public String name;
	
	@HasOne(key = "id", of = People.class)
	@SQLType(Type="bigint(20)", Default = "", Key = false, NULL = false, Collation = "", CharSet = "", AutoIncrement = false, Index = false)
	public long person;
	
	@HasMany(key = "id", of = Anime.class)
	@SQLType(Type="longblob", Default = "", Key = false, NULL = false, Collation = "", CharSet = "", AutoIncrement = false, Index = false)
	public String animes;
	
	@SQLType(Type="bigint(20)", Default = "", Key = false, NULL = false, Collation = "", CharSet = "", AutoIncrement = false, Index = true)
	public long myCharId;
	
	@HasMany(key = "id", of = Tag.class)
	@SQLType(Type="longblob", Default = "", Key = false, NULL = false, Collation = "", CharSet = "", AutoIncrement = false, Index = false)
	public String tags;
	
	public Character(ResultSet rs) {
		super(rs);
	}
	public Character(Object... data) {
		super(data);
	}
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAnimes() {
		return animes;
	}
	public void setAnimes(String animes) {
		this.animes = animes;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	
	public static List<Character> getByIdList( String chars, int page, int limit ){
		try {
			return _find(Character.class, "`id` IN ( ? )", chars).limit( (limit*page)+", "+(limit) ).exec(Character.class);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
