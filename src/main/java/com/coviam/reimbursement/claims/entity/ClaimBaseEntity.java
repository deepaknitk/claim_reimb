package com.coviam.reimbursement.claims.entity;

    import com.coviam.reimbursement.claims.model.constants.FieldNames;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import org.springframework.data.annotation.CreatedBy;
    import org.springframework.data.annotation.CreatedDate;
    import org.springframework.data.annotation.LastModifiedBy;
    import org.springframework.data.annotation.LastModifiedDate;
    import org.springframework.data.jpa.domain.support.AuditingEntityListener;
    import org.springframework.format.annotation.DateTimeFormat;

    import javax.persistence.Column;
    import javax.persistence.EntityListeners;
    import javax.persistence.MappedSuperclass;
    import java.io.Serializable;
    import java.util.Date;

@Data
@EqualsAndHashCode
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
public class ClaimBaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @CreatedBy
    @Column(name = FieldNames.CREATED_BY,nullable = false)
    private String createdBy;

    @CreatedDate
    @Column(name = FieldNames.CREATED_DATE,nullable = false)
    private Date createdAt;

    @LastModifiedBy
    @Column(name = FieldNames.UPDATED_BY)
    private String updatedBy;

    @LastModifiedDate
    @Column(name = FieldNames.UPDATED_DATE)
    private Date updatedAt;

    @Column(name = FieldNames.MARK_FOR_DELETE,nullable = false)
    private boolean markForDelete;
}
