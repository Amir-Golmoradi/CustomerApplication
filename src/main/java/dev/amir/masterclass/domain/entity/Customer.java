package dev.amir.masterclass.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity()
@Table(
        name = "customer",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "customer_email_unique",
                        columnNames = "customer_email"
                ),
//                @UniqueConstraint(
//                        name = "profile_image_id_unique",
//                        columnNames = "profileImageId"
//                )
        }
)
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @SequenceGenerator(name = "customer_id_sequence",
            sequenceName = "customer_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE,
            generator = "customer_id_sequence")
    @Column(name = "customer_id",
            nullable = false, columnDefinition = "Integer")
    private Integer id;
    @Column(name = "customer_name",
            nullable = false,
            columnDefinition = "TEXT")
    private String name;
    @Column(name = "customer_email",
            nullable = false,
            columnDefinition = "TEXT")
    private String email;
    @Column(name = "customer_age",
            nullable = false, columnDefinition = "Integer")
    private Integer age;


    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Customer customer = (Customer) o;
        return getId() != null && Objects.equals(getId(), customer.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
