package jokeOfTheDay.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jokeOfTheDay.rest.jackson.map.DateTimeDeserializer;
import jokeOfTheDay.rest.jackson.map.DateTimeSerializer;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;

/**
 * Created by mart on 30.11.15.
 */
@Entity
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "joke_id")
    private Joke joke;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    @Column(columnDefinition = "TEXT")
    private String comment;

    @Column
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime added;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty
    public void setJoke(Joke joke) {
        this.joke = joke;
    }

    @JsonIgnore
    public Joke getJoke() {
        return joke;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @JsonSerialize(using = DateTimeSerializer.class)
    public DateTime getAdded() {
        return added;
    }

    @JsonDeserialize(using = DateTimeDeserializer.class)
    public void setAdded(DateTime added) {
        this.added = added;
    }
}
