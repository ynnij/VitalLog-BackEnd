package com.vitallog.domain.board;

import java.util.Date;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Board {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private int id;
 
 private String title;
 
 @DateTimeFormat(pattern="yyyy-MM-dd")
 @Temporal(TemporalType.DATE)
 private Date createDate;
 private String contents;
 private String writer;
 
 @ColumnDefault("0")
 private int visitcount;

}
