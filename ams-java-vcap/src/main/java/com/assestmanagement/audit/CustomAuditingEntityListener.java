package com.assestmanagement.audit;

import java.time.LocalDateTime;

import org.springframework.data.domain.Auditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

public class CustomAuditingEntityListener  extends AuditingEntityListener{
	@Override
    public void touchForCreate(Object target) {
        super.touchForCreate(target);
        
        if (target instanceof Auditable) {
            Auditable auditable = (Auditable) target;
            System.out.println("Entity created at: " + LocalDateTime.now());
            System.out.println("Created by: " + auditable.getCreatedBy());
        }
    }

    @Override
    public void touchForUpdate(Object target) {
        super.touchForUpdate(target);
        
        if (target instanceof Auditable) {
            Auditable auditable = (Auditable) target;
            System.out.println("Entity updated at: " + LocalDateTime.now());
            System.out.println("Updated by: " + auditable.getLastModifiedBy());
        }
    }
}
