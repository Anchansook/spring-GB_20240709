package com.acs.springbasic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.acs.springbasic.dto.Lombok;

@SpringBootApplication
public class SpringbasicApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbasicApplication.class, args);
	}

	// Lombok.java
	void lombokExample() {

		Lombok lombok = new Lombok("a", "b", "c", false, false);
		lombok.getField1();
		lombok.setField3(null);

		// lombok = new Lombok(); => @NoArgsConstructor 사용했을 때
		lombok = new Lombok("d", "e");

		lombok.isField4();
		lombok.getField5();

	}

}
