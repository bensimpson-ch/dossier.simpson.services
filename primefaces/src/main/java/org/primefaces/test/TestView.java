package org.primefaces.test;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import javax.faces.view.ViewScoped;


import lombok.Data;

@Data
@ViewScoped
public class TestView implements Serializable {

    private String string = "Welcome to PrimeFaces!!!";
    private Integer integer;
    private BigDecimal decimal;
    private LocalDateTime localDateTime;

}
