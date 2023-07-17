package com.cisco.training.homeworkapplication.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "TAGS")
@EqualsAndHashCode
public class Tag {

    @Id
    @Column(name = "tag_id")
    private Integer tagId;

    @NonNull
    @Size(min = 3)
    @Size(max = 20)
    @Column(name = "name")
    private String name;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "tags")
    private Set<Content> contents;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "child_parent", joinColumns = @JoinColumn(name = "child_id"), inverseJoinColumns = @JoinColumn(name = "parent_id"))
    private Set<Tag> children;


    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "children")
    private Set<Tag> parents;
}
