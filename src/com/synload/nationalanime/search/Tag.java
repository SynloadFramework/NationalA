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

@SQLTable(name="Tag", version=1.2, description="contains tags for anime")
public class Tag extends Model {
	
	@SQLType(Type="bigint(20)", Default = "", Key = true, NULL = false, Collation = "", CharSet = "", AutoIncrement = true, Index = false)
	public long id;
	@SQLType(Type="varchar(255)", Default = "", Key = false, NULL = false, Collation = "utf8_general_ci", CharSet = "utf8", AutoIncrement = false, Index = true)
	public String type;
	@SQLType(Type="varchar(512)", Default = "", Key = false, NULL = false, Collation = "utf8_general_ci", CharSet = "utf8", AutoIncrement = false, Index = true)
	public String tag;
	
	@HasMany(key = "id", of = Anime.class)
	@SQLType(Type="longblob", Default = "", Key = false, NULL = false, Collation = "", CharSet = "", AutoIncrement = false, Index = false)
	public String animes;
	
	@HasMany(key = "id", of = People.class)
	@SQLType(Type="longblob", Default = "", Key = false, NULL = false, Collation = "", CharSet = "", AutoIncrement = false, Index = false)
	public String people;
	
	@HasOne(key = "id", of = Tag.class)
	@SQLType(Type="bigint(20)", Default = "", Key = false, NULL = false, Collation = "", CharSet = "", AutoIncrement = false, Index = false)
	public long parent;
	
	@SQLType(Type="bigint(20)", Default = "", Key = false, NULL = false, Collation = "", CharSet = "", AutoIncrement = false, Index = true)
	public long myTagId;
	
	public Tag(ResultSet rs) {
		super(rs);
	}
	public Tag(Object... data) {
		super(data);
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}

	
	public static List<Tag> getSearchByTagName( String tagname, int page, int limit ){
		try {
			return _find(Tag.class, "`tag` LIKE ?", "%"+tagname+"%").limit( (limit*page)+", "+(limit) ).exec(Tag.class);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static Tag getSearchById(String id){
		try {
			return _find(Tag.class, "`id`=?", id).limit("1").exec(Tag.class).get(0);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static List<Tag> getByName(String name, int page, int limit ){
		try {
			return _find(Tag.class, "`name`=?", name).limit( (limit*page)+", "+(limit) ).exec(Tag.class);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static List<Tag> getByIdList( String tags, int page, int limit ){
		try {
			return _find(Tag.class, "`id` IN ( ? )", tags).limit( (limit*page)+", "+limit ).exec(Tag.class);	} catch (InstantiationException e) {
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
