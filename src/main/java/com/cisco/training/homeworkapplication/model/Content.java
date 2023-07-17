package com.cisco.training.homeworkapplication.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity(name = "Contents")
public class Content {

    @Id
    @Column(name = "content_id")
    private Integer contentId;

    @Column(name = "content")
    private String content;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "content_tags", joinColumns = @JoinColumn(name = "content_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags;
}
