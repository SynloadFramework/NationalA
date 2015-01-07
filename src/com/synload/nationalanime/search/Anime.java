package com.synload.nationalanime.search;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import com.synload.framework.Log;
import com.synload.framework.modules.annotations.HasMany;
import com.synload.framework.modules.annotations.SQLTable;
import com.synload.framework.modules.annotations.SQLType;
import com.synload.framework.sql.Model;
import com.synload.nationalanime.NationalAnime;

@SQLTable(name="Anime", version=1.3, description="anime data")
public class Anime extends Model {
	
	@SQLType(Type="bigint(20)", Default = "", Key = true, NULL = false, Collation = "", CharSet = "", AutoIncrement = true, Index = false)
	public long id;
	
	@SQLType(Type="varchar(255)", Default = "", Key = false, NULL = false, Collation = "utf8_general_ci", CharSet = "utf8", AutoIncrement = false, Index = true)
	public String title;
	
	@SQLType(Type="longblob", Default = "", Key = false, NULL = false, Collation = "utf8_general_ci", CharSet = "utf8", AutoIncrement = false, Index = false)
	public String description;
	
	@SQLType(Type="int(20)", Default = "", Key = false, NULL = false, Collation = "", CharSet = "", AutoIncrement = false, Index = true)
	public int episodes;
	
	@SQLType(Type="varchar(255)", Default = "", Key = false, NULL = false, Collation = "utf8_general_ci", CharSet = "utf8", AutoIncrement = false, Index = true)
	public String status;
	
	@SQLType(Type="varchar(255)", Default = "", Key = false, NULL = false, Collation = "", CharSet = "", AutoIncrement = false, Index = true)
	public String type;
	
	@SQLType(Type="varchar(255)", Default = "", Key = false, NULL = false, Collation = "", CharSet = "", AutoIncrement = false, Index = false)
	public String image;
	
	@SQLType(Type="varchar(255)", Default = "", Key = false, NULL = false, Collation = "", CharSet = "", AutoIncrement = false, Index = false)
	public String rating;
	
	@HasMany(key = "id", of = Tag.class)
	@SQLType(Type="longblob", Default = "", Key = false, NULL = false, Collation = "", CharSet = "", AutoIncrement = false, Index = false)
	public String tags;
	
	@HasMany(key = "id", of = Character.class)
	@SQLType(Type="longblob", Default = "", Key = false, NULL = false, Collation = "", CharSet = "", AutoIncrement = false, Index = false)
	public String characters;
	
	@HasMany(key = "id", of = People.class)
	@SQLType(Type="longblob", Default = "", Key = false, NULL = false, Collation = "", CharSet = "", AutoIncrement = false, Index = false)
	public String people;
	
	@SQLType(Type="DOUBLE", Default = "", Key = false, NULL = false, Collation = "", CharSet = "", AutoIncrement = false, Index = true)
	public double score;
	
	@SQLType(Type="longblob", Default = "", Key = false, NULL = false, Collation = "", CharSet = "", AutoIncrement = false, Index = false)
	public String titles;
	
	@HasMany(key = "id", of = Anime.class)
	@SQLType(Type="longblob", Default = "", Key = false, NULL = false, Collation = "", CharSet = "", AutoIncrement = false, Index = false)
	public String animes;
	
	@SQLType(Type="bigint(20)", Default = "", Key = false, NULL = false, Collation = "", CharSet = "", AutoIncrement = false, Index = true)
	public long myAnimeId;
	
	public Anime(ResultSet rs) {
		super(rs);
	}
	public Anime(Object... data){
		super(data);
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getEpisodes() {
		return episodes;
	}
	public void setEpisodes(int episodes) {
		this.episodes = episodes;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getCharacters() {
		return characters;
	}
	public void setCharacters(String characters) {
		this.characters = characters;
	}
	public String getPeople() {
		return people;
	}
	public void setPeople(String people) {
		this.people = people;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public String getTitles() {
		return titles;
	}
	public void setTitles(String titles) {
		this.titles = titles;
	}
	
	/*
	 * STRING TO LISTS
	 */
	public List<String> getPeopleList(){
		return Arrays.asList(people.split(","));
	}
	public List<String> getCharactersList(){
		return Arrays.asList(characters.split(","));
	}
	public List<String> getTagsList(){
		return Arrays.asList(tags.split(","));
	}
	/*
	 * END STRING LISTS
	 */
	
	public List<People> getPeopleList(int page, int limit){
		return People.getByIdList(people, page, limit);
	}
	public List<Tag> getTagList(int page, int limit){
		return Tag.getByIdList(tags, page, limit);
	}
	
	public static List<Anime> getByIdList( String animes, int page, int limit ){
		try {
			return _find(Anime.class, "`id` IN ( ? )", animes).limit( (limit*page)+", "+limit ).exec(Anime.class);
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
	public static List<Anime> getByTitle(String title, int page, int limit){
		try {
			return _find(Anime.class, "`title`=?", title).limit( (limit*page)+", "+limit ).exec(Anime.class);
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
}
