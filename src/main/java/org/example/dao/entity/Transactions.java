package org.example.dao.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "transactions")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    @Column(name ="sender_card_number")
    private String senderCardNumber;
    @Column(name ="receiver_card_number")
    private String receiverCardNumber;
    private String status;
    private Double amount;
    @Column(name ="ccy_code")
    private String ccyCode;
    @Column(name ="is_active")
    private Boolean isActive;
    @Column(name ="created_at")
    private Timestamp createdAt;
    @Column(name ="updated_at")
    private Timestamp updatedAt;

    @Override
    public String toString() {
        return "Transactions{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", senderCardNumber='" + senderCardNumber + '\'' +
                ", receiverCardNumber='" + receiverCardNumber + '\'' +
                ", status='" + status + '\'' +
                ", amount=" + amount +
                ", ccyCode='" + ccyCode + '\'' +
                ", isActive=" + isActive +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
