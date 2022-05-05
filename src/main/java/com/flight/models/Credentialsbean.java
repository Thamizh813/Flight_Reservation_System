package com.flight.models;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="Credential_details")
@NoArgsConstructor
public class Credentialsbean {

@Id
private String userID;
private String password;
private String usertype;
private int loginstatus;







@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Credentialsbean)) return false;
    Credentialsbean user = (Credentialsbean) o;
    return Objects.equals(userID, user.userID) &&
            Objects.equals(password, user.password);
}
@Override
public int hashCode() {
    return Objects.hash(userID, password, usertype,loginstatus
                      );
}
@OneToOne(mappedBy = "cred", cascade = CascadeType.ALL)
private Profilebean prof;

@OneToMany(mappedBy = "credit",cascade = CascadeType.ALL)
@JsonIgnore
private List<CreditCard> credcard;

@OneToOne(mappedBy = "reserve",cascade = CascadeType.ALL)
@JsonIgnore
private Reservation reservation;




}
