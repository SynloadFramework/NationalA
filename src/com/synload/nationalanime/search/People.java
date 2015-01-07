package com.synload.nationalanime.search;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.synload.framework.modules.annotations.HasMany;
import com.synload.framework.modules.annotations.SQLTable;
import com.synload.framework.modules.annotations.SQLType;
import com.synload.framework.sql.Model;

@SQLTable(name="People", version=1.2, description="staff members")
public class People extends Model {
	
	
	
	@SQLType(Type="bigint(20)", Default = "", Key = true, NULL = false, Collation = "", CharSet = "", AutoIncrement = true, Index = false)
	public long id;
	
	@HasMany(key = "id", of = Anime.class)
	@SQLType(Type="longblob", Default = "", Key = false, NULL = false, Collation = "", CharSet = "", AutoIncrement = false, Index = false)
	public String animes;
	
	@SQLType(Type="varchar(255)", Default = "", Key = false, NULL = false, Collation = "utf8_general_ci", CharSet = "utf8", AutoIncrement = false, Index = true)
	public String name;
	
	@SQLType(Type="varchar(255)", Default = "", Key = false, NULL = false, Collation = "utf8_general_ci", CharSet = "utf8", AutoIncrement = false, Index = true)
	public String language;
	
	@HasMany(key = "id", of = Character.class)
	@SQLType(Type="longblob", Default = "", Key = false, NULL = false, Collation = "", CharSet = "", AutoIncrement = false, Index = false)
	public String characters;
	
	@SQLType(Type="varchar(255)", Default = "", Key = false, NULL = false, Collation = "", CharSet = "", AutoIncrement = false, Index = false)
	public String image;
	
	@HasMany(key = "id", of = Tag.class)
	@SQLType(Type="longblob", Default = "", Key = false, NULL = false, Collation = "", CharSet = "", AutoIncrement = false, Index = false)
	public String tags;
	
	@SQLType(Type="bigint(20)", Default = "", Key = false, NULL = false, Collation = "", CharSet = "", AutoIncrement = false, Index = true)
	public long myCreatorId;
	
	public People(ResultSet rs) {
		super(rs);
	}
	public People(Object... data){
		super(data);
	}
	
	public String getAnimes() {
		return animes;
	}
	public void setAnimes(String animes) {
		this.animes = animes;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCharacters() {
		return characters;
	}
	public void setCharacters(String characters) {
		this.characters = characters;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	
	public static List<People> getByIdList( String people, int page, int limit ){
		try {
			return _find(People.class, "`id` IN ( ? )", people).limit( (limit*page)+", "+(limit) ).exec(People.class);
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
