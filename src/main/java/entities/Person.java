package entities;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "person")
@NamedQuery(name = "Person.deleteAllRows", query = "DELETE from Person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id", nullable = false)
    private Integer id;

    @Size(max = 45)
    @NotNull
    @Column(name = "person_firstname", nullable = false, length = 45)
    private String personFirstname;

    @Size(max = 45)
    @NotNull
    @Column(name = "person_lastname", nullable = false, length = 45)
    private String personLastname;

    @Size(max = 45)
    @NotNull
    @Column(name = "person_email", nullable = false, length = 45)
    private String personEmail;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_address_id")
    private Address fkAddress;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "hobby_has_person",
            joinColumns = @JoinColumn(name = "fk_person_id"),
            inverseJoinColumns = @JoinColumn(name = "fk_hobby_id"))
    private Set<Hobby> hobbies = new LinkedHashSet<>();

    @OneToMany(mappedBy = "fkPerson", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Phone> phones = new LinkedHashSet<>();

    public Person() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPersonFirstname() {
        return personFirstname;
    }

    public void setPersonFirstname(String personFirstname) {
        this.personFirstname = personFirstname;
    }

    public String getPersonLastname() {
        return personLastname;
    }

    public void setPersonLastname(String personLastname) {
        this.personLastname = personLastname;
    }

    public String getPersonEmail() {
        return personEmail;
    }

    public void setPersonEmail(String personEmail) {
        this.personEmail = personEmail;
    }

    public void addAddress(Address address) {
        this.fkAddress = address;
        address.getPeople().add(this);
    }

    public Address getFkAddress() {
        return fkAddress;
    }

    public void setFkAddress(Address fkAddress) {
        this.fkAddress = fkAddress;
    }

    public Set<Hobby> getHobbies() {
        return hobbies;
    }

    public void addHobby(Hobby hobby) {
        this.hobbies.add(hobby);
        hobby.getPeople().add(this);
    }

    public void setHobbies(Set<Hobby> hobbies) {
        this.hobbies = hobbies;
    }

    public void setPhones(Set<Phone> phones) {
        this.phones = phones;
    }

    public Set<Phone> getPhones() {
        return phones;
    }

    public void addPhones(Phone phone) {
        this.phones.add(phone);
        phone.setFkPerson(this);
    }

    public Person(String personFirstname, String personLastname, String personEmail) {
        this.personFirstname = personFirstname;
        this.personLastname = personLastname;
        this.personEmail = personEmail;
    }
}