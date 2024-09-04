package com.acs.springbasic.entity;

import com.acs.springbasic.dto.PostUserRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name="user")
@Table(name="sample_user")
@Builder
public class SampleUserEntity {

    @Id
    private String userId;
    private String password;
    private String name;
    private String address;
    private String telNumber;

    public SampleUserEntity(PostUserRequestDto dto) {
        this.userId = dto.getUserId();
        this.password = dto.getPassword();
        this.name = dto.getName();
        this.address = dto.getAddress();
        this.telNumber = dto.getTelNumber();
    };
    // : 서비스 레이어에서 한꺼번에 인스턴스를 생성하도록 만들고 여기서 직접 생성자를 만들어 줌
    
}
