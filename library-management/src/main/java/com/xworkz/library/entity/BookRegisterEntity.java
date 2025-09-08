package com.xworkz.library.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "library_book_register")
@NamedQuery(name = "getAll",query = "select a from BookRegisterEntity a")
@NamedQuery(name = "getDataById", query = "select a from BookRegisterEntity a where a.bookId=:id")
@NamedQuery(name = "deleteDataById",query = "delete from BookRegisterEntity a where a.bookId=:id")
public class BookRegisterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Integer bookId;

    @Column(name = "book_title")
    private String bookTitle;

    @Column(name = "author")
    private String author;

    @Column(name = "language")
    private String language;

    @Column(name = "published_date")
    private LocalDate publishedDate;

    @Column(name = "price")
    private Float price;

    @OneToMany(mappedBy = "registerEntity",cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<AuditInfoEntity> audits=new ArrayList<>();

    @PrePersist
    public void onPrePersist() {
        AuditInfoEntity audit1 = new AuditInfoEntity();
        audit1.setRegisterEntity(this);
        audit1.setCreatedAt(LocalDateTime.now());
        audit1.setCreatedBy(this.bookTitle);
        audits.add(audit1);

        AuditInfoEntity audit2 = new AuditInfoEntity();
        audit2.setRegisterEntity(this);
        audit2.setCreatedAt(LocalDateTime.now());
        audit2.setCreatedBy("Admin");
        audits.add(audit2);
    }

}
